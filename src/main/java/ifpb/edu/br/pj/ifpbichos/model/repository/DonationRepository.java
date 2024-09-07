package ifpb.edu.br.pj.ifpbichos.model.repository;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation,Long> {

    Optional<Donation> findByPreferenceId(String preferenceId);

    List<Donation> findAllByPaymentTypeIgnoreCase(String paymentStatus);
}
