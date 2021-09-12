package co.com.poli.servicemovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceMoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMoviesApplication.class, args);
	}

}
