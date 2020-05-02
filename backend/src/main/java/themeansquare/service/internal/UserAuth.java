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

    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private EmployeeRepository employeeRepository;
    @Autowired 
    private CustomerRepository customerRepository;



    public UserAuth(UserRepository userRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this .userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
	}

    public String getRole(int id) {

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");
        response.put("message", "User does not exist");
        
        Iterable<Employee> empaItr = employeeRepository.findAll();

        Iterator empIt = empaItr.iterator();
        
        while (empIt.hasNext()) {
            Employee employee = (Employee) empIt.next();
            System.out.println(employee.getId());
            System.out.println(id);
            if (employee.getUserId().getId() == id) {
                return "Employee";
            }
        }

        Iterable<Customer> custItr = customerRepository.findAll();

        Iterator custIt = custItr.iterator();
        
        while (custIt.hasNext()) {
            Customer customer = (Customer) custIt.next();
            System.out.println(customer.getId());
            System.out.println(id);
            if (customer.getUserId().getId() == id) {
                return "Customer";
            }
        }

        return "No role :(";

    }

	@Override
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