package themeansquare.controller;

import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.IRegistration;
import themeansquare.service.internal.Registration;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    
    /**
        Use case:
            This is used by the user to create a registration. After success, they will be rerouted to login.
        
        Response:
            Success:
                response.put("username", this.username);
                response.put("role", "Customer");
                response.put("id", customer.getUserId().getId() + "");
                response.put("status", "200");
            Failure:
                response.put("message", "username is taken");
                response.put("status", "400");
     */
    
	@PostMapping("/register")
    public String register(
        @RequestParam(value = "username") String username, 
        @RequestParam(value = "password") String password, 
        @RequestParam(value = "firstName") String firstName, 
        @RequestParam(value = "lastName") String lastName,
        @RequestParam(value = "street") String street,
        @RequestParam(value = "city") String city, 
        @RequestParam(value = "state") String state, 
        @RequestParam(value = "zipcode") String zipcode, 
        @RequestParam(value = "licenseNumber") String licenseNumber, 
        @RequestParam(value = "licenseExpDate") String licenseExpDate, 
        @RequestParam(value = "email") String email) throws Exception {
        
        IRegistration reg = new Registration(username, password, firstName, lastName,
        street, city, state, zipcode, licenseNumber, licenseExpDate, email, 
        userRepository, customerRepository, addressRepository);
        String response = reg.register();

        return response;
	}

}





