package themeansquare.controller;

import themeansquare.model.Location;
import themeansquare.service.ILocationReg;
import themeansquare.service.internal.LocationReg;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.VehicleRepository;
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
    @Autowired
    private VehicleRepository vehicleRepository;


    @PostMapping("/location")
    public String addLocation (
        @RequestParam(value = "contactNumber") int contactNumber, 
        @RequestParam(value = "name") String name, 
        @RequestParam(value = "vehicleCapacity") int vehicleCapacity,
        @RequestParam(value = "street") String street,
        @RequestParam(value = "city") String city, 
        @RequestParam(value = "state") String state, 
        @RequestParam(value = "zipcode") String zipcode) throws Exception {
        
        ILocationReg loc = new LocationReg ( contactNumber, name, vehicleCapacity, street, city, state, zipcode,
                                            locationRepository,addressRepository, vehicleRepository);
        String response = loc.addLocation();
        return response;
    }

    @GetMapping("/location")
    public Iterable<Location> getLocations() throws Exception {
        ILocationReg loc = new LocationReg(locationRepository,addressRepository, vehicleRepository);
        return loc.getLocations();
    }

    @GetMapping(value = "/location/{id}")
    public Optional<Location> getVehicleById(@PathVariable Integer id) throws Exception {
        ILocationReg loc = new LocationReg (locationRepository,addressRepository, vehicleRepository);
        return loc.getLocationById(id);
    }

    @DeleteMapping("/location/{id}")
    public String delLocation(@PathVariable Integer id) throws Exception {
        ILocationReg loc = new LocationReg (locationRepository,addressRepository, vehicleRepository);
        String response = loc.delLocation(id);
        return response;
    }

    @PutMapping("/location/{id}")
    public String updateLocation(@RequestBody Location location, @PathVariable Integer id) throws Exception {
        ILocationReg loc = new LocationReg (locationRepository,addressRepository, vehicleRepository);
        String response = loc.updateLocationById(id, location );
        return response;
            
    }

}