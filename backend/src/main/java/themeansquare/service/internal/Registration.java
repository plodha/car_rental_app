package themeansquare.service.internal;

import themeansquare.model.Customer;
import themeansquare.model.User;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.IRegistration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Registration implements IRegistration {

    private UserRepository userRepository;
    private CustomerRepository customerRepository;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String licenseNumber;
    private String licenseExpDate;
    private String email;

    public Registration(String username, String password, String firstName, String lastName,
    String address, String licenseNumber, String licenseExpDate, String email, UserRepository userRepository, CustomerRepository customerRepository) {
       
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.licenseNumber = licenseNumber;
        this.licenseExpDate = licenseExpDate;
        this.email = email;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    public String register() {
        HashMap<String, String> response = new HashMap<>();
        response.put("isUserNameTaken", "true");
        response.put("status", "400");

        if (insertUser()) {
            response.remove("isUserNameTaken");
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setLicenseNumber(licenseNumber);
            // Change to datatime
            // customer.setLicenseExpDate(licenseExpDate);
            // Add email check
            customer.setEmail(email);
            // Checking start and end date for membership, setting those to now and now + 1
            // Creating Customer
            response.put("status", "200");
        }

        return this.convertMapToJson(response);
    }

    public boolean insertUser()  {
        
        User newUser = new User();
        Iterable<User> itr = userRepository.findAll();
        
        Iterator it = itr.iterator();
        
        while (it.hasNext()) {
            User user = (User) it.next();
            System.out.println(user.getUsername());
            
            if (user.getUsername().equals(this.username)) {
                return false;
            }
        }

        newUser.setUsername(this.username);
        newUser.setPassword(this.password);
        userRepository.save(newUser);
        System.out.println(newUser.getId());
        
        return true;
    }

    public boolean isValidEmail() {
        
        return false;
    }

    public boolean isUserNameTaken() {
        
        return false;
    }

    public String convertMapToJson(HashMap<String, String> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResponse = "{\"Status\" : 400, \"error_message\" : \"convertMapToJson failed. Registration.java file\"}";
        }
    
        return jsonResponse;
    }
}
