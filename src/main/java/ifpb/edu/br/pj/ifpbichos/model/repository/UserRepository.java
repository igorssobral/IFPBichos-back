package ifpb.edu.br.pj.ifpbichos.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


import ifpb.edu.br.pj.ifpbichos.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
	public UserDetails findByLogin(String login);
	Optional<User> findByEmail(String email);
	Optional<User> findByPasswordResetToken(String token);
	public boolean existsByLogin(String login);

	public boolean existsByCPF(String cpf);
	public boolean existsByPhoneNumber(String phoneNumber);
}
