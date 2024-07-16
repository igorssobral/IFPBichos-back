package ifpb.edu.br.pj.ifpbichos.model.config;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MercadoPagoConfiguration {

    @Value("${mercadopago.token}")
    private static String accessToken;
    private static final String baseUrl = MercadoPagoConfig.BASE_URL;
    
    @Bean
    public void initializeMercadoPago() {
        MercadoPagoConfig.setAccessToken(accessToken);
        
    }
    
    public static void main(String[] args) {
			System.out.println(accessToken);
			System.out.println(baseUrl);
		
	}
}


