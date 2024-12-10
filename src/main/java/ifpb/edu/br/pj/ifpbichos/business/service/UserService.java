package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByPasswordResetToken(String token) {
        return userRepository.findByPasswordResetToken(token);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
