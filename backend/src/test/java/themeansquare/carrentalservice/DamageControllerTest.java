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
import themeansquare.controller.DamageController;
import themeansquare.controller.RegistrationController;
import themeansquare.model.Address;
import themeansquare.model.Customer;
import themeansquare.model.Damage;
import themeansquare.model.User;
import themeansquare.model.VehicleType;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.ICustomer;
import themeansquare.service.internal.CustomerService;
import themeansquare.service.internal.Registration;

public class DamageControllerTest {
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
    @Mock
    private DamageController damageController;
    
    @InjectMocks
    ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
    @InjectMocks
    Registration reg = new Registration(userRepository, customerRepository, addressRepository);

    @Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getDamageForVehicleTypeTest() throws Exception {
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        ArrayList<Damage> results =  damageController.getDamageForVehicleType("1");
        Assert.assertEquals(results.size(), 0);
    }

	@Test
	public void addDamageTest() throws Exception {
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
		String results =  damageController.addDamage(createDamage());
		System.out.println(results);
        Assert.assertEquals(results, null);
	}
	
	@Test
	public void updateDamageTest() throws Exception {
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
		String results =  damageController.updateDamage(createDamage());
		System.out.println(results);
        Assert.assertEquals(results, null);
	}
	
	@Test
	public void deleteDamageTest() throws Exception {
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
		String results =  damageController.deleteDamage("1");
		System.out.println(results);
        Assert.assertEquals(results, null);
    }

    public Damage createDamage() {
		VehicleType vt = new VehicleType();
		vt.setVehicleClass("f");
		vt.setVehicleSize(5);
		
		Damage d = new Damage();
		d.setDamageFee(5);
		d.setDamageType("f");
		d.setVehicleTypeId(vt);

	   return d;
	}   
	

}
