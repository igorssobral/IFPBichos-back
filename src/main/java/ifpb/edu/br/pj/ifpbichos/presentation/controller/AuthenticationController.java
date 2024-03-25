package ifpb.edu.br.pj.ifpbichos.presentation.controller;
import ifpb.edu.br.pj.ifpbichos.business.service.TokenService;
import ifpb.edu.br.pj.ifpbichos.model.entity.ComissionMember;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.AuthenticationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.LoginResponseDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.UserRegistrationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.MissingFieldException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenService;


	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
		if(dto.login().isEmpty() || dto.password().isEmpty()) {
			Exception e = new MissingFieldException("email ou senha");
			return ResponseEntity.badRequest().body(e.getMessage());
		}else if(!userRepository.existsByLogin(dto.login())) {
			return ResponseEntity.badRequest().body("Usuário inexistente");
		}

		User user = (User) userRepository.findByLogin(dto.login());

		var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((User) auth.getPrincipal());


		return ResponseEntity.ok(new LoginResponseDTO(token, dto.login(),user.getUserRole()));

	}

	@PostMapping("/userRegistration")
	public ResponseEntity userRegistration(@RequestBody UserRegistrationDTO dto) {
		Exception e =null;
		if(dto.name().isEmpty() || dto.CPF().isBlank() || dto.phoneNumber().isEmpty() || 
				dto.email().isEmpty() || dto.password().isEmpty()) {
			e = new MissingFieldException();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		if(userRepository.existsByLogin(dto.login())) {
			 e = new ObjectAlreadyExistsException("Já existe um usuário com esse login");
			 return ResponseEntity.badRequest().body(e.getMessage());
		}

		User newUser = null;
		
		if(dto.name().isEmpty() || dto.CPF().isBlank() || dto.phoneNumber().isEmpty() || 
				dto.email().isEmpty() || dto.password().isEmpty()) {
			
		}

		String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
		if(dto.userRole() == UserRoles.ADMIN) {
			newUser = new ComissionMember(dto.name(),dto.CPF(),dto.phoneNumber(),dto.email(),dto.login(),
					encryptedPassword,dto.userRole(),dto.role());
		}else {
			newUser = new Donator(dto.name(),dto.CPF(),dto.phoneNumber(),dto.email(),dto.login(),
					encryptedPassword,UserRoles.USER, dto.registration(),dto.donatorType());
		}

		this.userRepository.save(newUser);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/isValidToken")
	public ResponseEntity isValidToken(@RequestParam String token){
		try{
			if(!tokenService.isValidToken(token)) {
				return ResponseEntity.badRequest().body("token invalid!!");
			}
			else{
				return ResponseEntity.ok().build();
			}
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
	}
}