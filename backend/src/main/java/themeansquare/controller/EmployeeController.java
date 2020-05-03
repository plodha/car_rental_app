package themeansquare.controller;

import themeansquare.model.Customer;
import themeansquare.model.Employee;
import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.IEmployee;
import themeansquare.service.internal.EmployeeService;

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
    *DateTimeFormatter EmployeeDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    *DateTimeFormatter EmployeeEndDate  = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    *boolean membershipStatus ;
    *boolean verified ;
    DateTimeFormatter licenseExpDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    Address address ;
    String email;
*/

@RestController
public class EmployeeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
        Input must be in this format (fields can be missing for some like id):
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
                "creditCard" : "1234567890123456"	
                "creditCardExpDate" : "2022-05-01",
                "email": "different@gmail.com"
            }
        Use case:
            This is used by the user to create a Employee. After success, they will be rerouted to login.
        
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
    
	@PostMapping("/createEmployee")
    public String createEmployee(@RequestBody Employee employee) throws Exception {
        
        IEmployee reg = new EmployeeService(employeeRepository);
        String response = reg.createEmployee(employee);

        return response;
	}

}





