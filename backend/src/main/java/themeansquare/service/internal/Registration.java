package themeansquare.service.internal;

import themeansquare.model.Address;
import themeansquare.model.Customer;
import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.IRegistration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Registration implements IRegistration {

    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;

    public Registration(UserRepository userRepository, CustomerRepository customerRepository, AddressRepository addressRepository) {

        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;

    }

    // Create design pattern for this validation check
    public String register(Customer newCustomer) throws Exception {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "username is taken");
        response.put("status", "400");

        // Add check for user name
        if (!checkIfUserExists(newCustomer.getUserId().getUsername())) {
            response.remove("message");
            Customer customer = new Customer();
            // Date licenseExpDateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(licenseExpDate);

            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            
            customer.setLicenseNumber(newCustomer.getLicenseNumber());
            customer.setLicenseExpDate(newCustomer.getLicenseExpDate());
            response.put("message", "email is taken");
            // Add email check
            if (!checkIfEmailExists(newCustomer.getEmail())) {
                response.remove("message");
                customer.setEmail(newCustomer.getEmail());

                Date startMembership = getStartMembershipDate();
                Date endMembership = getEndMembershipDate();
                DateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
                String sMembership = dateFormat.format(startMembership);
                String eMembership = dateFormat.format(endMembership);
                customer.setMembershipStartDate(sMembership);                
                customer.setMembershipEndDate(eMembership);
                
                customer.setUserId(this.createUser(newCustomer.getUserId().getUsername(), newCustomer.getUserId().getPassword()));
                
                customer.setAddress(this.createAddress(newCustomer.getAddress().getCity(), newCustomer.getAddress().getState(), newCustomer.getAddress().getStreet(), newCustomer.getAddress().getZipCode()));
                
                customer.setCreditCard(newCustomer.getCreditCard());
                customer.setCVV(newCustomer.getCVV());
                customer.setCreditCardExpDate(newCustomer.getCreditCardExpDate());

                customerRepository.save(customer);
                response.put("username", newCustomer.getUserId().getUsername());
                response.put("role", "Customer");
                response.put("id", customer.getUserId().getId() + "");
                response.put("status", "200");
            }
        }

        return this.convertMapToJson(response);
    }

    private Address createAddress(String city, String state, String street, int zipcode) {
        Address address = new Address();
        address.setCity(city);
        address.setState(state);
        address.setStreet(street);
        address.setZipCode(zipcode);
        addressRepository.save(address);

        return address;
    }

    public User createUser(String username, String password) {
        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);

        return newUser;
    }

    public Date getStartMembershipDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date today = new Date();

        return formatter.parse(formatter.format(today));
    }

    public Date getEndMembershipDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6); 
        Date nextSixMonths = cal.getTime();

        return nextSixMonths;
    }

    public boolean checkIfEmailExists(String email) {

        Iterable<Customer> itr = customerRepository.findAll();
        Iterator it = itr.iterator();
        while (it.hasNext()) {
            Customer tempCustomer = (Customer) it.next();
            System.out.println(tempCustomer.getEmail());
            
            if (tempCustomer.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }

    public boolean checkIfUserExists(String username)  {
        
        Iterable<User> itr = userRepository.findAll();
        
        Iterator it = itr.iterator();
        
        while (it.hasNext()) {
            User user = (User) it.next();
            System.out.println(user.getUsername());
            
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        
        return false;
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
