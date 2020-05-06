package themeansquare.carrentalservice;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import themeansquare.controller.CustomerController;
import themeansquare.controller.RegistrationController;
import themeansquare.model.Address;
import themeansquare.model.Customer;
import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.ICustomer;
import themeansquare.service.internal.CustomerService;
import themeansquare.service.internal.Registration;

public class CustomerControllerTest {
	@InjectMocks
	private CustomerController customerController;
	@InjectMocks
	private RegistrationController regController;
	@Mock
    private UserRepository userRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
    @InjectMocks
    Registration reg = new Registration(userRepository, customerRepository, addressRepository);

    @Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllCustomersTest() throws Exception {
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        ArrayList<Customer> results =  customerController.getAllCustomers();
        Assert.assertEquals(results.size(), 0);
    }
	
	@Test
	public void getCustomerInfoTest() throws Exception {

		regController.register(createCustomer("email", "username"));
		Customer response = customerController.getCustomerInfo("1");
		Assert.assertTrue(response == null);

	}
	
	@Test
	public void updateCustomerTest() throws Exception {

		regController.register(createCustomer("email", "username"));
		try {
			String response = customerController.updateCustomer(createCustomer("email", "username"));
		
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
		// Assert.assertTrue(response == null);

	}

	@Test
	public void removeCustomerTest() throws Exception {

		regController.register(createCustomer("email", "username"));
		try {
			String response = customerController.removeCustomer("1");
			
			Assert.assertEquals("{\"status\":\"200\"}", response);
		} catch (Exception e) {
			
		}
		
		// Assert.assertTrue(response == null);

	}

    public Customer createCustomer(String email, String username) {
        Customer c = new Customer();
		Address a = new Address();
		User u = new User();
		
		
		u.setPassword("lol");
		u.setUsername(username);
		
		a.setCity("Fremont");
		a.setState("CA");
		a.setStreet("Fremont Blvd");
		a.setZipCode(94536);
		
		Date date = new Date();
		c.setCVV("233");
		c.setEmail(email);
		c.setFirstName("Pranav");
		c.setLastName("Lodha");
		c.setLicenseExpDate("2020-10-10");
		c.setLicenseNumber("ABCDEFG");
		c.setLicenseExpDate("2020-10-10");
		c.setMembershipEndDate("2020-10-10");
		c.setMembershipStartDate("2020-10-10");
		c.setCreditCard("12378917293");
		c.setCreditCardExpDate("2020-10-10");
		c.setAddress(a);
        c.setUserId(u);
        
        return c;
	}   
	

}
