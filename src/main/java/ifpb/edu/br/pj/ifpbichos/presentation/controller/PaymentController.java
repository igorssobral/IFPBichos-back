package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import ifpb.edu.br.pj.ifpbichos.business.service.DonationService;
import ifpb.edu.br.pj.ifpbichos.business.service.MercadoPagoService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.DonationManual;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.PaymentRequestDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.PaymentProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @Autowired
    private DonationService donationService;

    @PostMapping
    public Preference createPayment(@RequestBody @Valid PaymentRequestDTO paymentRequest) throws  PaymentProcessingException {
        return mercadoPagoService.createPayment(paymentRequest.getTitle(),
                paymentRequest.getTransactionAmount(), paymentRequest.getInstallments(),paymentRequest.getCampaignId()
                ,paymentRequest.getUserLogin(), paymentRequest.getBackUrl(), paymentRequest.getIsDirected()

        );
    }

    @PostMapping("/donation-manual")
    public ResponseEntity<?> donationManual(@RequestBody @Valid DonationManual donationManual) {
        try {
            donationService.saveManualDonation(donationManual);
            return ResponseEntity.ok().body("Doação registrada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao regitrar Doação!");
        }
    }

    @PatchMapping("/{preferenceId}")
    public Donation updatePayment(@PathVariable String preferenceId, @RequestBody PaymentRequestDTO paymentRequest) throws Exception {
        return mercadoPagoService.updatePayment(preferenceId, paymentRequest.getPaymentId(), paymentRequest.getStatus(), paymentRequest.getPaymentType());
    }

    @GetMapping
    public Preference findPreference(@RequestParam String id) throws MPException, MPApiException {
        PreferenceClient client = new PreferenceClient();

        Preference preference = client.get(id);
        return preference;
    }


}
