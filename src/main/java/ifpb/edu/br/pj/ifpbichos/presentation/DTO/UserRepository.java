package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifpb.edu.br.pj.ifpbichos.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public Optional<User> findByLogin(String login);
}