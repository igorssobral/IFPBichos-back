package ifpb.edu.br.pj.ifpbichos.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;


@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer>{
	public Optional<Campaign> findByTitle(String title);
	public boolean existsByTitle(String title);
}

