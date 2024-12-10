package ifpb.edu.br.pj.ifpbichos.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	@Autowired
	SecurityFilter securityFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return  httpSecurity
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/ws/notifications").permitAll() // Permitir acesso ao WebSocket
						.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/auth/userRegistration").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/comissionMember/manual-donations").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/action-campaign").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/api/action-campaign").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/campaign").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/api/campaign").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/campaign/{id}").permitAll()
						.requestMatchers(HttpMethod.PUT, "/api/campaign/{id}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api/campaign/{id}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/api/campaign/finishedBalance").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/auth/isValidToken").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/auth/forgot-password").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/auth/reset-password").permitAll()
						.requestMatchers(HttpMethod.POST, "/payments").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/payments/donation-manual").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/resources-realocations").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT,"/payments/{preferenceId}").permitAll()
						.anyRequest().authenticated()
				)
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}