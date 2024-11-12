package pe.com.innovaviajes.ivgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;


@EnableZuulProxy
@Controller
@SpringBootApplication
public class IvGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(IvGatewayApplication.class, args);
	}

}
