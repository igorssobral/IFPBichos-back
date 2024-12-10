package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;


@Service
public class PasswordResetService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public void sendPasswordResetEmail(String email) {
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }

        User user = userOpt.get();
        String token = generateToken();
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);

        user.setPasswordResetToken(token);
        user.setPasswordResetExpiration(expirationTime);
        userService.save(user);

        String resetLink = "http://localhost:5173/reset-password?token=" + token;
        emailService.sendEmail(email, "Recuperação de Senha", "Clique no link para redefinir sua senha: " + resetLink);
    }


    public void resetPassword(String token, String newPassword) {
        Optional<User> userOpt = userService.findByPasswordResetToken(token);

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Token inválido!");
        }

        User user = userOpt.get();
        if (user.getPasswordResetExpiration().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expirado!");
        }

        user.setPassword(encryptPassword(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetExpiration(null);
        userService.save(user);
    }

    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[24];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private String encryptPassword(String password) {

        return new BCryptPasswordEncoder().encode(password);
    }
}
