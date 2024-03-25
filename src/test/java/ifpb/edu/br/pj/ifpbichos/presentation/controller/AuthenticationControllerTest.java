package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import ifpb.edu.br.pj.ifpbichos.business.service.TokenService;
import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.AuthenticationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.LoginResponseDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.UserRegistrationDTO;

public class AuthenticationControllerTest {

    private AuthenticationController authenticationController;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private TokenService tokenService;

    @BeforeEach
    public void setUp() {
        authenticationManager = mock(AuthenticationManager.class);
        userRepository = mock(UserRepository.class);
        tokenService = mock(TokenService.class);
        authenticationController = new AuthenticationController();
        authenticationController.setAuthenticationManager(authenticationManager);
        authenticationController.setUserRepository(userRepository);
        authenticationController.setTokenService(tokenService);
    }

    @Test
    public void testLogin_Success() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(createMockDonator());
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenService.generateToken(any(User.class))).thenReturn("mocked_token");

        AuthenticationDTO authenticationDTO = new AuthenticationDTO("mocked_login","mocked_password");


        ResponseEntity response = authenticationController.login(authenticationDTO);

        assert response.getStatusCodeValue() == 200;
        assert response.getBody() instanceof LoginResponseDTO;
        LoginResponseDTO loginResponseDTO = (LoginResponseDTO) response.getBody();
        assert loginResponseDTO.token().equals("mocked_token");
        assert loginResponseDTO.user().equals("mocked_login");
        assert loginResponseDTO.userRole() == UserRoles.USER;
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
    public void testUserRegistration_Success() {
        when(userRepository.existsByLogin(any())).thenReturn(false);

        UserRegistrationDTO userRegistrationDTO = createUserRegistrationDTO();

        ResponseEntity response = authenticationController.userRegistration(userRegistrationDTO);

        assert response.getStatusCodeValue() == 200;
    }

    @Test
    public void testUserRegistration_UserAlreadyExists() {
        when(userRepository.existsByLogin(any())).thenReturn(true);
        when(userRepository.existsByCPF(any())).thenReturn(true);


        UserRegistrationDTO userRegistrationDTO = createUserRegistrationDTO();

        ResponseEntity response = authenticationController.userRegistration(userRegistrationDTO);

        assert response.getStatusCodeValue() == 400;
    }

    private User createMockDonator() {
        return new Donator("mocked_name", "mocked_cpf", "mocked_phone", "mocked_email",
                "mocked_login","mocked_password", UserRoles.USER,"mocked_register", DonatorType.PRIVATE_INDIVIDUAL );
    }

    private UserRegistrationDTO createUserRegistrationDTO() {
        return new UserRegistrationDTO("mocked_name", "mocked_Phone", "mocked_email", "mocked_login",
                "mocked_password", UserRoles.USER,"mocked_register", DonatorType.PRIVATE_INDIVIDUAL , "mocked_CPF", ComissionMemberRole.ADMIN);
    }
}
