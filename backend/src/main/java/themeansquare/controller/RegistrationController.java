package themeansquare.controller;

import themeansquare.service.IRegistration;
import themeansquare.service.internal.Registration;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
    Customer
    *int _id;
    String name;
    String licenseNumber ;
    *DateTimeFormatter registrationDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    *DateTimeFormatter registrationEndDate  = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    *boolean membershipStatus ;
    *boolean verified ;
    DateTimeFormatter licenseExpDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    Address address ;
    String email;
*/

@RestController
public class RegistrationController {

	@GetMapping("/registration")
    public String registration(@RequestParam(value = "name") String name, @RequestParam(value = "licenseNumber") String licenseNumber,
         @RequestParam(value = "licenseExpDate") String licenseExpDate, @RequestParam(value = "address") String address,
         @RequestParam(value = "email") String email) {
        
        HashMap<String, String> params = new HashMap<String, String>();

        IRegistration reg = new Registration(params);

        return String.format("Hello %s!", test);
	}

}





