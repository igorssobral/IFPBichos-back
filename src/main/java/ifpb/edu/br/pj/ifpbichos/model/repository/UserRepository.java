package ifpb.edu.br.pj.ifpbichos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import ifpb.edu.br.pj.ifpbichos.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	UserDetails findByLogin(String login);
}
