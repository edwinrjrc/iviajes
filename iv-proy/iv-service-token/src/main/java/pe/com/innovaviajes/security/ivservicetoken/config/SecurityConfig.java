/**
 * 
 */
package pe.com.innovaviajes.security.ivservicetoken.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Edwin
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// 1. Deshabilitar CSRF para poder usar POST desde cURL
				.csrf(csrf -> csrf.disable())

				// 2. Definir que NO guardaremos sesión (Stateless)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// 3. Autorizar el endpoint específico
				.authorizeHttpRequests(
						auth -> auth.requestMatchers(org.springframework.http.HttpMethod.GET, "/auth/anonymous")
								.permitAll().anyRequest().authenticated());

		return http.build();
	}

}
