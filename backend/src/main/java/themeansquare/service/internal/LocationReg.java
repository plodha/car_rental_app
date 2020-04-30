package themeansquare.service.internal;

import themeansquare.service.ILocationReg;
import themeansquare.model.Address;
import themeansquare.model.Location;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.AddressRepository;


import java.util.HashMap;
import java.util.Optional;
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

    // validation logic for location api
    public String addLocation() throws Exception {

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
        Location existLocation = locationRepository.findById(id).get();

        if(existLocation != null) {
            existLocation.setContactNumber(Optional.ofNullable(location.getContactNumber()).orElse(existLocation.getContactNumber()));
            existLocation.setName(Optional.ofNullable(location.getName()).orElse(existLocation.getName()));
            existLocation.setVehicleCapacity(Optional.ofNullable(location.getVehicleCapacity()).orElse(existLocation.getVehicleCapacity()));
        
            int addressId = existLocation.getAddress().getId();
            addressRepository.save(this.updateAddressById (addressId, location));
            locationRepository.save(existLocation);
            response.put("status", "200");
        }
        return this.convertMapToJson(response);
    }

    public Address updateAddressById (int addressId, Location location) { 

        Address existAddress = addressRepository.findById(addressId).get();
        Address address = location.getAddress();

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