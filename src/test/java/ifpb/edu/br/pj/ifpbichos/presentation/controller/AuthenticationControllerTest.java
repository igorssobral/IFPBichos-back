package ifpb.edu.br.pj.ifpbichos.presentation.controller;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class AuthenticationControllerTest {

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
        authenticationController.setAuthenticationManager(authenticationManager);
        authenticationController.setUserRepository(userRepository);
        authenticationController.setUserRegistrationService(userRegistrationService);
        authenticationController.setTokenService(tokenService);
    }
    @Test
    void login_ValidUser_ReturnsToken() {

        AuthenticationDTO dto = new AuthenticationDTO("username@gmail.com", "password");
        User user = createMockUser();
        when(userRepository.existsByLogin(dto.login())).thenReturn(true);
        when(userRepository.findByLogin(dto.login())).thenReturn(user);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(tokenService.generateToken(user)).thenReturn("generatedToken");


        ResponseEntity responseEntity = authenticationController.login(dto);


        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertInstanceOf(LoginResponseDTO.class, responseEntity.getBody());
        LoginResponseDTO loginResponseDTO = (LoginResponseDTO) responseEntity.getBody();
        assertEquals("username@gmail.com", loginResponseDTO.user());
        assertEquals(UserRoles.USER.toString(), loginResponseDTO.userRole().toString());
        assertEquals("generatedToken", loginResponseDTO.token());
    }


    @Test
    public void testLogin_UserNotFound() {
        when(userRepository.existsByLogin(any())).thenReturn(false);

        AuthenticationDTO authenticationDTO = new AuthenticationDTO("non_existent_user","mocked_password");


        ResponseEntity response = authenticationController.login(authenticationDTO);

        assert response.getStatusCodeValue() == 400;
        assert response.getBody().equals("Usuário inexistente");
    }

    @Test
    void testUserRegistration_Success() {

        UserRegistrationDTO dto = createUserRegistrationDTO();
        when(userRepository.existsByLogin(dto.login())).thenReturn(false);
        when(userRepository.existsByCPF(dto.CPF())).thenReturn(false);

        // Act
        ResponseEntity responseEntity = authenticationController.userRegistration(dto);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(userRegistrationService, times(1)).registerUser(dto);
    }

    @Test
    void testUserRegistration_EmailAlreadyExists() {

        UserRegistrationDTO dto = createUserRegistrationDTO();
        when(userRepository.existsByLogin(dto.login())).thenReturn(true);


        ResponseEntity responseEntity = authenticationController.userRegistration(dto);


        assertEquals(400, responseEntity.getStatusCodeValue());
        assertTrue(responseEntity.getBody().toString().contains("Ja existe uma conta cadastrada com esse email"));
        verify(userRegistrationService, never()).registerUser(dto);
    }

    @Test
    void testUserRegistration_CPFAlreadyExists() {

        UserRegistrationDTO dto = createUserRegistrationDTO();
        when(userRepository.existsByLogin(dto.login())).thenReturn(false);
        when(userRepository.existsByCPF(dto.CPF())).thenReturn(true);


        ResponseEntity responseEntity = authenticationController.userRegistration(dto);


        assertEquals(400, responseEntity.getStatusCodeValue());
        assertTrue(responseEntity.getBody().toString().contains("CPF já registrado"));
        verify(userRegistrationService, never()).registerUser(dto);
    }


    @Test
    public void testUserRegistration_UserAlreadyExists() {
        when(userRepository.existsByLogin(any())).thenReturn(true);
        when(userRepository.existsByCPF(any())).thenReturn(true);


        UserRegistrationDTO userRegistrationDTO = createUserRegistrationDTO();

        ResponseEntity response = authenticationController.userRegistration(userRegistrationDTO);

        assert response.getStatusCodeValue() == 400;
    }

    private User createMockUser() {
        return new Donator("mocked_name", "mocked_cpf", "mocked_phone", "username@gmail.com",
                "username@gmail.com", "joao1234", UserRoles.USER, DonatorType.PRIVATE_INDIVIDUAL);
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
