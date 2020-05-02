package themeansquare.controller;

import themeansquare.model.Customer;
import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.IMembership;
import themeansquare.service.IRegistration;
import themeansquare.service.internal.MembershipService;
import themeansquare.service.internal.Registration;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class MembershipController {

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
    
	@PutMapping("/cancelMembership")
    public String cancelMembership(@RequestParam(value = "userId") String userId) throws Exception {
        
        IMembership reg = new MembershipService(userRepository, customerRepository, addressRepository);

        return reg.cancelMembership(userId);
	}

}





