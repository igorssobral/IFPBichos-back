package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import ifpb.edu.br.pj.ifpbichos.business.service.LoginService;
import ifpb.edu.br.pj.ifpbichos.business.service.TokenService;
import ifpb.edu.br.pj.ifpbichos.business.service.UserRegistrationService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.AuthenticationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.LoginResponseDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.UserRegistrationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectAlreadyExistsException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class AuthenticationControllerTest {

    @Mock
    private LoginService loginService;

    @Mock
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRegistrationService userRegistrationService;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    public void setUp() {
        authenticationController = new AuthenticationController();
        authenticationManager = mock(AuthenticationManager.class);
        userRepository = mock(UserRepository.class);
        userRegistrationService = mock(UserRegistrationService.class);
        tokenService = mock(TokenService.class);
        loginService = mock(LoginService.class);

        authenticationController.setAuthenticationManager(authenticationManager);
        authenticationController.setUserRepository(userRepository);
        authenticationController.setUserRegistrationService(userRegistrationService);
        authenticationController.setTokenService(tokenService);
        authenticationController.setLoginService(loginService); // Configurar o loginService no controller

    }
    @Test
    void login_ValidUser_ReturnsToken() throws Exception {
        AuthenticationDTO dto = new AuthenticationDTO("username@gmail.com", "password");
        User user = createMockUser();

        when(userRepository.existsByLogin(dto.login())).thenReturn(true);
        when(userRepository.findByLogin(dto.login())).thenReturn(user);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(tokenService.generateToken(user)).thenReturn("generatedToken");

        LoginResponseDTO loginResponse = new LoginResponseDTO("generatedToken", dto.login(), user.getUserRole());
        when(loginService.login(dto)).thenReturn(loginResponse);

        ResponseEntity responseEntity = authenticationController.login(dto);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof LoginResponseDTO);

        LoginResponseDTO loginResponseDTO = (LoginResponseDTO) responseEntity.getBody();
        assertEquals("username@gmail.com", loginResponseDTO.user());
        assertEquals(UserRoles.USER.toString(), loginResponseDTO.userRole().toString());
        assertEquals("generatedToken", loginResponseDTO.token());
    }



    @Test
    public void testLogin_UserNotFound() throws Exception {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO("non_existent_user", "mocked_password");

        when(userRepository.existsByLogin(authenticationDTO.login())).thenReturn(false);

        doThrow(new ObjectNotFoundException("Usuário inexistente!")).when(loginService).login(authenticationDTO);

        ResponseEntity<?> response = authenticationController.login(authenticationDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Usuário inexistente!", response.getBody());
    }

    @Test
    void testUserRegistration_Success() throws Exception {

        UserRegistrationDTO dto = createUserRegistrationDTO();
        when(userRepository.existsByLogin(dto.login())).thenReturn(false);
        when(userRepository.existsByCPF(dto.CPF())).thenReturn(false);

        ResponseEntity responseEntity = authenticationController.userRegistration(dto);

        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(userRegistrationService, times(1)).registerUser(dto);
    }

    @Test
    void testUserRegistration_EmailAlreadyExists() throws Exception {

        UserRegistrationDTO dto = createUserRegistrationDTO();
        doThrow(new ObjectAlreadyExistsException("Já existe um usuário com esse email")).when(userRegistrationService).registerUser(dto);

        ResponseEntity<?> responseEntity = authenticationController.userRegistration(dto);

        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("Já existe um usuário com esse email", responseEntity.getBody());
        verify(userRegistrationService, times(1)).registerUser(dto);



    }

    @Test
    public void testUserRegistration_CPFAlreadyExists() throws Exception {
        UserRegistrationDTO dto = createUserRegistrationDTO();
        doThrow(new ObjectAlreadyExistsException("Já existe um usuário com esse CPF")).when(userRegistrationService).registerUser(dto);

        ResponseEntity<?> responseEntity = authenticationController.userRegistration(dto);

        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("Já existe um usuário com esse CPF", responseEntity.getBody());
        verify(userRegistrationService, times(1)).registerUser(dto);
    }

    @Test
    void testUserRegistration_UserAlreadyExists() throws Exception {
        UserRegistrationDTO dto = createUserRegistrationDTO();
        doThrow(new ObjectAlreadyExistsException("Já existe um usuário com esse login")).when(userRegistrationService).registerUser(dto);
        doThrow(new ObjectAlreadyExistsException("Já existe um usuário com esse CPF")).when(userRegistrationService).registerUser(dto);




        ResponseEntity<?> response = authenticationController.userRegistration(dto);

        assertEquals(400, response.getStatusCodeValue());

        assertTrue(response.getBody().toString().contains("Já existe um usuário com esse login") ||
                response.getBody().toString().contains("Já existe um usuário com esse CPF"));
    }


    private User createMockUser() {
        return new Donator("mocked_name", "mocked_cpf", "mocked_phone", "username@gmail.com",
                "username@gmail.com", "password", UserRoles.USER, DonatorType.PRIVATE_INDIVIDUAL);
    }


    private UserRegistrationDTO createUserRegistrationDTO() {
        return new UserRegistrationDTO("mocked_name", "username@gmail.com", "mocked_login", "username@gmail.com",
                "username@gmail.com", UserRoles.USER, "mocked_register", DonatorType.PRIVATE_INDIVIDUAL, "mocked_CPF", ComissionMemberRole.COLABORATOR);
    }

    @Test
    void isValidToken_ValidToken_ReturnsOk() {

        String validToken = "validToken";
        when(tokenService.isValidToken(validToken)).thenReturn(true);


        ResponseEntity responseEntity = authenticationController.isValidToken(validToken);


        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }

    @Test
    void isValidToken_InvalidToken_ReturnsBadRequest() {

        String invalidToken = "invalidToken";
        when(tokenService.isValidToken(invalidToken)).thenReturn(false);


        ResponseEntity responseEntity = authenticationController.isValidToken(invalidToken);


        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("token invalid!!", responseEntity.getBody());
    }

    @Test
    void isValidToken_ExceptionThrown_ReturnsInternalServerError() {

        String token = "token";
        String expectedErrorMessage = "Internal Server Error";
        when(tokenService.isValidToken(token)).thenThrow(new RuntimeException(expectedErrorMessage));


        ResponseEntity responseEntity = authenticationController.isValidToken(token);


        assertEquals(500, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedErrorMessage, ((RuntimeException) responseEntity.getBody()).getMessage());    }

}
