package ifpb.edu.br.pj.ifpbichos;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class IfpbichosApplication implements WebMvcConfigurer, CommandLineRunner {

	public static void main(String[] args){

		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
		System.setProperty("MERCADO_PAGO_API_TEST_KEY", dotenv.get("MERCADO_PAGO_API_TEST_KEY"));
		SpringApplication.run(IfpbichosApplication.class, args);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");
	}

	@Override
	public void run(String... args) throws Exception {

	}
		// TODO Auto-generated method stub

	}

