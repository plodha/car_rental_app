package themeansquare.controller;

import themeansquare.model.Customer;
import themeansquare.model.Damage;
import themeansquare.model.Price;
import themeansquare.model.User;
import themeansquare.repository.AddressRepository;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.DamageRepository;
import themeansquare.repository.EmployeeRepository;
import themeansquare.repository.PriceRepository;
import themeansquare.repository.UserRepository;
import themeansquare.service.ICustomer;
import themeansquare.service.IDamage;
import themeansquare.service.IPrice;
import themeansquare.service.IRegistration;
import themeansquare.service.IUser;
import themeansquare.service.internal.CustomerService;
import themeansquare.service.internal.DamageService;
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
public class DamageController {

    @Autowired
    private DamageRepository damageRepository;
    
    
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

    @GetMapping("/getDamageForVehicleType")
    public ArrayList<Damage> getDamageForVehicleType(@RequestParam(value = "vehicleTypeId") String vehicleTypeId) {
        IDamage damageService = new DamageService(damageRepository);
        
        return damageService.getDamageForVehicleType(vehicleTypeId);
    }
    
    /**
        Use case:
            As admin, I can add a Damage 
        
        Response:
            Success:
                {
                    status: 200
                }
            Failure:
                No failures, empty will return empty with 200
     */
    
    @PostMapping("/addDamage")
    public String addDamage(@RequestBody Damage damage) {

        IDamage damageService = new DamageService(damageRepository);
        
        return damageService.addDamage(damage);
    }

    /**
        Use case:
            As admin I can update an existing Damage
        
        Response:
            Success:
                {
                    status: 200
                }
            Failure:
                No failures, empty will return empty with 200
     */

    @PutMapping("/updateDamage")
    public String updateDamage(@RequestBody Damage damage) {

        IDamage damageService = new DamageService(damageRepository);
        
        return damageService.updateDamage(damage);
    }

    /**
        Use case:
            As admin I can delete an existing Damage
        
        Response:
            Success:
                {
                    status: 200
                }
            Failure:
                No failures, empty will return empty with 200
     */

    @DeleteMapping("/deleteDamage")
    public String deleteDamage(@RequestParam(value = "damageId") String damageId) {

        IDamage damageService = new DamageService(damageRepository);
        
        return damageService.deleteDamage(damageId);

    }
}





