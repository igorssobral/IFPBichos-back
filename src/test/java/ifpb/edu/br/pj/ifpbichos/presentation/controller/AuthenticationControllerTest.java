package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ifpb.edu.br.pj.ifpbichos.business.service.TokenService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.AuthenticationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.UserRegistrationDTO;

public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController controller;

    @Mock
    private UserRepository repository;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthenticationManager authenticationManager;
    
    private AuthenticationDTO exDto;
    
    private ResponseEntity respEntity;
    
    private User exDonator;

    @BeforeEach
    public void setup() {
    	MockitoAnnotations.openMocks(this);
        exDonator = new Donator();
        exDonator.setId(1);
        exDonator.setLogin("ytallopereiralves@gmail.com");
        exDonator.setPassword("12345678");
        exDto = new  AuthenticationDTO("ytallopereiralves@gmail.com","12345678");
    }
    
  
    @Test
    public void testLoginValid() {
        // Configure o repositório para retornar exDonator quando getReferenceById(1) for chamado
        when(repository.getReferenceById(1)).thenReturn(exDonator);

        exDto = new AuthenticationDTO(exDonator.getEmail(),exDonator.getPassword());

       // when(repository.existsByLogin( exDto.login())).thenReturn(true);
       // when(authenticationManager.authenticate(any())).thenReturn(new TestingAuthenticationToken("ytallopereiralves@gmail.com", "12345678", new ArrayList<>()));

        respEntity = controller.login( exDto);

        assertEquals(HttpStatus.OK, respEntity.getStatusCode());
    }
    
    /**@Test
    public void testLogin() {
    	
    	when(repository.existsByLogin(exDto.login())).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(new TestingAuthenticationToken(exDto.login(), exDto.password(), new ArrayList<>()));

        respEntity = controller.login(exDto);
        assertEquals(HttpStatus.OK, respEntity.getStatusCode());
    }
    **/
    /**
    @Test
    public void testLoginValid() {
    	when(repository.getReferenceById(1)).thenReturn(exDonator);
    	//User donator = repository.getReferenceById(1);
        AuthenticationDTO dto = new AuthenticationDTO(exDonator.getEmail(),exDonator.getPassword());
        
        

        when(repository.existsByLogin(dto.login())).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(new TestingAuthenticationToken("ytallopereiralves@gmail.com", "12345678", new ArrayList<>()));

        ResponseEntity response = controller.login(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
	**/
    @Test
    public void testUserRegistrationValid() {
        UserRegistrationDTO dto = new UserRegistrationDTO(null, null, null, null, null, null, null, null, null, null);
       

        when(repository.existsByLogin(dto.login())).thenReturn(false);

        ResponseEntity response = controller.userRegistration(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}