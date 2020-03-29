package themeansquare.controller;

import themeansquare.service.IRegistration;
import themeansquare.service.internal.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*
    Customer
    int _id;
    String name;
    String licenseNumber ;
    DateTimeFormatter registrationDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    DateTimeFormatter registrationEndDate  = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    boolean membershipStatus ;
    boolean verified ;
    DateTimeFormatter licenseExpDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    Address address ;
    String email;


*/


@RestController
public class RegistrationController {

	@GetMapping("/registration")
    public String registration(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "test", defaultValue = "test") String test) {
        IRegistration reg = new Registration();
        return String.format("Hello %s!", test);
	}

}





