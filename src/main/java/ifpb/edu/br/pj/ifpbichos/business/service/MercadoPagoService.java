package ifpb.edu.br.pj.ifpbichos.business.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MercadoPagoService {

    public Preference createPayment(String description, BigDecimal transactionAmount, String token,
                                    String paymentMethodId, int installments, String email)
            throws MPException, MPApiException {

        PreferenceClient client = new PreferenceClient();

        PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                        .id("1234")
                        .title("Campanha teste")
                        .description(description)
                        .categoryId("currency_id")
                        .quantity(1)
                        .currencyId("BRL")
                        .unitPrice(new BigDecimal(String.valueOf(transactionAmount)))
                        .build();

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

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .backUrls(
                        PreferenceBackUrlsRequest.builder()
                                .success("http://test.com/success")
                                .failure("http://test.com/failure")
                                .pending("http://test.com/pending")
                                .build())
                .differentialPricing(
                        PreferenceDifferentialPricingRequest.builder()
                                .id(1L)
                                .build())
                .expires(false)
                .items(items)
                .marketplaceFee(new BigDecimal("0"))
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
        return preference;
    }


}
