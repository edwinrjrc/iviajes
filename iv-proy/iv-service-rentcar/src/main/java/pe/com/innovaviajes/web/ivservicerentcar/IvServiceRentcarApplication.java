package pe.com.innovaviajes.web.ivservicerentcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class IvServiceRentcarApplication {

	public static void main(String[] args) {
		SpringApplication.run(IvServiceRentcarApplication.class, args);
	}

}
