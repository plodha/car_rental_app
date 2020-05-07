package themeansquare.service.internal;

import java.util.HashMap;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import themeansquare.model.Customer;
import themeansquare.model.Employee;
import themeansquare.model.User;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.IUser;

public class UserAuth implements IUser {

    private UserRepository userRepository; 
    private EmployeeRepository employeeRepository; 
    private CustomerRepository customerRepository;

    private String username;
    private String password;

    public UserAuth(UserRepository userRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this .userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }
    
    public UserAuth(String username, String password,UserRepository userRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository) {

        this.username = username;
        this.password = password;
        this .userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    public String getRole(int id) {
        
        CustomerRoleHandler crh = new CustomerRoleHandler();
        EmployeeRoleHandler erh = new EmployeeRoleHandler();
        NoRoleHandler nrh = new NoRoleHandler();

        crh.setRepo(customerRepository);
        erh.setRepo(employeeRepository);
        
        crh.setSuccessor(erh);
        erh.setSuccessor(nrh);

        return crh.handle(id);

    }

    
    

    public String isValidCredentials(User userCheck) {
       
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");
        response.put("message", "User does not exist");
        
        Iterable<User> itr = userRepository.findAll();

        Iterator it = itr.iterator();
        
        while (it.hasNext()) {
            User user = (User) it.next();
            System.out.println(user.getUsername());
            
            if (user.getUsername().equals(userCheck.getUsername())) {
                if (user.getPassword().equals(userCheck.getPassword()))
                {

                    response.put("status", "200");
                    response.remove("message");
                    response.put("role", getRole(user.getId()));
                    response.put("username", user.getUsername());
                    response.put("id", user.getId() + "");
                    return convertMapToJson(response);
                }
                else 
                {
                    response.put("message", "Invalid password");
                    return convertMapToJson(response);
                }
                
            }
        }

        return convertMapToJson(response);

    }

    public String isValidCredentialsNew() {
       
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");
        response.put("message", "User does not exist");
        Iterable<User> itr = userRepository.findAll();

        Iterator it = itr.iterator();
        
        while (it.hasNext()) {
            User user = (User) it.next();
            System.out.println(user.getUsername());
            
            if (user.getUsername().equals(this.username)) {
                if (user.getPassword().equals(this.password))
                {

                    response.put("status", "200");
                    response.remove("message");
                    response.put("role", getRole(user.getId()));
                    response.put("username", user.getUsername());
                    response.put("id", user.getId() + "");
                    return convertMapToJson(response);
                }
                else 
                {
                    response.put("message", "Invalid password");
                    return convertMapToJson(response);
                }
                
            }
        }

        return convertMapToJson(response);

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