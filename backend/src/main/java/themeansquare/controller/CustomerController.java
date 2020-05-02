package themeansquare.controller;

import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.IRegistration;
import themeansquare.service.IUser;
import themeansquare.service.internal.Registration;
import themeansquare.service.internal.UserAuth;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CustomerController {

    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private EmployeeRepository employeeRepository;
    @Autowired 
    private CustomerRepository customerRepository;

    /**
     * 
        {
            "username" : "wqureshi",
            "password" : "password"
        }
     * @param user
     * @return
     */

    @PostMapping("/getAllCustomers")
    public String getAllCustomers(@RequestBody User user) {
        IUser userAuth = new UserAuth(userRepository, employeeRepository, customerRepository);
        
        return userAuth.isValidCredentials(user);
    }
    
    @PostMapping("/updateCustomer")
    public String updateCustomer(@RequestBody User user) {
        IUser userAuth = new UserAuth(userRepository, employeeRepository, customerRepository);
        
        return userAuth.isValidCredentials(user);
    }

    @PostMapping("/removeCustomer")
    public String removeCustomer(@RequestBody User user) {
        IUser userAuth = new UserAuth(userRepository, employeeRepository, customerRepository);
        
        return userAuth.isValidCredentials(user);
    }

}





