package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifpb.edu.br.pj.ifpbichos.model.entity.ComissionMember;
import ifpb.edu.br.pj.ifpbichos.model.repository.ComissionMemberRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.AuthenticationDTO;



@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthenticationDTO dto) {
		var userNamePassword = new UsernamePasswordAuthenticationToken(dto.getName(), dto.getPassword());
		var auth = this.authenticationManager.authenticate(userNamePassword);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/registration")
	public ResponseEntity registration(@RequestBody AuthenticationDTO dto) {
		return null;
	}
	
}
