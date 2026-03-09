package pe.com.innovaviajes.ivgateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class IvGatewayApplication {
	
	private static final Logger log = LoggerFactory.getLogger(IvGatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IvGatewayApplication.class, args);
    }
    
    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            log.info(">>> GATEWAY - Ruta solicitada: {}", exchange.getRequest().getPath());
            log.info(">>> GATEWAY - Token detectado: {}", 
                exchange.getRequest().getHeaders().getFirst("Authorization"));
            
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info(">>> GATEWAY - Respuesta enviada con estado: {}", 
                    exchange.getResponse().getStatusCode());
            }));
        };
    }
}