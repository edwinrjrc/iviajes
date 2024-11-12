package pe.com.innovaviajes.web.ivserviceviajes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class IvServiceViajesApplication {

	public static void main(String[] args) {
		SpringApplication.run(IvServiceViajesApplication.class, args);
	}

}
