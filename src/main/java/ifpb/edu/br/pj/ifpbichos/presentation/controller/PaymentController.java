package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import ifpb.edu.br.pj.ifpbichos.business.service.MercadoPagoService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.PaymentProcessingException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping
    public Preference createPayment(@RequestBody PaymentRequest paymentRequest) throws  PaymentProcessingException {
        return mercadoPagoService.createPayment(paymentRequest.getTitle(), paymentRequest.getDescription(),
                paymentRequest.getTransactionAmount(), paymentRequest.getInstallments(),paymentRequest.getCampaignId()
                ,paymentRequest.getUserLogin(), paymentRequest.getBackUrl(), paymentRequest.getIsDirected()

        );
    }

    @PatchMapping("/{preferenceId}")
    public Donation updatePayment(@PathVariable String preferenceId, @RequestBody PaymentRequest paymentRequest) throws Exception {
        System.out.println(paymentRequest.toString());
        return mercadoPagoService.updatePayment(preferenceId, paymentRequest.getPaymentId(), paymentRequest.getStatus(), paymentRequest.getPaymentType());
    }

    @GetMapping
    public Preference findPreference(@RequestParam String id) throws MPException, MPApiException {
        PreferenceClient client = new PreferenceClient();

        Preference preference = client.get(id);
        System.out.println(preference);
        return preference;
    }
}
@Data
class PaymentRequest {
    private String title;
    private String description;
    private BigDecimal transactionAmount;
    private int installments;
    private String paymentId;
    private String status;
    private String paymentType;
    private String preferenceId;
    private Long CampaignId;
    private String userLogin;
    private String backUrl;
    private Boolean isDirected;

}