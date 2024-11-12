package pe.com.innovaviajes.data.ivjpadestinos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class IvJpaDestinosApplication {

	public static void main(String[] args) {
		SpringApplication.run(IvJpaDestinosApplication.class, args);
	}

}
