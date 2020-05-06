package themeansquare.carrentalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"themeansquare.controller"})
@EnableJpaRepositories(basePackages = {"themeansquare.repository"})
@EntityScan("themeansquare.model")
public class CarRentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalServiceApplication.class, args);
	}

	///comment the below block for local development
	@Bean
	public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
					@Override
					public void addCorsMappings(CorsRegistry registry) {
							registry.addMapping("/**").allowedOrigins("*").allowedMethods("PUT", "DELETE", "GET", "POST");
					}
			};
	}
}





