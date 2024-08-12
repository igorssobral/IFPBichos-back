package ifpb.edu.br.pj.ifpbichos.model.repository;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation,Long> {

    public Optional<Donation> findByPreferenceId(String preferenceId);
}
