package pe.com.innovaviajes.iveurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class IvEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IvEurekaServerApplication.class, args);
	}

}
