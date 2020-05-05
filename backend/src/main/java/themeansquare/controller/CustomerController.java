package themeansquare.controller;

import themeansquare.model.Customer;
import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.ICustomer;
import themeansquare.service.IRegistration;
import themeansquare.service.IUser;
import themeansquare.service.internal.CustomerService;
import themeansquare.service.IRegistration;
import themeansquare.service.IUser;
import themeansquare.service.internal.Registration;
import themeansquare.service.internal.UserAuth;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class CustomerController {

    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private EmployeeRepository employeeRepository;
    @Autowired 
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    /**
        Use case:
            As an admin, I want to get all the users and be able to do some actions on them.
        
        Response:
            Success:
                {
                    results : [
                        {

                        }
                    ]
                    status : [
                        {
                            status : 
                        }
                    ]
                }
                
                For status, just index to zero, there will only be one item in there
            Failure:
                No failures, empty will return empty with 200
     */

    @GetMapping("/getAllCustomers")
    public ArrayList<Customer> getAllCustomers() {
        ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        
        return customerService.getAllCustomers();
    }
    
    /**
        Use case:
            As an admin or user I want to get my profile information
        
        Response:
            Success:
                {
                    "address": {
                        "id": 1,
                        "state": "ca",
                        "city": "random city",
                        "street": "random street",
                        "zipCode": 94086
                    },
                    "id": 1,
                    "userId": {
                        "id": 1,
                        "password": "notsecure",
                        "username": "wqureshi1"
                    },
                    "firstName": "Wasae",
                    "lastName": "Qureshi",
                    "licenseNumber": "747324",
                    "licenseExpDate": "1994-05-22",
                    "membershipStartDate": "2020-05-01",
                    "membershipEndDate": "2021-05-01",
                    "email": "different@gmail.com"
                    
                }
            
            Use status from http request
            Failure:
                No failures possible
     */
    
    @GetMapping("/getCustomerInfo")
    public Customer getCustomerInfo(@RequestParam(value = "userId") String userId) {
        ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        
        return customerService.getCustomerInfo(userId);
    }
    
     /**
        Use case:
            As a user, I can update my information.
            You can only call this if you called getCustomerInfo before
            You will need to return same params as before and updated values
        
        Response:
            Success

            Failure:
                No failures possible
     */

    @PutMapping("/updateCustomer")
    public String updateCustomer(@RequestBody Customer customer) {
        
        ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);        
        
        return customerService.updateCustomer(customer);
    }

    /**
        Use case:
            Admin can delete the user. They need to only pass id of user
        
        Response:
            Success:
                response.put("status", "200");
            Failure:
                No failures possible
     */

    @DeleteMapping("/removeCustomer")
    public String removeCustomer(@RequestParam(value = "userId") String userId) {
        ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        
        return customerService.removeCustomer(userId);
    }

}





