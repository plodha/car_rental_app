package themeansquare.service.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import themeansquare.model.Customer;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.ICustomer;
import themeansquare.service.IRegistration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class CustomerService implements ICustomer {

    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;

	public CustomerService(UserRepository userRepository, EmployeeRepository employeeRepository,
			CustomerRepository customerRepository, AddressRepository addressRepository) {
                this .userRepository = userRepository;
                this.employeeRepository = employeeRepository;
                this.customerRepository = customerRepository;
                this.addressRepository = addressRepository;
	}

    @Override
    public ArrayList<Customer> getAllCustomers() {

        Iterable<Customer> custItr = customerRepository.findAll();

        Iterator custIt = custItr.iterator();
        
        ArrayList<Customer> resultsFinal = new ArrayList<Customer>();

        while (custIt.hasNext()) {
            Customer customer = (Customer) custIt.next();
            resultsFinal.add(customer);
        }

        return resultsFinal;
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

            Customer customer = (Customer) custIt.next();
            if (customer.getUserId().getId() == Integer.parseInt(userId)) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public String updateCustomer(Customer customer) {

        HashMap<String, String> response = new HashMap<String, String>();

        Registration registrationService = new Registration(this.userRepository, this.customerRepository, this.addressRepository);
        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
        Customer inDBCustomer = optionalCustomer.get();

        if (!inDBCustomer.getUserId().getUsername().equals(customer.getUserId().getUsername())) {

            String username = customer.getUserId().getUsername();
            if (registrationService.checkIfUserExists(username)) {

                response.put("status", "400");
                response.put("message", "Username is taken");
                return convertMapToJson(response);
            }

        }

        if (!inDBCustomer.getEmail().equals(customer.getEmail())) {
            String email = customer.getEmail();
            if (registrationService.checkIfEmailExists(email)) {

                response.put("status", "400");
                response.put("message", "Email is taken");
                return convertMapToJson(response);
            }
        }

        customerRepository.save(customer);
        response.put("status", "200");
        return convertMapToJson(response);
    }

    @Override
    public String removeCustomer(String userId) {
        HashMap<String, String> response = new HashMap<String, String>();
        Customer toRemove = getCustomerInfo(userId);
        customerRepository.delete(toRemove);
        response.put("status", "200");
        return convertMapToJson(response);
    }

}
