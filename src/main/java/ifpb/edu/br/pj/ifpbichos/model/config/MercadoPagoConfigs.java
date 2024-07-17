package ifpb.edu.br.pj.ifpbichos.model.config;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MercadoPagoConfigs {

    @Value("${mercadopago.token}")
    private String accessToken;

    @PostConstruct
    public void init() {
       MercadoPagoConfig.setAccessToken(accessToken);

    }
}