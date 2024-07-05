package ifpb.edu.br.pj.ifpbichos.business.service;

import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.decimal.DecimalMaxValidatorForLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.AuthenticationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.LoginResponseDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;

@Service
public class LoginService {
	
	 @Autowired
	 private  UserRepository userRepository;
	 
	 @Autowired
	 private AuthenticationManager authenticationManager;
	 
	 @Autowired
	 private TokenService tokenService;


	@Transactional
	public LoginResponseDTO login(AuthenticationDTO dto) throws Exception {
		if (!userRepository.existsByLogin(dto.login())) {
			throw new ObjectNotFoundException("Usuário inexistente!");
		}

		User user = (User) userRepository.findByLogin(dto.login());

		var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((User) auth.getPrincipal());

		return new LoginResponseDTO(token, dto.login(), user.getUserRole());
	}
}
