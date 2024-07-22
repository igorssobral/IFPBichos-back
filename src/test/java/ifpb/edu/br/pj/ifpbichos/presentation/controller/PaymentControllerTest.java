package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import ifpb.edu.br.pj.ifpbichos.business.service.MercadoPagoService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.PaymentRequestDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.PaymentProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;

public class PaymentControllerTest {

    @Mock
    private MercadoPagoService mercadoPagoService;

    @InjectMocks
    private PaymentController paymentController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePayment() throws PaymentProcessingException {
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
        paymentRequest.setTitle("Title");
        paymentRequest.setDescription("Description");
        paymentRequest.setTransactionAmount(BigDecimal.valueOf(100));
        paymentRequest.setInstallments(1);
        paymentRequest.setCampaignId(1L);
        paymentRequest.setUserLogin("userLogin");
        paymentRequest.setBackUrl("http://back.url");
        paymentRequest.setIsDirected(true);

        Preference preference = new Preference();
        when(mercadoPagoService.createPayment(anyString(), anyString(), any(), anyInt(), anyLong(), anyString(), anyString(), anyBoolean())).thenReturn(preference);

        Preference result = paymentController.createPayment(paymentRequest);
        assertNotNull(result);
        verify(mercadoPagoService, times(1)).createPayment(anyString(), anyString(), any(), anyInt(), anyLong(), anyString(), anyString(), anyBoolean());
    }

    @Test
    public void testUpdatePayment() throws Exception {
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
        paymentRequest.setPaymentId("paymentId");
        paymentRequest.setStatus("approved");
        paymentRequest.setPaymentType("credit_card");

        Donation donation = new Donation();
        when(mercadoPagoService.updatePayment(anyString(), anyString(), anyString(), anyString())).thenReturn(donation);

        Donation result = paymentController.updatePayment("preferenceId", paymentRequest);
        assertNotNull(result);
        verify(mercadoPagoService, times(1)).updatePayment(anyString(), anyString(), anyString(), anyString());
    }


}
