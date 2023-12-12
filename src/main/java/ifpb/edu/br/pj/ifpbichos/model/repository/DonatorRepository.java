package ifpb.edu.br.pj.ifpbichos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;

@Repository
public interface DonatorRepository extends JpaRepository<Donator, Integer>{
	
}
