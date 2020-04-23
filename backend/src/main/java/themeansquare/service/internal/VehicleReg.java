package themeansquare.service.internal;

import themeansquare.model.Vehicle;
import themeansquare.model.VehicleType;
import themeansquare.model.Location;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.VehicleTypeRepository;
import themeansquare.service.IVehicleReg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class VehicleReg implements IVehicleReg {
    
    private VehicleRepository vehicleRepository;
    private VehicleTypeRepository vehicleTypeRepository;
    private LocationRepository locationRepository;
    
    ///// arrtibutes
    //vehicle
    String licensePlate;	
    String model;
    String make;		
    Boolean status;	
    String vIN;
    int year; //
    //int location; --foreign key to Location
    //int vehicleType; --foreign key to vehicleType

    //vehicleType
    String vehicleClass;
    int vehicleSize;

    //Location
    int contactNumber;
    String name;	
    int vehicleCapacity;
    //int address;  --foreign key to vehicleType ??

    public VehicleReg(VehicleRepository vehicleRepository,VehicleTypeRepository vehicleTypeRepository, LocationRepository locationRepository ) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.locationRepository = locationRepository;
    }

    public VehicleReg( String vehicleClass, int vehicleSize , String licensePlate , String model,String make,
                       Boolean status, String vIN,int year,int contactNumber, String name, int vehicleCapacity,
                       VehicleRepository vehicleRepository,VehicleTypeRepository vehicleTypeRepository, LocationRepository locationRepository ) {

        this.vehicleClass = vehicleClass;
        this.vehicleSize = vehicleSize;
        this.licensePlate = licensePlate;
        this.model = model;
        this.make = make;
        this.status = status;
        this.vIN = vIN;
        this.year = year;
        this.contactNumber = contactNumber;
        this.name = name;
        this.vehicleCapacity = vehicleCapacity;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.locationRepository = locationRepository;

    }

    

    // validation logic for vehicle api
    public String addVehicle() throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("isLicensePlateAvailable", "true");
        response.put("status", "400");

        // Add check unique license plate
        if (checkIfLicensePlateExists()) {
            response.remove("isLicensePlateAvailable");
            Vehicle vehicle = new Vehicle();
            
            //this.vehicleClass = vehicleClass;
            //this.vehicleSize = vehicleSize;
            vehicle.setLicensePlate(licensePlate);
            vehicle.setModel(model);
            vehicle.setMake(make);
            vehicle.setStatus(status);
            vehicle.setYear(year);
            response.put("isVINAvailable", "true");

            // Add VIN check
            if (checkIfVINExists()) {
                response.remove("isVINAvailable");
                
                vehicle.setVIN(vIN);
                VehicleType vehicleType = this.createVehicleType();
                vehicle.setVehicleTypeId(vehicleType);  // to break circular relation
                vehicle.setLocation(this.createLocation(vehicleType));
                vehicleRepository.save(vehicle);

                response.put("status", "200");
            }
        }

        return this.convertMapToJson(response);
    }

    private VehicleType createVehicleType() {
        VehicleType vehicleType = new VehicleType();
        vehicleType.setVehicleClass(vehicleClass);
        vehicleType.setVehicleSize(vehicleSize);
        vehicleTypeRepository.save(vehicleType);

        return vehicleType;
    }

    public Location createLocation(VehicleType vehicleType) {
        Location location = new Location();

        location.setContactNumber(contactNumber);
        location.setName(name);
        location.setVehicleCapacity(vehicleCapacity);
        location.setAddress(vehicleType);
        locationRepository.save(location);

        return location;
    }

    //look for existing license plate
    public boolean checkIfLicensePlateExists() {

        Iterable<Vehicle> itr = vehicleRepository.findAll();
        
        Iterator it = itr.iterator();
        
        while (it.hasNext()) {
            Vehicle vehicle = (Vehicle) it.next();
            System.out.println(vehicle.getLicensePlate());
            
            if (vehicle.getLicensePlate().equals(this.licensePlate)) {
                return false;
            }
        }

        return true;
    }

    public boolean checkIfVINExists() {

        Iterable<Vehicle> itr = vehicleRepository.findAll();
        
        Iterator it = itr.iterator();
        
        while (it.hasNext()) {
            Vehicle vehicle = (Vehicle) it.next();
            System.out.println(vehicle.getVIN());
            
            if (vehicle.getVIN().equals(this.vIN)) {
                return false;
            }
        }

        return true;
    }

    //for get api 
    public Iterable<Vehicle> getVehicles() throws Exception {
        
        HashMap<String, String> response = new HashMap<>();
        Iterable<Vehicle> itr = vehicleRepository.findAll();
        response.put("status", "400");
        if(itr != null){
            response.put("status", "200");
            return  itr;
        }
        return null;
    }

    //delete api
    public String delVehicle(Integer id) throws Exception {

        HashMap<String, String> response = new HashMap<>();
        vehicleRepository.deleteById(id);
        response.put("status", "200");

        return this.convertMapToJson(response);
    }

    //for update api
    public String updateVehicleById(Integer id, Vehicle vehicle) throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");

        System.out.println("--------------3---------------");
            System.out.println(vehicleRepository.findById(id).get().getModel());
            System.out.println(vehicleRepository.findById(id).get());
            System.out.println("--------method4---------------------");

        Vehicle existVehicle = vehicleRepository.findById(id).get(); //
        System.out.println("-----------------5------------");
        if(existVehicle != null) {
            
            // if no new value, then update with the old value
            //Optional.ofNullable(vehicle.getVIN()).orElse(existVehicle.getVIN())
            existVehicle.setLicensePlate(Optional.ofNullable(vehicle.getLicensePlate()).orElse(existVehicle.getLicensePlate()));
            existVehicle.setModel(Optional.ofNullable(vehicle.getModel()).orElse(existVehicle.getModel()));
            existVehicle.setMake(Optional.ofNullable(vehicle.getMake()).orElse(existVehicle.getMake()));
            existVehicle.setStatus(Optional.ofNullable(vehicle.isStatus()).orElse(existVehicle.isStatus()));
            existVehicle.setVIN(Optional.ofNullable(vehicle.getVIN()).orElse(existVehicle.getVIN())); ///need to check vIN
            existVehicle.setYear(Optional.ofNullable(vehicle.getYear()).orElse(existVehicle.getYear()));

            //existVehicle.setVehicleTypeId(existVehicle.getVehicleTypeId());
           // existVehicle.setLocation(existVehicle.getLocation());
            System.out.println("--------------6---------------");

            System.out.println(existVehicle.getVehicleTypeId().getId());
            System.out.println(existVehicle.getVehicleTypeId().toString());

            VehicleType existVehicleType = vehicleTypeRepository.findById(existVehicle.getVehicleTypeId().getId()).get();
            VehicleType vehicleType = vehicle.getVehicleTypeId();
            if (existVehicleType != null) {
               // Optional.ofNullable(vehicle.getVehicleTypeId().getVehicleSize()).orElse(existVehicleType.getVehicleSize())
                System.out.println("---------------6.1--------------");
                //System.out.println(Optional.ofNullable(vehicleType.getVehicleClass()).orElse(existVehicleType.getVehicleClass()));
                //System.out.println(Optional.ofNullable(vehicleType.getVehicleSize()).orElse(existVehicleType.getVehicleSize()));
                existVehicleType.setVehicleClass(existVehicleType.getVehicleClass());
                existVehicleType.setVehicleSize(existVehicleType.getVehicleSize());
                System.out.println("---------------6.2--------------");
                vehicleTypeRepository.save(existVehicleType);
            }
            

            System.out.println("---------------7--------------");
            System.out.println(existVehicle.getLocation().getId());
            System.out.println(existVehicle.getLocation().toString());
            locationRepository.save(existVehicle.getLocation());
            System.out.println("---------------8--------------");
            vehicleRepository.save(existVehicle);
            System.out.println("---------------9--------------");
            
            response.put("status", "200");
        }          

        return this.convertMapToJson(response);
    }

    public String convertMapToJson(HashMap<String, String> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResponse = "{\"Status\" : 400, \"error_message\" : \"convertMapToJson failed. Vehicle.java file\"}";
        }
    
        return jsonResponse;
    }
}