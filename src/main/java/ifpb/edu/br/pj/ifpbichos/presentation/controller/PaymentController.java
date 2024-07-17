package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import ifpb.edu.br.pj.ifpbichos.business.service.MercadoPagoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping
    public Preference createPayment(@RequestBody PaymentRequest paymentRequest) throws MPException, MPApiException, MPApiException {
        return mercadoPagoService.createPayment(
                paymentRequest.getDescription(),
                paymentRequest.getTransactionAmount(),
                paymentRequest.getToken(),
                paymentRequest.getPaymentMethodId(),
                paymentRequest.getInstallments(),
                paymentRequest.getEmail()
        );
    }
}
@Data
class PaymentRequest {
    private String description;
    private BigDecimal transactionAmount;
    private String token;
    private String paymentMethodId;
    private int installments;
    private String email;

    // Getters and setters
}