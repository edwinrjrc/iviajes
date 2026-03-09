package pe.com.innovaviajes.config.ivconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // Esta activa el servidor
@SpringBootApplication
public class IvConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IvConfigServerApplication.class, args);
	}

}
