package themeansquare.service.internal;

import themeansquare.service.ILocationReg;
import themeansquare.model.Address;
import themeansquare.model.Location;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.VehicleTypeRepository;
import themeansquare.repository.AddressRepository;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationReg implements ILocationReg {

    private LocationRepository locationRepository;
    private AddressRepository addressRepository;
    
    //Location
    private int contactNumber;
    private String name;	
    private int vehicleCapacity;
    
    //Address
    private String street;
    private String city;
    private String state;
    private String zipcode;

    public LocationReg( LocationRepository locationRepository, AddressRepository addressRepository ) {
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
    }

    public LocationReg(int contactNumber, String name, int vehicleCapacity,
                       String street, String city,String state, String zipcode,
                       LocationRepository locationRepository, AddressRepository addressRepository) {

        this.contactNumber = contactNumber;
        this.name = name;
        this.vehicleCapacity = vehicleCapacity;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
    }

    // validation logic for vehicle api
    public String addVehicle() throws Exception {

        HashMap<String, String> response = new HashMap<>();
        //response.put("isLicensePlateAvailable", "true");
        response.put("status", "400");

        Location location = new Location();

        location.setContactNumber(contactNumber);
        location.setName(name);
        location.setVehicleCapacity(vehicleCapacity);
        location.setAddress(this.createAddress());
        locationRepository.save(location);

        response.put("status", "200");
        return this.convertMapToJson(response);
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

    //get-all api 
    public Iterable<Location> getLocations() throws Exception{
        
        HashMap<String, String> response = new HashMap<>();
        Iterable<Location> itr = locationRepository.findAll();
        response.put("status", "400");
        if(itr != null){
            response.put("status", "200");
            return  itr;
        }
        return null;
    }

    //get by id api
    public Optional<Location> getLocationById(Integer id) throws Exception {

        return locationRepository.findById(id);
    }

    //delete api
    public String delLocation(Integer id) throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            response.put("status", "200");
        }
        return this.convertMapToJson(response);
    }

    //for update api
    public String updateLocationById(Integer id, Location location) throws Exception {

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