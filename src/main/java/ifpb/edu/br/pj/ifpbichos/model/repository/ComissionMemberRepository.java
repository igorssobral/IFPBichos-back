package ifpb.edu.br.pj.ifpbichos.model.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifpb.edu.br.pj.ifpbichos.model.entity.ComissionMember;

@Repository
public interface ComissionMemberRepository extends JpaRepository<ComissionMember, Integer>{
	public boolean existsByLogin(String Login);
}