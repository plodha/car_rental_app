package themeansquare.controller;

import themeansquare.model.Vehicle;
import themeansquare.model.VehicleType;
import themeansquare.model.Location;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.VehicleTypeRepository;
import themeansquare.service.IVehicleReg;
import themeansquare.service.internal.VehicleReg;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/vehicleReg")
    public String register(
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
        @RequestParam(value = "vehicleCapacity") int vehicleCapacity) throws Exception {
        
        IVehicleReg reg = new VehicleReg ( vehicleClass, vehicleSize , licensePlate , model,make, status, vIN,
                                          year, contactNumber, name, vehicleCapacity,
                                          vehicleRepository, vehicleTypeRepository, locationRepository);
        String response = reg.vehicleReg();

        return response;
	}
}