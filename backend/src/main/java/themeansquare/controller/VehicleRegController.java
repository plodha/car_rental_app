package themeansquare.controller;

import themeansquare.model.Vehicle;
import themeansquare.model.VehicleType;
import themeansquare.model.Location;
import themeansquare.service.IVehicleReg;
import themeansquare.service.internal.VehicleReg;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.ReservationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.VehicleTypeRepository;
import themeansquare.repository.AddressRepository;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Iterator;

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
    int String vehicleCondition;
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
    @Autowired
    private ReservationRepository reservationRepository;
   
    @PostMapping("/vehicle")
    public String addVehicle(@RequestParam(value = "licensePlate") String licensePlate,
            @RequestParam(value = "model") String model, @RequestParam(value = "make") String make,
            @RequestParam(value = "status") Boolean status, @RequestParam(value = "vIN") String vIN,
            @RequestParam(value = "year") int year, @RequestParam(value = "vehicleTypeId") int vehicleTypeId,
            @RequestParam(value = "vehicleCondition") String vehicleCondition,
            @RequestParam(value = "locationId") int locationId, 
            @RequestParam(value = "currentMileage") String currentMileage, 
            @RequestParam(value = "registrationTag") String registrationTag, 
            @RequestParam(value = "serviceDate") String serviceDate) throws Exception {

        IVehicleReg reg = new VehicleReg(licensePlate, model, make, status, vIN, year, vehicleCondition,locationId, vehicleTypeId,currentMileage, registrationTag, serviceDate, 
                                        vehicleRepository, vehicleTypeRepository, locationRepository, addressRepository,reservationRepository);
        String response = reg.addVehicle();
        return response;
    }

    // get all vehicles
    @GetMapping("/vehicle")
    public Iterable<Vehicle> getVehicles() throws Exception {
        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,
                                        addressRepository,reservationRepository);
        return reg.getVehicles();
        // return vehicleRepository.findAll();
    }

    // get vehicle with a fixed vehicle id
    @GetMapping(value = "/vehicle/{id}")
    public Optional<Vehicle> getVehicleById(@PathVariable Integer id) throws Exception {
        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,
                                        addressRepository,reservationRepository);
        return reg.getVehicleById(id);
    }

    // get available vehicle for a vehicleType Id
    @GetMapping(value = "/vehicleForVehicletype/{vehicleTypeId}")
    public Iterable<Vehicle> getVehicleByVehicleType(@PathVariable Integer vehicleTypeId) throws Exception {
        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,
                                        addressRepository,reservationRepository);
        return reg.getVehicleByVehicleType(vehicleTypeId);
    }

    // get available vehicle for a location
    @GetMapping(value = "/vehicleForLocation/{locationId}")
    public Iterable<Vehicle> getVehicleByLocation(@PathVariable Integer locationId) throws Exception {
        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,
                                        addressRepository,reservationRepository);
        return reg.getVehicleByLocation(locationId);
    }

    // get available vehicle for a vehicleType Id, location, pickuptime, actualdropOfftime
    /*
        from postman:
        http://localhost:8080/getVehiclesAvailableForReservation?locationId=2&vehicleTypeId=1&newPickUpTime=1/15/2020 10:00&newEstimatedDropOffTime=1/17/2020 10:00
    */
    @GetMapping("/getVehiclesAvailableForReservation")
    public Iterable<Vehicle> getVehiclesAvailableForReservation(@RequestParam(value = "locationId") Integer locationId,
                                               @RequestParam(value = "vehicleTypeId") Integer vehicleTypeId,
                                               @RequestParam(value = "newPickUpTime") String newPickUpTime,
                                               @RequestParam(value = "newEstimatedDropOffTime") String newEstimatedDropOffTime) throws Exception {

        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,
                                        addressRepository,reservationRepository);
        return reg.getVehiclesAvailableForReservation(locationId,vehicleTypeId,newPickUpTime,newEstimatedDropOffTime);
    }

    @DeleteMapping("/vehicle/{id}")
    public String delVehicles(@PathVariable Integer id) throws Exception {

        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,
                                        addressRepository,reservationRepository);
        String response = reg.delVehicle(id);
        //vehicleRepository.deleteById(id);
        return response;
    }

    //update vehicle with fixed vehicleType and location
    @PutMapping("/vehicle/{id}/{vehicleTypeId}/{locationId}")
    public String updateVehicleWith_LocationVehicleType(@RequestBody Vehicle vehicle, @PathVariable Integer id, @PathVariable Integer vehicleTypeId, @PathVariable Integer locationId) throws Exception {

        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,
                                        addressRepository,reservationRepository);
        String response = reg.updateVehicleById(id, vehicleTypeId, locationId, vehicle );
        return response;
            
    }

    //update a vehicle
    @PutMapping("/vehicle/{id}")
    public String updateVehicle(@RequestBody Vehicle vehicle, @PathVariable Integer id) throws Exception {

        IVehicleReg reg = new VehicleReg(vehicleRepository, vehicleTypeRepository, locationRepository,
                                        addressRepository,reservationRepository);
        String response = reg.updateVehicleByIdOld(id, vehicle );
        return response;
            
    }

    @PostMapping("/vehicle/old")
    public String addVehicleOld (
        @RequestParam(value = "licensePlate") String licensePlate, 
        @RequestParam(value = "model") String model, 
        @RequestParam(value = "make") String make, 
        @RequestParam(value = "status") Boolean status,
        @RequestParam(value = "vIN") String vIN,
        @RequestParam(value = "year") int year, 
        @RequestParam(value = "vehicleCondition") String vehicleCondition,
        @RequestParam(value = "vehicleClass") String vehicleClass, 
        @RequestParam(value = "vehicleSize") int vehicleSize, 
        @RequestParam(value = "contactNumber") int contactNumber, 
        @RequestParam(value = "name") String name, 
        @RequestParam(value = "vehicleCapacity") int vehicleCapacity,
        @RequestParam(value = "street") String street,
        @RequestParam(value = "city") String city, 
        @RequestParam(value = "state") String state, 
        @RequestParam(value = "zipcode") String zipcode,
        @RequestParam(value = "currentMileage") String currentMileage, 
        @RequestParam(value = "registrationTag") String registrationTag, 
        @RequestParam(value = "serviceDate") String serviceDate) throws Exception {
        
        IVehicleReg reg = new VehicleReg ( vehicleClass, vehicleSize , licensePlate , model,make, status, vIN,
                                          year, vehicleCondition,contactNumber, name, vehicleCapacity, street, city, state, zipcode,currentMileage, registrationTag, serviceDate,
                                          vehicleRepository, vehicleTypeRepository, locationRepository,addressRepository,reservationRepository);
        String response = reg.addVehicleOld();
        return response;
    }


    
}