package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import ifpb.edu.br.pj.ifpbichos.model.entity.UndirectedBalance;
import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonationPaymentStatus;
import ifpb.edu.br.pj.ifpbichos.model.repository.DonationRepository;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.DonationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.DonationManual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationService {
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final UndirectedBalanceService undirectedBalanceService;

    @Autowired
    public DonationService(DonationRepository donationRepository, UserRepository userRepository,UndirectedBalanceService undirectedBalanceService ) {
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.undirectedBalanceService = undirectedBalanceService;
    }

    public void saveManualDonation(DonationManual donationManual) throws Exception {

        User user = (User) userRepository.findByLogin(donationManual.userLogin());

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Donation donation = new Donation();

        donation.setDate(LocalDateTime.now());
        donation.setTitle(donationManual.title());
        donation.setDescription(donationManual.description());
        donation.setDirected(false);
        donation.setPaymentType("DINHEIRO FISICO");
        donation.setDonationValue(donationManual.donationValue());
        donation.setPreferenceId("DOAÇÃO AVULSA");
        donation.setStatus(DonationPaymentStatus.APPROVED);
        donation.setDonator(user);

        user.getDonations().add(donation);
        UndirectedBalance undirectedBalance = undirectedBalanceService.getCurrentBalanceEntity();
        undirectedBalanceService.update(undirectedBalance, donation.getDonationValue());

        donationRepository.save(donation);
    }

    public List<DonationDTO> findAllDonationsManual() {
        System.out.println("chegou aqui");
        List<Donation> donations = donationRepository.findAllByPaymentTypeIgnoreCase("DINHEIRO FISICO");
        System.out.println(donations.size());

        return donations.stream().map(DonationDTO::new).toList();
    }
}
