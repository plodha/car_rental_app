package themeansquare.carrentalservice;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import themeansquare.controller.RegistrationController;
import themeansquare.model.Address;
import themeansquare.model.Customer;
import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.internal.Registration;

public class RegistrationControllerTest {
	@InjectMocks
	private RegistrationController regController;
	
	@Mock
    private UserRepository userRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressRepository addressRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void registerTest() throws Exception {
		
		Registration reg = new Registration(userRepository, customerRepository, addressRepository);
		Customer c = new Customer();
		Address a = new Address();
		User u = new User();
		
		
		u.setPassword("lol");
		u.setUsername("pranav");
		
		a.setCity("Fremont");
		a.setState("CA");
		a.setStreet("Fremont Blvd");
		a.setZipCode(94536);
		
		Date date = new Date();
		c.setCVV("233");
		c.setEmail("pranav.lodha@sjsu.edu");
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
		
		String res = reg.register(c);
		Assert.assertTrue(res.equals("{\"role\":\"Customer\",\"id\":\"0\",\"status\":\"200\",\"username\":\"pranav\"}"));
	}
}
