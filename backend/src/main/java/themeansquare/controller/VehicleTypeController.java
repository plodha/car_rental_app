package themeansquare.controller;

import themeansquare.model.Customer;
import themeansquare.model.VehicleType;
import themeansquare.model.Price;
import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.DamageRepository;
import themeansquare.repository.VehicleTypeRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.PriceRepository;
import themeansquare.repository.UserRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.service.ICustomer;
import themeansquare.service.IVehicleType;
import themeansquare.service.IPrice;
import themeansquare.service.IRegistration;
import themeansquare.service.IUser;
import themeansquare.service.internal.CustomerService;
import themeansquare.service.internal.VehicleTypeService;
import themeansquare.service.internal.PriceService;
import themeansquare.service.IRegistration;
import themeansquare.service.IUser;
import themeansquare.service.internal.Registration;
import themeansquare.service.internal.UserAuth;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class VehicleTypeController {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private DamageRepository damageRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AddressRepository addressRepository;
    /**
        Use case:
            Given a Vehicle Type, give me all the prices for it.
        
        Response:
            Success:
                [
                    {
                        "hourlyPrice": 50.0,
                        "hourlyRange": "1 - 5",
                        "id": 1,
                        "vehicleTypeId": {
                            "id": 1,
                            "vehicleSize": 5,
                            "vehicleClass": "Car"
                        },
                        "lateFee": 40.0
                    }
                ]
            Failure:
                No failures, empty will return empty with 200
     */

    @GetMapping("/getAllVehicleTypes")
    public ArrayList<VehicleType> getAllVehicleTypes() {
        IVehicleType VehicleTypeService = new VehicleTypeService(vehicleTypeRepository);
        
        return VehicleTypeService.getAllVehicleTypes();
    }
    
    /**
        Use case:
            As admin, I can add a VehicleType 
        
        Response:
            Success:
                {
                    status: 200
                }
            Failure:
                No failures, empty will return empty with 200
     */
    
    @PostMapping("/addVehicleType")
    public String addVehicleType(@RequestBody VehicleType vehicleType) {

        IVehicleType VehicleTypeService = new VehicleTypeService(vehicleTypeRepository);
        
        return VehicleTypeService.addVehicleType(vehicleType);
    }

    /**
        Use case:
            As admin I can update an existing VehicleType
        
        Response:
            Success:
                {
                    status: 200
                }
            Failure:
                No failures, empty will return empty with 200
     */

    @PutMapping("/updateVehicleType")
    public String updateVehicleType(@RequestBody VehicleType vehicleType) {

        IVehicleType VehicleTypeService = new VehicleTypeService(vehicleTypeRepository);
        
        return VehicleTypeService.updateVehicleType(vehicleType);
    }

    /**
     * Use case: As admin I can delete an existing VehicleType
     * 
     * Response: Success: { status: 200 } Failure: No failures, empty will return
     * empty with 200
     * 
     * @throws Exception
     */

    @DeleteMapping("/deleteVehicleType")
    public String deleteVehicleType(@RequestParam(value = "vehicleTypeId") String vehicleTypeId) throws Exception {

        IVehicleType VehicleTypeService = new VehicleTypeService(vehicleTypeRepository, priceRepository, damageRepository, vehicleRepository);
        
        return VehicleTypeService.deleteVehicleType(vehicleTypeId);

    }
}





