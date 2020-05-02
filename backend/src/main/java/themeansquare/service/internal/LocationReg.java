package themeansquare.service.internal;

import themeansquare.service.ILocationReg;
import themeansquare.model.Address;
import themeansquare.model.Location;
import themeansquare.model.Vehicle;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.AddressRepository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class LocationReg implements ILocationReg {

    private LocationRepository locationRepository;
    private AddressRepository addressRepository;
    private VehicleRepository vehicleRepository;
    
    //Location
    private int contactNumber;
    private String name;	
    private int vehicleCapacity;
    
    //Address
    private String street;
    private String city;
    private String state;
    private String zipcode;

    public LocationReg( LocationRepository locationRepository, AddressRepository addressRepository,VehicleRepository vehicleRepository ) {
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public LocationReg(int contactNumber, String name, int vehicleCapacity,
                       String street, String city,String state, String zipcode,
                       LocationRepository locationRepository, AddressRepository addressRepository, VehicleRepository vehicleRepository) {

        this.contactNumber = contactNumber;
        this.name = name;
        this.vehicleCapacity = vehicleCapacity;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // validation logic for location api
    public String addLocation() throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("IfLocationAvailable", "true");
        response.put("status", "400");

        //validation to check unique rental location
        if(checkIfLocationExists()){
            response.remove("IfLocationAvailable");
            Location location = new Location();
            location.setContactNumber(contactNumber);
            location.setName(name);
            location.setVehicleCapacity(vehicleCapacity);
            location.setAddress(this.createAddress());
            locationRepository.save(location);

            response.put("status", "200");
        }
        
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

    public boolean checkIfLocationExists() {
        Iterable<Location> itr = locationRepository.findAll();
        Iterator it = itr.iterator();
        
        while (it.hasNext()) {
            Location location = (Location) it.next();
            System.out.println(location.getName()); 
            if (location.getName().equals(this.name)) {
                return false;
            }
        }
        return true;
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
        Iterable<Vehicle> itr = vehicleRepository.findAll();
        Iterator it = itr.iterator();

        // delete all the vehicles of that location first
        while (it.hasNext()) {
            Vehicle vehicle = (Vehicle) it.next();
            System.out.println("vehicle.getId() "+vehicle.getId()); 
            System.out.println("id.intValue() " + id.intValue());
            if( vehicle.getLocation().getId() == id.intValue()) {
                vehicleRepository.deleteById(vehicle.getId());
            }
        }
        // after that delete the location
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
        
        if (locationRepository.existsById(id)) {
            Location existLocation = locationRepository.findById(id).get();
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
        Address address = location.getAddress();
        Address existAddress = null;
        if(addressRepository.existsById(addressId)) {
            existAddress = addressRepository.findById(addressId).get();
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