package themeansquare.service.internal;

import java.util.HashMap;
import java.util.Iterator;

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
       
        HashMap<String, String> response = new HashMap<>();
        Iterable<Customer> custItr = customerRepository.findAll();

        Iterator custIt = custItr.iterator();
        
        while (custIt.hasNext()) {
            HashMap<String, String> temp = new HashMap<>();
            Customer customer = (Customer) custIt.next();
            temp.put("username", customer.getUserId().getUsername());
            temp.put("username", customer.getUserId().getUsername());
        }
        return null;
    }
    
}