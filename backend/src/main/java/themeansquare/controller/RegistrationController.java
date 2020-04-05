package themeansquare.controller;



import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import themeansquare.service.IRegistration;
//import themeansquare.service.internal.Registration;

import themeansquare.model.Address;
import themeansquare.repository.AddressRepository;
import themeansquare.service.IRegistration;
import themeansquare.service.internal.Registration;


/*
    Customer
    *int _id;
    String name;
    String licenseNumber ;
    *DateTimeFormatter registrationDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    *DateTimeFormatter registrationEndDate  = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    *boolean membershipStatus ;
    *boolean verified ;
    DateTimeFormatter licenseExpDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    Address address ;
    String email;
*/

@RestController()
public class RegistrationController {

	@GetMapping("/register")
    public String register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, 
        @RequestParam(value = "licenseNumber") String licenseNumber, @RequestParam(value = "licenseExpDate") String licenseExpDate, 
        @RequestParam(value = "address") String address, @RequestParam(value = "email") String email) {
        
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        params.put("licenseNumber", licenseNumber);
        params.put("licenseExpDate", licenseExpDate);
        params.put("address", address);
        params.put("email", email);

        IRegistration reg = new Registration(params);

        String response = reg.isValidParams();
        
        return response;
	}
	
	@Autowired
	private AddressRepository addressRepository;
	
	@GetMapping("/testAddress")
	public String testAddress() {
		Address pranav = new Address();
		pranav.setStreet("40744 Chiltern Drive");
		pranav.setCity("Fremont");
		pranav.setState("California");
		pranav.setZipCode(94539);
		addressRepository.save(pranav);
		return "Test";
	}

}





