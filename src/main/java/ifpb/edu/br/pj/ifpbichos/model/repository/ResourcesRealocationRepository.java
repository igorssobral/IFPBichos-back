package ifpb.edu.br.pj.ifpbichos.model.repository;

import ifpb.edu.br.pj.ifpbichos.model.entity.ResourcesRealocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRealocationRepository extends JpaRepository<ResourcesRealocation, Long> {

}

