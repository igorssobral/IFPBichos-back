package ifpb.edu.br.pj.ifpbichos.model.repository;

import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>{
	 Optional<Campaign> findByTitle(String title);
	 boolean existsByTitle(String title);

	 @Query("SELECT c FROM Campaign c ORDER BY c.start DESC")
	 List<Campaign> findAllByOrderByStartDesc();
	List<Campaign> findAllByCampaingStatusTrue();

	List<Campaign> findAllByCampaingStatusFalseAndCampaignActionResourceIsNull();

	List<Campaign> findAllByCampaingStatusFalse();


	List<Campaign> findAllByCampaingStatusFalseAndBalanceGreaterThan(BigDecimal balance);

	@Query("SELECT SUM(c.balance) FROM Campaign c")
	BigDecimal findTotalBalance();


}

