package ifpb.edu.br.pj.ifpbichos.model.repository;

import ifpb.edu.br.pj.ifpbichos.model.entity.UndirectedBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UndirectedBalanceRepository extends JpaRepository<UndirectedBalance, Long>{

}

