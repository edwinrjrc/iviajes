package pe.com.innovaviajes.web.ivserviceviajes.config;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    
    @Value("${JWT_SECRET}") 
    private String jwtSecret;
    
    public SecurityConfig() {
        System.out.println("***************************************************");
        System.out.println("INSTANCIANDO CONFIGURACIÓN DE SEGURIDAD - INNOVA VIAJES");
        System.out.println("***************************************************");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Cargando SecurityConfig - Secret disponible: {}", (jwtSecret != null));
        
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // IMPORTANTE: Quité "/api/viajes" porque el Gateway hace StripPrefix
                // Usamos hasAuthority("ROLE_ANONYMOUS") que coincide con tu token decodificado
                .requestMatchers("/destinoservice/**").hasAuthority("ROLE_ANONYMOUS")
                .requestMatchers("/viajeService/**").authenticated()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth -> oauth
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            );
        
        return http.build();
    }
    
    @Bean
    public JwtDecoder jwtDecoder() {
        // Asegúrate de que jwtSecret tenga al menos 32 caracteres para HS256
        SecretKeySpec secretKey = new SecretKeySpec(this.jwtSecret.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Este convertidor extrae los roles del campo "roles" de tu JSON del token
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix(""); // No añadimos SCOPE_, usamos el nombre tal cual
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles"); // El nombre del campo en tu token

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}