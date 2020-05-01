package themeansquare.service.internal;

import themeansquare.service.IVehicleReg;
import themeansquare.model.Vehicle;
import themeansquare.model.VehicleType;
import themeansquare.model.Address;
import themeansquare.model.Location;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.VehicleTypeRepository;
import themeansquare.repository.AddressRepository;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class VehicleReg implements IVehicleReg {
    
    private VehicleRepository vehicleRepository;
    private VehicleTypeRepository vehicleTypeRepository;
    private LocationRepository locationRepository;
    private AddressRepository addressRepository;
    
    ///// arrtibutes
    //vehicle
    private String licensePlate;	
    private String model;
    private String make;		
    private Boolean status;	
    private String vIN;
    private int year; //
    private int locationId;
    private int vehicleTypeId;
    //int location; --foreign key to Location
    //int vehicleType; --foreign key to vehicleType

    //vehicleType
    private String vehicleClass;
    private int vehicleSize;

    //Location
    private int contactNumber;
    private String name;	
    private int vehicleCapacity;
    
    //Address
    private String street;
    private String city;
    private String state;
    private String zipcode;

    public VehicleReg(VehicleRepository vehicleRepository,VehicleTypeRepository vehicleTypeRepository, LocationRepository locationRepository, AddressRepository addressRepository ) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
    }

    public VehicleReg( String vehicleClass, int vehicleSize , String licensePlate , String model,String make,
                       Boolean status, String vIN,int year,int contactNumber, String name, int vehicleCapacity,
                       String street, String city,String state, String zipcode,
                       VehicleRepository vehicleRepository,VehicleTypeRepository vehicleTypeRepository, LocationRepository locationRepository, AddressRepository addressRepository) {

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
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;

    }

    public VehicleReg(  String licensePlate , String model,String make,
                       Boolean status, String vIN,int year,int locationId, int vehicleTypeId,
                       VehicleRepository vehicleRepository,VehicleTypeRepository vehicleTypeRepository, LocationRepository locationRepository, AddressRepository addressRepository) {

        this.licensePlate = licensePlate;
        this.model = model;
        this.make = make;
        this.status = status;
        this.vIN = vIN;
        this.year = year;
        this.locationId =locationId; 
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;

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

                response.put("LocationIdNotAvailable", "true");
                if (locationRepository.existsById(this.locationId)) {
                    Location location = locationRepository.findById(this.locationId).get();
                    vehicle.setLocation(location);
                    response.remove("LocationIdNotAvailable");
                    
                    response.put("VehicleTypeNotAvailable", "true");
                    if (vehicleTypeRepository.existsById(this.vehicleTypeId)) {
                        VehicleType vehicleType = vehicleTypeRepository.findById(this.vehicleTypeId).get();
                        vehicle.setVehicleTypeId(vehicleType); 
                        response.remove("VehicleTypeNotAvailable");
                    }
                }
                vehicleRepository.save(vehicle);
                response.put("status", "200");
            }
        }
        return this.convertMapToJson(response);
    }

    // validation logic for vehicle api
    public String addVehicleOld() throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("isLicensePlateAvailable", "true");
        response.put("status", "400");

        // Add check unique license plate
        if (checkIfLicensePlateExists()) {
            response.remove("isLicensePlateAvailable");
            Vehicle vehicle = new Vehicle();
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
                vehicle.setVehicleTypeId(this.createVehicleType());  // to break circular relation
                vehicle.setLocation(this.createLocation());
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

    public Location createLocation( ) {
        Location location = new Location();

        location.setContactNumber(contactNumber);
        location.setName(name);
        location.setVehicleCapacity(vehicleCapacity);
        location.setAddress(this.createAddress());
        locationRepository.save(location);

        return location;
    }

    private Address createAddress() {
        Address address = new Address();
        address.setCity(this.city);
        address.setState(this.state);
        address.setStreet(this.street);
        address.setZipCode(Integer.parseInt(this.zipcode));
        addressRepository.save(address);

        return address;
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

    //get-all api 
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

    //get by id api
    public Optional<Vehicle> getVehicleById(Integer id) throws Exception {

        return vehicleRepository.findById(id);
    }

    //delete api
    public String delVehicle(Integer id) throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");

        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            response.put("status", "200");
        }
        return this.convertMapToJson(response);
    }

    //for update api
    public String updateVehicleById(Integer id, Vehicle vehicle) throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");
        Vehicle existVehicle = vehicleRepository.findById(id).get(); //
        if(existVehicle != null) {
            System.out.println("vehicle.getVIN() "+vehicle.getVIN());
            // if no new value, then update with the old value
            //Optional.ofNullable(vehicle.getVIN()).orElse(existVehicle.getVIN())
            existVehicle.setLicensePlate(Optional.ofNullable(vehicle.getLicensePlate()).orElse(existVehicle.getLicensePlate()));
            existVehicle.setModel(Optional.ofNullable(vehicle.getModel()).orElse(existVehicle.getModel()));
            existVehicle.setMake(Optional.ofNullable(vehicle.getMake()).orElse(existVehicle.getMake()));
            existVehicle.setStatus(Optional.ofNullable(vehicle.isStatus()).orElse(existVehicle.isStatus()));
            existVehicle.setVIN(Optional.ofNullable(vehicle.getVIN()).orElse(existVehicle.getVIN())); ///need to check vIN
            existVehicle.setYear(Optional.ofNullable(vehicle.getYear()).orElse(existVehicle.getYear()));

            vehicleTypeRepository.save(existVehicle.getVehicleTypeId());
            locationRepository.save(existVehicle.getLocation());
            vehicleRepository.save(existVehicle);

            response.put("status", "200");
        }          

        return this.convertMapToJson(response);
    }

    //for update api
    public String updateVehicleByIdOld(Integer id, Vehicle vehicle) throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");
        Vehicle existVehicle = vehicleRepository.findById(id).get(); //
        if(existVehicle != null) {
            System.out.println("vehicle.getVIN() "+vehicle.getVIN());
            // if no new value, then update with the old value
            //Optional.ofNullable(vehicle.getVIN()).orElse(existVehicle.getVIN())
            existVehicle.setLicensePlate(Optional.ofNullable(vehicle.getLicensePlate()).orElse(existVehicle.getLicensePlate()));
            existVehicle.setModel(Optional.ofNullable(vehicle.getModel()).orElse(existVehicle.getModel()));
            existVehicle.setMake(Optional.ofNullable(vehicle.getMake()).orElse(existVehicle.getMake()));
            existVehicle.setStatus(Optional.ofNullable(vehicle.isStatus()).orElse(existVehicle.isStatus()));
            existVehicle.setVIN(Optional.ofNullable(vehicle.getVIN()).orElse(existVehicle.getVIN())); ///need to check vIN
            existVehicle.setYear(Optional.ofNullable(vehicle.getYear()).orElse(existVehicle.getYear()));

            
            int vehicleTypeId = existVehicle.getVehicleTypeId().getId();
            vehicleTypeRepository.save(this.updateVehicleTypeById(vehicleTypeId, vehicle));
       
            int locationId = existVehicle.getLocation().getId();
            locationRepository.save(this.updateLocationById(locationId, vehicle));
            vehicleRepository.save(existVehicle);

            response.put("status", "200");
        }          

        return this.convertMapToJson(response);
    }

    public VehicleType updateVehicleTypeById (int id, Vehicle vehicle) {
        VehicleType existVehicleType = vehicleTypeRepository.findById(id).get();
        VehicleType vehicleType = vehicle.getVehicleTypeId();

        if (existVehicleType != null) {
               // Optional.ofNullable(vehicle.getVehicleTypeId().getVehicleSize()).orElse(existVehicleType.getVehicleSize())
                existVehicleType.setVehicleClass(Optional.ofNullable(vehicleType.getVehicleClass()).orElse(existVehicleType.getVehicleClass()));
                existVehicleType.setVehicleSize(Optional.ofNullable(vehicleType.getVehicleSize()).orElse(existVehicleType.getVehicleSize()));
                
        }
        return existVehicleType;
    }

    public Location updateLocationById (int locationId, Vehicle vehicle) {

        Location existLocation = locationRepository.findById(locationId).get();
        Location location = vehicle.getLocation();

        if(existLocation != null) {
            existLocation.setContactNumber(Optional.ofNullable(location.getContactNumber()).orElse(existLocation.getContactNumber()));
            existLocation.setName(Optional.ofNullable(location.getName()).orElse(existLocation.getName()));
            existLocation.setVehicleCapacity(Optional.ofNullable(location.getVehicleCapacity()).orElse(existLocation.getVehicleCapacity()));
        
            int addressId = existLocation.getAddress().getId();
            addressRepository.save(this.updateAddressById (addressId, vehicle));
        }
        //addressRepository.save(existVehicle.getLocation().getAddress());
        return existLocation;
    }

    public Address updateAddressById (int addressId, Vehicle vehicle) { 

        Address existAddress = addressRepository.findById(addressId).get();
        Address address = vehicle.getLocation().getAddress();

        if(existAddress != null) {
            // Optional.ofNullable(location.getContactNumber()).orElse(existLocation.getContactNumber())
            existAddress.setCity(Optional.ofNullable(address.getCity()).orElse(existAddress.getCity()));
            existAddress.setState(Optional.ofNullable(address.getState()).orElse(existAddress.getState()));
            existAddress.setStreet(Optional.ofNullable(address.getStreet()).orElse(existAddress.getStreet()));
            existAddress.setZipCode(Optional.ofNullable(address.getZipCode()).orElse(existAddress.getZipCode()));
        }
        return existAddress;
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