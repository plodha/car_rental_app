package themeansquare.service.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import themeansquare.model.Customer;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.ICustomer;

public class CustomerService implements ICustomer {

    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private EmployeeRepository employeeRepository;
    @Autowired 
    private CustomerRepository customerRepository;


	public CustomerService(UserRepository userRepository, EmployeeRepository employeeRepository,
			CustomerRepository customerRepository) {
                this .userRepository = userRepository;
                this.employeeRepository = employeeRepository;
                this.customerRepository = customerRepository;
	}

    @Override
    public String getAllCustomers() {
       
        HashMap<String, ArrayList<HashMap<String, String>>> response = new HashMap<String, ArrayList<HashMap<String, String>>>();
        ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();;
        
        Iterable<Customer> custItr = customerRepository.findAll();
        
        Iterator custIt = custItr.iterator();
        
        while (custIt.hasNext()) {
            HashMap<String, String> temp = new HashMap<>();
            Customer customer = (Customer) custIt.next();
            temp.put("username", customer.getUserId().getUsername());
            temp.put("user_id", customer.getUserId().getId() + "");
            results.add(temp);
        }
        
        ArrayList<HashMap<String, String>> status = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> statusTemp = new HashMap<>();
        
        statusTemp.put("status", "200");
        status.add(statusTemp);
        response.put("results", results);
        response.put("status", status);
        
        return convertMapListToJson(response);
    }
    

    public String convertMapToJson(HashMap<String, String> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResponse = "{\"Status\" : 400, \"error_message\" : \"convertMapToJson failed. Vehicle.java file\"}";
        }
    
        return jsonResponse;
    }

    public String convertMapListToJson(HashMap<String, ArrayList<HashMap<String, String>>> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResponse = "{\"Status\" : 400, \"error_message\" : \"convertMapToJson failed. Vehicle.java file\"}";
        }
    
        return jsonResponse;
    }

    @Override
    public Customer getCustomerInfo(String userId) {
        
        Iterable<Customer> custItr = customerRepository.findAll();
        
        Iterator custIt = custItr.iterator();
        
        while (custIt.hasNext()) {
            HashMap<String, String> temp = new HashMap<>();
            Customer customer = (Customer) custIt.next();
            if (customer.getUserId().getId() == Integer.parseInt(userId)) {
                return customer;
            }
        }

        return null;
    }
    
}