package themeansquare.carrentalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"themeansquare.controller", "themeansquare.repository"})
public class CarRentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalServiceApplication.class, args);
	}

}





