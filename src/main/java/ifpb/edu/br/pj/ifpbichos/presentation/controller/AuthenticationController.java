package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import ifpb.edu.br.pj.ifpbichos.business.service.LoginService;
import ifpb.edu.br.pj.ifpbichos.business.service.PasswordResetService;
import ifpb.edu.br.pj.ifpbichos.business.service.TokenService;
import ifpb.edu.br.pj.ifpbichos.business.service.UserRegistrationService;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.AuthenticationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.ForgotPasswordRequest;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.ResetPasswordRequest;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.UserRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	private final TokenService tokenService;
	private final UserRegistrationService userRegistrationService;
	private final LoginService loginService;
	private final PasswordResetService passwordResetService;


	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager,
                                    TokenService tokenService,
                                    UserRegistrationService userRegistrationService,
                                    LoginService loginService, PasswordResetService passwordResetService) {
		this.tokenService = tokenService;
		this.userRegistrationService = userRegistrationService;
		this.loginService = loginService;
        this.passwordResetService = passwordResetService;
    }

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO dto) {
		try {
			return ResponseEntity.ok(loginService.login(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/userRegistration")
	public ResponseEntity<?> userRegistration(@RequestBody @Valid UserRegistrationDTO dto) {
		try {
			userRegistrationService.registerUser(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.ok().build();
	}

	@PostMapping("/isValidToken")
	public ResponseEntity<Boolean> isValidToken(@RequestParam String token) {
		try {
			boolean isValid = tokenService.isValidToken(token);
			return ResponseEntity.ok(isValid);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(false);
		}
	}


	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
		try {
			passwordResetService.sendPasswordResetEmail(request.getEmail());
			return ResponseEntity.ok("E-mail de recuperação enviado com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erro ao enviar o e-mail de recuperação: " + e.getMessage());
		}
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
		try {
			passwordResetService.resetPassword(request.getToken(), request.getNewPassword());
			return ResponseEntity.ok("Senha redefinida com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Erro ao redefinir a senha: " + e.getMessage());
		}
	}





}