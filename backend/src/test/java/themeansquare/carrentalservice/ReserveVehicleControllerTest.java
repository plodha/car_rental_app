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
import themeansquare.controller.ReserveVehicleController;
import themeansquare.model.Address;
import themeansquare.model.Customer;
import themeansquare.model.Damage;
import themeansquare.model.Employee;
import themeansquare.model.Invoice;
import themeansquare.model.Reservation;
import themeansquare.model.User;
import themeansquare.model.VehicleType;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.ICustomer;
import themeansquare.service.internal.CustomerService;
import themeansquare.service.internal.Registration;

public class ReserveVehicleControllerTest {
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
    private ReserveVehicleController reserveVehicleController;

    @InjectMocks
    ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
    @InjectMocks
    Registration reg = new Registration(userRepository, customerRepository, addressRepository);

    @Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void registerTest() throws Exception {
        String [] test = {};
        Reservation r = new Reservation();
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        String results =  reserveVehicleController.register(r);
        Assert.assertEquals(results, null);
    }

    @Test
	public void addReservationTest() throws Exception {
        String [] test = {};
        Reservation r = new Reservation();
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        String results =  reserveVehicleController.addReservation(
            "", "", 0.0, "",true,2.0, 0.0, 0.0,0.0, 1, 1, 1, 1);
        Assert.assertEquals(results, null);
    }

    @Test
	public void getReservationsTest() throws Exception {
        String [] test = {};
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        Iterable<Reservation> results =  reserveVehicleController.getReservations();
        Assert.assertEquals(results.iterator().hasNext(), false);
    }

    @Test
	public void getReservationByIdTest() throws Exception {
        String [] test = {};
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        Optional<Reservation> results =  reserveVehicleController.getReservationById(1);
        Assert.assertEquals(results.isPresent(), false);
    }

    @Test
	public void cancelReservationTest() throws Exception {
        String [] test = {};
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        String results =  reserveVehicleController.cancelReservation(1, false);
        Assert.assertEquals(results, null);
    }

    @Test
	public void getEstimatedPriceForVehiclesTest() throws Exception {
        String [] test = {};
        
        // ICustomer customerService = new CustomerService(userRepository, employeeRepository, customerRepository, addressRepository);
        String results =  reserveVehicleController.getEstimatedPriceForVehicles(1, "tomorrow", "today");
        Assert.assertEquals(results, null);
    }

}
