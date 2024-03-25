package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.model.entity.ComissionMember;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    public UserRegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User registerUser(UserRegistrationDTO dto) {
        System.out.println(dto);
        if (userRepository.existsByLogin(dto.login())) {
            throw new IllegalArgumentException("Login already exists");
        }

        if (userRepository.existsByCPF(dto.CPF())) {
            throw new IllegalArgumentException("CPF already registered");
        }

        if (dto.name() == null || dto.name().isEmpty() ||
                dto.phoneNumber() == null || dto.phoneNumber().isEmpty() ||
                dto.email() == null || dto.email().isEmpty() ||
                dto.login() == null || dto.login().isEmpty() ||
                dto.password() == null || dto.password().isEmpty()) {
            throw new IllegalArgumentException("Missing required fields");
        }


        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User newUser;
        if (dto.userRole() == UserRoles.ADMIN) {
            newUser = new ComissionMember(dto.name(), dto.CPF(), dto.phoneNumber(), dto.email(), dto.login(),
                    encryptedPassword, dto.userRole(), dto.role());
        } else {
            newUser = new Donator(dto.name(), dto.CPF(), dto.phoneNumber(), dto.email(), dto.login(),
                    encryptedPassword, UserRoles.USER, dto.registration(), dto.donatorType());
        }

       return userRepository.save(newUser);
    }
}
