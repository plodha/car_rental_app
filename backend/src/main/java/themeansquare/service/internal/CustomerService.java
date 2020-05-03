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
    public String getAllCustomers() {

        HashMap<String, ArrayList<HashMap<String, String>>> response = new HashMap<String, ArrayList<HashMap<String, String>>>();
        ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();;

        Iterable<Customer> custItr = customerRepository.findAll();

        Iterator custIt = custItr.iterator();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        while (custIt.hasNext()) {
            HashMap<String, String> temp = new HashMap<>();
            Customer customer = (Customer) custIt.next();
            temp.put("FirstName", customer.getFirstName());
            temp.put("LastName", customer.getLastName());
            temp.put("Street", customer.getAddress().getStreet());
            temp.put("LicenseNumber", customer.getLicenseNumber());
            //Date s = customer.getMembershipStartDate();
            temp.put("Start Date", dateFormat.format(customer.getMembershipStartDate()));
            //s = customer.getMembershipEndDate();
            temp.put("End Date", dateFormat.format(customer.getMembershipEndDate()));
            temp.put("Email", customer.getEmail());
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
