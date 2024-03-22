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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){

		var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((User) auth.getPrincipal());

		return ResponseEntity.ok(new LoginResponseDTO(token));

	}

	@PostMapping("/userRegistration")
	public ResponseEntity userRegistration(@RequestBody UserRegistrationDTO dto) {

		if(this.userRepository.findByLogin(dto.login())!= null) {

			return ResponseEntity.badRequest().build();
		}

		User newUser = null;

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

}