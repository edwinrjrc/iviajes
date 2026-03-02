package pe.com.innovaviajes.ivgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@EnableZuulProxy
@Controller
@SpringBootApplication
public class IvGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(IvGatewayApplication.class, args);
	}
	
	/*@Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200"); // URL de tu Front-end
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }*/
	
	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    
	    // 1. Permitir que Angular envíe credenciales (cookies, auth headers)
	    config.setAllowCredentials(true);
	    
	    // 2. Definir exactamente quién puede llamar a la API
	    // Usa localhost o edwinlaptop según cómo abras tu Angular
	    config.addAllowedOrigin("http://localhost:4200");
	    config.addAllowedOrigin("http://edwinlaptop:4200");
	    
	    // 3. Permitir todos los encabezados (Content-Type, Authorization, etc.)
	    config.addAllowedHeader("*");
	    
	    // 4. Permitir todos los métodos HTTP
	    config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("POST");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("DELETE");
	    
	    // Aplicar a todas las rutas del Gateway
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}

}
