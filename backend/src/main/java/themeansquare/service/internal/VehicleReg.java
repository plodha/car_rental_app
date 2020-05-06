package themeansquare.service.internal;

import themeansquare.service.IVehicleReg;
import themeansquare.model.Vehicle;
import themeansquare.model.VehicleType;
import themeansquare.model.Address;
import themeansquare.model.Reservation;
import themeansquare.model.Location;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.ReservationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.VehicleTypeRepository;
import themeansquare.repository.AddressRepository;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




public class VehicleReg implements IVehicleReg {
    
    private VehicleRepository vehicleRepository;
    private VehicleTypeRepository vehicleTypeRepository;
    private LocationRepository locationRepository;
    private AddressRepository addressRepository;
    private ReservationRepository reservationRepository;
    
    ///// arrtibutes
    //vehicle
    private String licensePlate;	
    private String model;
    private String make;		
    private Boolean status;	
    private String vIN;
    private int year; //
    private String vehicleCondition;
    private int locationId;
    private int vehicleTypeId;
    private String currentMileage; 
    private String registrationTag;
    private String serviceDate;
    

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

    public VehicleReg(VehicleRepository vehicleRepository,VehicleTypeRepository vehicleTypeRepository, LocationRepository locationRepository, AddressRepository addressRepository,ReservationRepository reservationRepository ) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
        this.reservationRepository =reservationRepository;
    }

    public VehicleReg(  String licensePlate , String model,String make,
    Boolean status, String vIN,int year, String vehicleCondition,int locationId, int vehicleTypeId, String currentMileage, String registrationTag, String serviceDate,
    VehicleRepository vehicleRepository,VehicleTypeRepository vehicleTypeRepository, LocationRepository locationRepository, AddressRepository addressRepository,ReservationRepository reservationRepository) {

        this.licensePlate = licensePlate;
        this.model = model;
        this.make = make;
        this.status = status;
        this.vIN = vIN;
        this.year = year;
        this.vehicleCondition = vehicleCondition;
        this.locationId =locationId; 
        this.vehicleTypeId = vehicleTypeId;
        this.currentMileage = currentMileage; 
        this.registrationTag = registrationTag;
        this.serviceDate = serviceDate;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
        this.reservationRepository = reservationRepository;

    }
    public VehicleReg( String vehicleClass, int vehicleSize , String licensePlate , String model,String make,
                       Boolean status, String vIN,int year, String vehicleCondition, int contactNumber, String name, int vehicleCapacity,
                       String street, String city,String state, String zipcode, String currentMileage, String registrationTag, String serviceDate,
                       VehicleRepository vehicleRepository,VehicleTypeRepository vehicleTypeRepository, LocationRepository locationRepository, AddressRepository addressRepository,ReservationRepository reservationRepository) {

        this.vehicleClass = vehicleClass;
        this.vehicleSize = vehicleSize;
        this.licensePlate = licensePlate;
        this.model = model;
        this.make = make;
        this.status = status;
        this.vIN = vIN;
        this.year = year;
        this.vehicleCondition = vehicleCondition;
        this.contactNumber = contactNumber;
        this.name = name;
        this.vehicleCapacity = vehicleCapacity;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.currentMileage = currentMileage; 
        this.registrationTag = registrationTag;
        this.serviceDate = serviceDate;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
        this.reservationRepository = reservationRepository;
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
            vehicle.setVehicleCondition(vehicleCondition);
            vehicle.setCurrentMileage(currentMileage);
            vehicle.setRegistrationTag(registrationTag);
            vehicle.setServiceDate(serviceDate);
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
            vehicle.setVehicleCondition(vehicleCondition);
            vehicle.setCurrentMileage(currentMileage);
            vehicle.setRegistrationTag(registrationTag);
            vehicle.setServiceDate(serviceDate);
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

    //get available vehicle for a vehicleType Id
    public Iterable<Vehicle> getVehicleByVehicleType(Integer vehicleTypeId) throws Exception {

        // Optional<Vehicle> vec = vehicleRepository.findById(id)
        //                                          .filter(vehicle -> (vehicle.getVehicleTypeId().getId() == vehicleTypeId.intValue()) && vehicle.isStatus());
        
        // if(vec.isPresent()){
        //     return vec;
        // }
        // return vec.empty();

        Iterable<Vehicle> itr = vehicleRepository.findAll();
        Iterator iter = itr.iterator();
        while(iter.hasNext()){
            Vehicle tempVehicle = (Vehicle) iter.next();
            //tempVehicle.isStatus() = false = occupied vehicle
            if(!tempVehicle.isStatus() || (tempVehicle.getVehicleTypeId().getId() != vehicleTypeId.intValue())) { 
                System.out.println("remove tempVehicle.getId() "+ tempVehicle.getId());
                iter.remove();
            }
        }

        return  itr;
    }

    //get available vehicle for a location
    public Iterable<Vehicle> getVehicleByLocation(Integer locationId) throws Exception {

        //Optional<Vehicle> vec = vehicleRepository.findAll();
                                                // .filter(vehicle -> (vehicle.getLocation().getId() == locationId.intValue()) && vehicle.isStatus());
      
        Iterable<Vehicle> itr = vehicleRepository.findAll();
        Iterator iter = itr.iterator();
        while(iter.hasNext()){
            Vehicle tempVehicle = (Vehicle) iter.next();
            //tempVehicle.isStatus() = false = occupied vehicle
            if(!tempVehicle.isStatus() || (tempVehicle.getLocation().getId() != locationId.intValue())) {
                System.out.println("remove tempVehicle.getId() "+ tempVehicle.getId());
                iter.remove();
            }
        }
        return itr;
    }

    // get available vehicle for a vehicleType Id, location, pickuptime, actualdropOfftime
    /*
        1. get all vehicle for a location and vehicleType
        2. get all the active reservation
        3. for each vehicle of no.1, check overlapping date inside reservation list of no.2
        4. if 4.1 or 4.2 date diff is positive (>0), then that vehicle is available for reservation
        logic for non overlapping date checking:
        4.1. newPickUpTime > oldDropOffTime or,
        4.2. newDropoffTime < oldPickUpTime
    */
    public Iterable<Vehicle> getVehiclesAvailableForReservation (Integer locationId, Integer vehicleTypeId, String newPickUpTime, String newEstimatedDropOffTime) throws Exception {
        Iterable<Vehicle> itr = vehicleRepository.findAll();
        Iterator iter = itr.iterator();
        while(iter.hasNext()){
            Vehicle tempVehicle = (Vehicle) iter.next();
            //tempVehicle.isStatus() = false = occupied vehicle
            if((tempVehicle.getVehicleTypeId().getId() != vehicleTypeId.intValue()) || (tempVehicle.getLocation().getId() != locationId.intValue())) {
                System.out.println("remove tempVehicle.getId() "+ tempVehicle.getId());
                iter.remove();
            }
        }

        Iterable<Reservation> itr_reserve = this.getActiveReservationList();
        Iterator iter_vehicle = itr.iterator();
        while(iter_vehicle.hasNext()){ 
            Vehicle tempVehicle = (Vehicle) iter_vehicle.next();
            Iterator iter_reserve = itr_reserve.iterator();
            while(iter_reserve.hasNext()) {
                Reservation tempReservation = (Reservation) iter_reserve.next();
                String oldPickUpTime = tempReservation.getPickUpTime(); 
                String oldDropOffTime = tempReservation.getEstimateDropOffTime();  //using estimate date as actualdropOff coz vehicle is not returned yet
                System.out.println("-------");
                System.out.println("newPickUpTime "+newPickUpTime);
                System.out.println("oldDropOffTime "+ oldDropOffTime);
                Double diff_1 = this.DateDiff(newPickUpTime, oldDropOffTime); //check if: 1. newPickUpTime > oldDropOffTime
                System.out.println("-------");
                System.out.println("oldPickUpTime "+oldPickUpTime);
                System.out.println("newEstimatedDropOffTime "+ newEstimatedDropOffTime);
                Double diff_2 = this.DateDiff(oldPickUpTime, newEstimatedDropOffTime); //check if: 2. oldPickUpTime > newDropoffTime
                System.out.println("-------");

                if (diff_1 <=0 && diff_2 <= 0) {
                    //another check for iter_vehicle
                    iter_vehicle.remove(); //this vehicle is not eligible for reservation
                    break;
                }     
            } 
        }
        return itr;
    }

    //get active reservation list; status =1
    public Iterable<Reservation> getActiveReservationList()  {
        Iterable<Reservation> itr = reservationRepository.findAll();
        Iterator iter = itr.iterator();

        while(iter.hasNext()){
            Reservation tempReservation= (Reservation) iter.next();
            if(!tempReservation.isStatus()) {
               System.out.println("tempReservation.getId() "+ tempReservation.getId());
                iter.remove();
            }
        }
        return itr;
    }

    public double DateDiff(String pickUpTime, String estimateDropOffTime) {
        String dateStart = pickUpTime;//"1/15/2020 10:57";
        String dateStop = estimateDropOffTime;//"1/15/2020 9:57";

        // Custom date format
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");  

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (ParseException e) {
            e.printStackTrace();
        }    

        // Get msec from each, and subtract.
        double diff =   d1.getTime()- d2.getTime(); // pickUpTime - estimatedDropOffTime
        double diffSeconds = diff / 1000 % 60;  
        double diffMinutes = diff / (60 * 1000) % 60; 
        double diffHours = diff / (60 * 60 * 1000);                             
        System.out.println("Time diff in hours: " + diffHours + " hrs.");

        return Math.ceil(diffHours);
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
    public String updateVehicleById(Integer id, Integer vehicleTypeId, Integer locationId, Vehicle vehicle) throws Exception {

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
            existVehicle.setVehicleCondition(Optional.ofNullable(vehicle.getVehicleCondition()).orElse(existVehicle.getVehicleCondition()));
            existVehicle.setCurrentMileage(Optional.ofNullable(vehicle.getCurrentMileage()).orElse(existVehicle.getCurrentMileage()));
            existVehicle.setRegistrationTag(Optional.ofNullable(vehicle.getRegistrationTag()).orElse(existVehicle.getRegistrationTag()));
            existVehicle.setServiceDate(Optional.ofNullable(vehicle.getServiceDate()).orElse(existVehicle.getServiceDate()));
            

         
            response.put("LocationIdNotAvailable", "true");
            if (locationRepository.existsById(locationId)) {
                Location location = locationRepository.findById(locationId).get();
                existVehicle.setLocation(location);
                response.remove("LocationIdNotAvailable");
                
                response.put("VehicleTypeNotAvailable", "true");
                if (vehicleTypeRepository.existsById(vehicleTypeId)) {
                    VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId).get();
                    existVehicle.setVehicleTypeId(vehicleType); 
                    response.remove("VehicleTypeNotAvailable");
                }
            }
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
            existVehicle.setVehicleCondition(Optional.ofNullable(vehicle.getVehicleCondition()).orElse(existVehicle.getVehicleCondition()));
            existVehicle.setCurrentMileage(Optional.ofNullable(vehicle.getCurrentMileage()).orElse(existVehicle.getCurrentMileage()));
            existVehicle.setRegistrationTag(Optional.ofNullable(vehicle.getRegistrationTag()).orElse(existVehicle.getRegistrationTag()));
            existVehicle.setServiceDate(Optional.ofNullable(vehicle.getServiceDate()).orElse(existVehicle.getServiceDate()));
            

            
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