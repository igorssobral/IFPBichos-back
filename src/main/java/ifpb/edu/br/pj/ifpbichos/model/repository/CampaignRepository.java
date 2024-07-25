package ifpb.edu.br.pj.ifpbichos.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;


public interface CampaignRepository extends JpaRepository<Campaign, Long>{
	 Optional<Campaign> findByTitle(String title);
	 boolean existsByTitle(String title);
	List<Campaign> findAllByOrderByStartAsc();

}

