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

@RestController
public class UserAuthController {

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
    
    /**
        Use case:
            This is to make sure that the user is authenticated. They need to provide the correct credentials
        
        Body Param Example:
            {
                "username" : "exampleUser",
                "password" : "examplePassword"
            }

        Response:
            Success:
                response.put("status", "200");
                response.remove("message");
                response.put("role", getRole(user.getId()));
                response.put("username", user.getUsername());
                response.put("id", user.getId() + "");
            Failure:
                response.put("status", "400");
                response.put("message", "Some message on why it failed");
     */

    @PostMapping("/auth")
    public String auth(@RequestBody User user) {
        IUser userAuth = new UserAuth(userRepository, employeeRepository, customerRepository);
        
        return userAuth.isValidCredentials(user);
    }

}





