package themeansquare.service.internal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import themeansquare.model.Customer;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.IMembership;

public class MembershipService implements IMembership {

    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;

    public MembershipService(UserRepository userRepository, CustomerRepository customerRepository,
            AddressRepository addressRepository) {
                this.customerRepository = customerRepository;
                this.addressRepository = addressRepository;
                this.userRepository = userRepository;
    }

    @Override
    public String cancelMembership(String userId) throws ParseException {
        
        HashMap<String, String> response = new HashMap<>();

        Iterable<Customer> custItr = customerRepository.findAll();
        
        Iterator custIt = custItr.iterator();
        
        while (custIt.hasNext()) {
            
            Customer customer = (Customer) custIt.next();
            if (customer.getUserId().getId() == Integer.parseInt(userId)) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                DateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
                Date today = new Date();
                String strDate = dateFormat.format(today);  
                customer.setMembershipEndDate(strDate);
                customerRepository.save(customer);
                response.put("status", "200");
                return convertMapToJson(response);
            }
        }

        response.put("message", "No user :(");
        response.put("status", "400");

        return convertMapToJson(response);

    }

    public String renewMembership(String userId) throws ParseException {

        HashMap<String, String> response = new HashMap<>();

        Iterable<Customer> custItr = customerRepository.findAll();
        
        Iterator custIt = custItr.iterator();
        
        while (custIt.hasNext()) {
            
            Customer customer = (Customer) custIt.next();
            if (customer.getUserId().getId() == Integer.parseInt(userId)) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                DateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
                Date today = new Date();
                String strDate = dateFormat.format(today); 
                customer.setMembershipStartDate(strDate);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, 6); 
                Date nextSixMonths = cal.getTime();
                strDate = dateFormat.format(nextSixMonths);

                customer.setMembershipEndDate(strDate);
                customerRepository.save(customer);
                response.put("status", "200");
                return convertMapToJson(response);
            }
        }

        response.put("message", "No user :(");
        response.put("status", "400");

        return convertMapToJson(response);

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
    
}