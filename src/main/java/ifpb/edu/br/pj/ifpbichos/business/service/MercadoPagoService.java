package ifpb.edu.br.pj.ifpbichos.business.service;

import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donator;
import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonationPaymentStatus;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignRepository;
import ifpb.edu.br.pj.ifpbichos.model.repository.DonationRepository;
import ifpb.edu.br.pj.ifpbichos.model.repository.DonatorRepository;
import ifpb.edu.br.pj.ifpbichos.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MercadoPagoService {

    @Autowired
    private DonatorRepository donatorRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private UserRepository userRepository;

    public Preference createPayment(String title, String description, BigDecimal transactionAmount, Integer installments,Long campaignId, String userLogin)
            throws MPException, MPApiException {

        try {
            PreferenceClient client = new PreferenceClient();

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder().id("1234").title(title).description(description).categoryId("currency_id").quantity(1).currencyId("BRL").unitPrice(new BigDecimal(String.valueOf(transactionAmount))).build();

            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);
//
//        PreferenceFreeMethodRequest freeMethod =
//                PreferenceFreeMethodRequest.builder()
//                        .id(1L).build();
//        List<PreferenceFreeMethodRequest> freeMethodList = new ArrayList<>();
//        freeMethodList.add(freeMethod);
//
//        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
//        excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());
//
//        List<PreferencePaymentMethodRequest> excludedPaymentMethods = new ArrayList<>();
//        excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("").build());
            String successUrl = "http://localhost:5173/success";
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .backUrls(PreferenceBackUrlsRequest.builder()
                            .success("http://localhost:5173/campanhas")
                            .failure("http://localhost:5173/campanhas")
                            .pending("http://localhost:5173/pending")
                            .build()).differentialPricing(PreferenceDifferentialPricingRequest.builder()
                            .id(1L).build()).expires(false).items(items)
                    .marketplaceFee(new BigDecimal("0"))
                    .autoReturn("all")
                    .build();
//              .payer(
//                        PreferencePayerRequest.builder()
//                                .name("Test")
//                                .surname("User")
//                                .email("your_test_email@example.com")
//                                .phone(PhoneRequest.builder().areaCode("11").number("4444-4444").build())
//                                .identification(
//                                        IdentificationRequest.builder().type("CPF").number("19119119100").build())
//                                .build())
//                .autoReturn("all")
//                .binaryMode(true)
//                .externalReference("1643827245")
//                .marketplace("marketplace")
//                .notificationUrl("http://notificationurl.com")
//                .operationType("regular_payment")
//                .paymentMethods(
//                        PreferencePaymentMethodsRequest.builder()
//                                .defaultPaymentMethodId("master")
//                                .excludedPaymentTypes(excludedPaymentTypes)
//                                .excludedPaymentMethods(excludedPaymentMethods)
//                                .installments(5)
//                                .defaultInstallments(1)
//                                .build())
//                .shipments(
//                        PreferenceShipmentsRequest.builder()
//                                .mode("custom")
//                                .localPickup(false)
//                                .defaultShippingMethod(null)
//                                .freeMethods(freeMethodList)
//                                .cost(BigDecimal.TEN)
//                                .freeShipping(false)
//                                .dimensions("10x10x20,500")
//                                .build())
//                .statementDescriptor("Test Store")
//                .build();

            Preference preference = client.create(preferenceRequest);
            System.out.println(preference.getInitPoint());

            SaveDonation(preference.getId(), transactionAmount, campaignId, userLogin);

            return preference;
        } catch (MPException e) {
            throw new RuntimeException(e);
        } catch (MPApiException e) {
            throw new RuntimeException(e);
        }
    }

    public Donation updatePayment(String preferenceId,String paymentId,String status,String paymentType) throws Exception {
        Optional<Donation> donation = donationRepository.findByPreferenceId(preferenceId);
        Optional<Campaign> campaign = campaignRepository.findById(donation.get().getCampaign().getId());
        if(!donation.isPresent()){
           throw new Exception("Erro ao atualizar");

        }
        campaign.get().setBalance(campaign.get().getBalance().add(donation.get().getDonationValue()));

        campaignRepository.save(campaign.get());

        Donation donationUpdate = donation.get();
        donationUpdate.setPaymentId(paymentId);
        donationUpdate.setStatus(DonationPaymentStatus.APPROVED);
        donationUpdate.setPaymentType(paymentType);
        System.out.println(donationUpdate);
        return donationRepository.save(donationUpdate);
    }

    public void SaveDonation(String preferenceId, BigDecimal transactionAmount,Long campaignId, String userLogin) {

        User user = (User) userRepository.findByLogin(userLogin);
        System.out.println(user.getLogin());
        System.out.println(campaignId);
        Optional<Campaign> campaign = campaignRepository.findById(campaignId);
        Donation donation = new Donation();

        donation.setDate(LocalDateTime.now());
        donation.setCampaign(campaign.get());
        donation.setDirected(false);
        donation.setPreferenceId(preferenceId);
        donation.setDonationValue(transactionAmount);
        donation.setStatus(DonationPaymentStatus.PENDING);
        user.getDonations().add(donation);
        donation.setDonator(user);


        donationRepository.save(donation);
    }

}
