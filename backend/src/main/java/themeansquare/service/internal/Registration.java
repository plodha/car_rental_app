package themeansquare.service.internal;

import themeansquare.model.Address;
import themeansquare.model.Customer;
import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.IRegistration;

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

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String licenseNumber;
    private String licenseExpDate;
    private String email;

    public Registration(String username, String password, String firstName, String lastName, String street, String city,
            String state, String zipcode, String licenseNumber, String licenseExpDate, String email,
            UserRepository userRepository, CustomerRepository customerRepository, AddressRepository addressRepository) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.licenseNumber = licenseNumber;
        this.licenseExpDate = licenseExpDate;
        this.email = email;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;

    }

    // Create design pattern for this validation check
    public String register() throws Exception {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "username is taken");
        response.put("status", "400");

        // Add check for user name
        if (checkIfUserExists()) {
            response.remove("message");
            Customer customer = new Customer();
            Date licenseExpDateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(licenseExpDate);

            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            
            customer.setLicenseNumber(licenseNumber);
            customer.setLicenseExpDate(licenseExpDateFormat);
            response.put("message", "email is taken");
            // Add email check
            if (checkIfEmailExists()) {
                response.remove("message");
                customer.setEmail(email);

                Date startMembership = getStartMembershipDate();
                Date endMembership = getEndMembershipDate();
                customer.setMembershipStartDate(startMembership);                
                customer.setMembershipEndDate(endMembership);
                
                customer.setUserId(this.createUser());
                
                customer.setAddress(this.createAddress());
                customerRepository.save(customer);
                response.put("username", this.username);
                response.put("role", "Customer");
                response.put("id", customer.getUserId().getId() + "");
                response.put("status", "200");
            }
        }

        return this.convertMapToJson(response);
    }

    private Address createAddress() {
        Address address = new Address();
        address.setCity(this.city);
        address.setState(this.state);
        address.setStreet(this.street);
        address.setZipCode(Integer.parseInt(this.zipcode));
        addressRepository.save(address);

        return address;
    }

    public User createUser() {
        User newUser = new User();

        newUser.setUsername(this.username);
        newUser.setPassword(this.password);
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
        cal.add(Calendar.YEAR, 1); 
        Date nextYear = cal.getTime();

        return nextYear;
    }

    public boolean checkIfEmailExists() {

        Iterable<Customer> itr = customerRepository.findAll();
        
        Iterator it = itr.iterator();
        
        while (it.hasNext()) {
            Customer tempCustomer = (Customer) it.next();
            System.out.println(tempCustomer.getEmail());
            
            if (tempCustomer.getEmail().equals(this.email)) {
                return false;
            }
        }

        return true;
    }

    public boolean checkIfUserExists()  {
        
        Iterable<User> itr = userRepository.findAll();
        
        Iterator it = itr.iterator();
        
        while (it.hasNext()) {
            User user = (User) it.next();
            System.out.println(user.getUsername());
            
            if (user.getUsername().equals(this.username)) {
                return false;
            }
        }
        
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
