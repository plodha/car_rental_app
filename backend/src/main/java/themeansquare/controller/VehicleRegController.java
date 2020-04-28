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
    //vehicle
    String licensePlate;	
    String model;
    String make;		
    Boolean status;	
    String vIN;
    int year; //
    int location;
    int vehicleType;

    //vehicleType
    String vehicleClass;
    int vehicleSize;

    //Location
    int contactNumber;
    String name;	
    int vehicleCapacity;
    int address;
*/

@RestController
public class VehicleRegController {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AddressRepository addressRepository;

    
    @PostMapping("/vehicle")
    public String addVehicle (
        @RequestParam(value = "licensePlate") String licensePlate, 
        @RequestParam(value = "model") String model, 
        @RequestParam(value = "make") String make, 
        @RequestParam(value = "status") Boolean status,
        @RequestParam(value = "vIN") String vIN,
        @RequestParam(value = "year") int year, 
        @RequestParam(value = "vehicleClass") String vehicleClass, 
        @RequestParam(value = "vehicleSize") int vehicleSize, 
        @RequestParam(value = "contactNumber") int contactNumber, 
        @RequestParam(value = "name") String name, 
        @RequestParam(value = "vehicleCapacity") int vehicleCapacity,
        @RequestParam(value = "street") String street,
        @RequestParam(value = "city") String city, 
        @RequestParam(value = "state") String state, 
        @RequestParam(value = "zipcode") String zipcode) throws Exception {
        
        IVehicleReg reg = new VehicleReg ( vehicleClass, vehicleSize , licensePlate , model,make, status, vIN,
                                          year, contactNumber, name, vehicleCapacity, street, city, state, zipcode,
                                          vehicleRepository, vehicleTypeRepository, locationRepository,addressRepository);
        String response = reg.addVehicle();

        return response;
    }
    
    @GetMapping("/vehicle")
    public Iterable<Vehicle> getVehicles() throws Exception {
        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,addressRepository);
        return reg.getVehicles();
        //return vehicleRepository.findAll();
    }

    @GetMapping(value = "/vehicle/{id}")
    public Optional<Vehicle> getVehicleById(@PathVariable Integer id) throws Exception {
        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,addressRepository);
        return reg.getVehicleById(id);
    }

    @DeleteMapping("/vehicle/{id}")
    public String delVehicles(@PathVariable Integer id) throws Exception {

        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,addressRepository);
        String response = reg.delVehicle(id);
        //vehicleRepository.deleteById(id);
        return response;
    }

    @PutMapping("/vehicle/{id}")
    public String updateVehicle(@RequestBody Vehicle vehicle, @PathVariable Integer id) throws Exception {

        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,addressRepository);
        String response = reg.updateVehicleById(id, vehicle );
        return response;
            
    }


    
}