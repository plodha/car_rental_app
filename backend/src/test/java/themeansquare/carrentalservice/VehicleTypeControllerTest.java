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
import themeansquare.controller.VehicleTypeController;
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

public class VehicleTypeControllerTest {
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
    private VehicleTypeController vehicleTypeController;

    @InjectMocks
    ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
    @InjectMocks
    Registration reg = new Registration(userRepository, customerRepository, addressRepository);

    @Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllVehicleTypesTest() throws Exception {
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        ArrayList<VehicleType> results =  vehicleTypeController.getAllVehicleTypes();
        Assert.assertEquals(results.size(), 0);
    }

    @Test
	public void addVehicleTypeTest() throws Exception {
        VehicleType vt = new VehicleType();
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        String results =  vehicleTypeController.addVehicleType(vt);
        Assert.assertEquals(results, null);
    }

    @Test
	public void updateVehicleTypeTest() throws Exception {
        VehicleType vt = new VehicleType();
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        String results =  vehicleTypeController.updateVehicleType(vt);
        Assert.assertEquals(results, null);
    }

    @Test
	public void deleteVehicleTypeTest() throws Exception {
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        String results =  vehicleTypeController.deleteVehicleType("1");
        Assert.assertEquals(results, null);
    }
}
