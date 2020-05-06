package themeansquare.carrentalservice;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

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
import themeansquare.controller.EmployeeController;
import themeansquare.controller.InvoiceServiceController;
import themeansquare.controller.MembershipController;
import themeansquare.controller.RegistrationController;
import themeansquare.controller.UserAuthController;
import themeansquare.controller.VehicleRegController;
import themeansquare.model.Address;
import themeansquare.model.Customer;
import themeansquare.model.Damage;
import themeansquare.model.Employee;
import themeansquare.model.Invoice;
import themeansquare.model.User;
import themeansquare.model.Vehicle;
import themeansquare.model.VehicleType;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.ICustomer;
import themeansquare.service.internal.CustomerService;
import themeansquare.service.internal.Registration;

public class VehicleRegControllerTest {
	@InjectMocks
	private EmployeeController employeeController;
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
    @Mock
    private VehicleRegController vehicleRegController;

    @InjectMocks
    ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
    @InjectMocks
    Registration reg = new Registration(userRepository, customerRepository, addressRepository);

    @Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
    @Test
	public void getVehiclesTest() throws Exception {
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        Iterable<Vehicle> results =  vehicleRegController.getVehicles();
        Assert.assertEquals(results.iterator().hasNext(), false);
    }

    @Test
	public void getVehicleByIdTest() throws Exception {
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        Optional<Vehicle> results =  vehicleRegController.getVehicleById(1);
        Assert.assertEquals(results.isPresent(), false);
    }

    @Test
	public void getVehicleByVehicleTypeTest() throws Exception {
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        Iterable<Vehicle> results =  vehicleRegController.getVehicleByVehicleType(1);
        Assert.assertEquals(results.iterator().hasNext(), false);
    }

    @Test
	public void getVehicleByLocationTest() throws Exception {
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        Iterable<Vehicle> results =  vehicleRegController.getVehicleByLocation(1);
        Assert.assertEquals(results.iterator().hasNext(), false);
    }
    
    @Test
	public void delVehiclesTest() throws Exception {
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        String results =  vehicleRegController.delVehicles(1);
        Assert.assertEquals(results, null);
    }

    @Test
	public void updateVehicleWith_LocationVehicleTypeTest() throws Exception {
        Vehicle v = new Vehicle();
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        String results =  vehicleRegController.updateVehicleWith_LocationVehicleType(v, 1, 1, 1);
        Assert.assertEquals(results, null);
    }

    @Test
	public void updateVehicleTest() throws Exception {
        Vehicle v = new Vehicle();
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        String results =  vehicleRegController.updateVehicle(v, 1);
        Assert.assertEquals(results, null);
    }
}
