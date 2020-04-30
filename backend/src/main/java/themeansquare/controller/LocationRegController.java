package themeansquare.controller;

import themeansquare.model.Vehicle;
import themeansquare.model.VehicleType;
import themeansquare.model.Location;
import themeansquare.service.IVehicleReg;
import themeansquare.service.internal.VehicleReg;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.VehicleTypeRepository;
import themeansquare.repository.AddressRepository;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*

    //Location
    int contactNumber;
    String name;	
    int vehicleCapacity;
    int address;

    //Address
     private String street;
    private String city;
    private String state;
    private String zipcode;
*/
@RestController
public class LocationRegController {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AddressRepository addressRepository;

}