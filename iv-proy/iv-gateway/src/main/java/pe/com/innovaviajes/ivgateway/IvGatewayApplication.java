package pe.com.innovaviajes.ivgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class IvGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IvGatewayApplication.class, args);
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        final CorsConfiguration config = new CorsConfiguration();

        // 1. Permitir credenciales para Angular
        config.setAllowCredentials(true);

        // 2. Orígenes permitidos (puedes usar setAllowedOriginPatterns si tienes problemas)
        config.setAllowedOrigins(Arrays.asList(
            "http://localhost:4200",
            "http://edwinlaptop:4200"
        ));

        // 3. Encabezados permitidos
        config.addAllowedHeader("*");

        // 4. Métodos permitidos
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}