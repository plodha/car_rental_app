package themeansquare.controller;

import themeansquare.model.User;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.IRegistration;
import themeansquare.service.internal.Registration;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepository userRepository;
    private CustomerRepository customerRepository;

	@GetMapping("/register")
    public String register(
        @RequestParam(value = "username") String username, 
        @RequestParam(value = "password") String password, 
        @RequestParam(value = "firstName") String firstName, 
        @RequestParam(value = "lastName") String lastName,
        @RequestParam(value = "address") String address, 
        @RequestParam(value = "licenseNumber") String licenseNumber, 
        @RequestParam(value = "licenseExpDate") String licenseExpDate, 
        @RequestParam(value = "email") String email) {
        
        IRegistration reg = new Registration(username, password, firstName, lastName,
        address, licenseNumber, licenseExpDate, email, userRepository, customerRepository);
        String response = reg.register();

        return response;
	}

}





