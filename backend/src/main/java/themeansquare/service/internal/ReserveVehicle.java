package themeansquare.service.internal;

import themeansquare.service.IReservation;
import themeansquare.model.Customer;
import themeansquare.model.Location;
import themeansquare.model.Price;
import themeansquare.model.Reservation;
import themeansquare.model.Vehicle;
import themeansquare.model.VehicleType;
import themeansquare.model.Invoice;
import themeansquare.service.internal.ReserveVehicle;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.PriceRepository;
import themeansquare.repository.ReservationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.VehicleTypeRepository;
import themeansquare.repository.InvoiceRepository;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

public class ReserveVehicle implements IReservation {
    
    private CustomerRepository customerRepository;
    private LocationRepository locationRepository;
    private VehicleRepository vehicleRepository;
    private InvoiceRepository invoiceRepository;
    private ReservationRepository reservationRepository;
    private PriceRepository priceRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    private String actualDropOffTime; 
    private String estimateDropOffTime;
    private Double estimatedPrice;
    private String pickUpTime;
    private Boolean status;
    private Double damageFee;
    private Double estimatedPriceInvoice;
    private Double lateFee;
    private Double totalPrice;
    private int customerId;
    private int vehicleId;
    private int locationId;
    private int vehicleTypeId;

    public ReserveVehicle( CustomerRepository customerRepository, LocationRepository locationRepository,
                          VehicleRepository vehicleRepository, InvoiceRepository invoiceRepository,
                          ReservationRepository reservationRepository, PriceRepository priceRepository) {
        this.customerRepository = customerRepository; 
        this.locationRepository = locationRepository;
        this.vehicleRepository = vehicleRepository; 
        this.invoiceRepository = invoiceRepository;
        this.reservationRepository = reservationRepository;
        this.priceRepository = priceRepository;
    }

    public ReserveVehicle(String ActualDropOffTime, String EstimateDropOffTime,Double estimatedPrice,String PickUpTime, Boolean status,
                          Double damageFee,Double estimatedPriceInvoice,Double lateFee,Double totalPrice,                      
                          int customerId, int vehicleId, int vehicleTypeId, int locationId, 
                          CustomerRepository customerRepository, LocationRepository locationRepository,
                          VehicleRepository vehicleRepository, InvoiceRepository invoiceRepository,
                          ReservationRepository reservationRepository, PriceRepository priceRepository) {

        this.actualDropOffTime= ActualDropOffTime;
        this.estimateDropOffTime = EstimateDropOffTime;
        this.estimatedPrice = estimatedPrice;
        this.pickUpTime = PickUpTime;
        this.status = status;
        this.damageFee =damageFee;
        this.estimatedPriceInvoice = estimatedPriceInvoice;
        this.lateFee = lateFee;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.vehicleId = vehicleId; 
        this.vehicleTypeId = vehicleTypeId; 
        this.locationId = locationId;
        this.customerRepository = customerRepository; 
        this.locationRepository = locationRepository;
        this.vehicleRepository = vehicleRepository; 
        this.invoiceRepository = invoiceRepository;
        this.reservationRepository = reservationRepository;
        this.priceRepository = priceRepository;
    }

    /*
        1. add reservation
        2. front end will pass invoice id=0, don't use it, create new reservation
        3. set status =0 for vehicle table to make vehicle unavailable
    */
    public String addReservation(Reservation newReservation) throws Exception {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");

        Reservation reservation = new Reservation();
        //for first time actualDropOff time is empty, when vehicle is return, will update actualDropOff time
        reservation.setActualDropOffTime("");
        reservation.setEstimateDropOffTime(newReservation.getEstimateDropOffTime());
        reservation.setEstimatedPrice(newReservation.getEstimatedPrice());
        reservation.setPickUpTime(newReservation.getPickUpTime());
        reservation.setStatus(newReservation.isStatus());

        reservation.setCustomer(newReservation.getCustomer());
        reservation.setVehicle(this.updateVehicleForReservation(newReservation.getVehicle()));
        reservation.setLocation(newReservation.getLocation());
        reservation.setInvoice(this.createInvoice(newReservation));
        reservationRepository.save(reservation);
        
        response.put("status", "200");
        return this.convertMapToJson(response);
    }

    //set status =0 for vehicle table to make vehicle unavailable
    private Vehicle updateVehicleForReservation (Vehicle vehicle) {

        int vehicleId = vehicle.getId();
        if (vehicleRepository.existsById(vehicleId)) {
            vehicle.setStatus(false);
            //vehicleRepository.save(vehicle);
        }
        //Vehicle existVehicle = vehicleRepository.findById(id).get();
        return vehicle;
    }

    private Invoice createInvoice(Reservation newReservation) {
        Invoice invoice = new Invoice();
        invoice.setDamageFee(newReservation.getInvoice().getDamageFee());
        invoice.setEstimatedPrice(newReservation.getInvoice().getEstimatedPrice());
        invoice.setLateFee(newReservation.getInvoice().getLateFee());
        invoice.setTotalPrice(newReservation.getInvoice().getTotalPrice());
        invoiceRepository.save(invoice);

        return invoice;
    }

    //can be used for front end
    public String addReservationOld() throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");

        Reservation reservation = new Reservation();
        //for first time actualDropOff time is empty
        reservation.setActualDropOffTime("");
        reservation.setEstimateDropOffTime(this.estimateDropOffTime);
        reservation.setEstimatedPrice(estimatedPrice);
        reservation.setPickUpTime(this.pickUpTime);
        reservation.setStatus(status);

        reservation.setCustomer(customerRepository.findById(customerId).get());
        reservation.setVehicle(vehicleRepository.findById(vehicleId).get());
        reservation.setLocation(locationRepository.findById(locationId).get());
        reservation.setInvoice(this.createInvoiceOld());
        reservationRepository.save(reservation);
        response.put("status", "200");
        return this.convertMapToJson(response);
    }

    private Invoice createInvoiceOld() {
        Invoice invoice = new Invoice();
        invoice.setDamageFee(damageFee);
        invoice.setEstimatedPrice(estimatedPriceInvoice);
        invoice.setLateFee(lateFee);
        invoice.setTotalPrice(totalPrice);
        invoiceRepository.save(invoice);

        return invoice;
    }


    //get-all api 
    public Iterable<Reservation> getReservations() throws Exception {
        
        HashMap<String, String> response = new HashMap<>();
        Iterable<Reservation> itr = reservationRepository.findAll();
        response.put("status", "400");
        if(itr != null){
            response.put("status", "200");
            return  itr;
        }
        return null;
    }

    //get Reservation by id api
    public Optional<Reservation> getReservationById(Integer id) throws Exception {

        return reservationRepository.findById(id);
    }

    // get reservation with a fixed customer id
    public Iterable<Reservation> getReservationByCustomerId(Integer customerId) throws Exception {

        Iterable<Reservation> itr = reservationRepository.findAll();
        Iterator iter = itr.iterator();
        while(iter.hasNext()){
            Reservation tempReservation = (Reservation) iter.next();
            //tempReservation.isStatus() = true = active reservation
            if(!tempReservation.isStatus() || (tempReservation.getCustomer().getId() != customerId)) {
               // System.out.println("tempPrice.getVehicleTypeId() "+ tempPrice.getVehicleTypeId().getId());
                iter.remove();
            }
        }
        
        return itr;

    }

    //cancel reservation
    /*
        logic for late fee: get the vehicle type
               get the late fee for that vehicle type
               then update invoice table for latefee and total fee
               update EstimatedFee =0 in reservation table **
               vehicle status = true = free for reservation
               update reservation status =  0 =cancel
    */
    public String cancelReservation(Integer reservationId, Boolean isLatefee) throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");
        
        if (reservationRepository.existsById(reservationId)) {
            Reservation existReserve = reservationRepository.findById(reservationId).get();
            if(existReserve != null) {
                ///late fee computation for cancelation
                if(isLatefee) {
                    Vehicle existVehicle = existReserve.getVehicle();
                    if(existVehicle != null) {
                        ///1 get vehicleType
                        int vehicleTypeId = existVehicle.getVehicleTypeId().getId();
                        ///2 get late fee
                        double lateFee = getLateFeeForVehicleType(vehicleTypeId);
                        System.out.println("lateFee "+ lateFee);

                        ///3 get invoce id and update latefee and total fee
                        Invoice invoice = existReserve.getInvoice();
                        invoice.setEstimatedPrice(0.0);
                        invoice.setLateFee(lateFee);
                        invoice.setTotalPrice(lateFee);
                        invoiceRepository.save(invoice);

                        ///4 vehicle status = true = free for reservation
                        existVehicle.setStatus(true);
                        existReserve.setVehicle(existVehicle);
                        vehicleRepository.save(existVehicle);
                    }
                }
                else {
                    
                    Vehicle existVehicle = existReserve.getVehicle();
                    if(existVehicle != null) {
                         ///3 get invoce id and update estimated price =0
                         Invoice invoice = existReserve.getInvoice();
                         invoice.setEstimatedPrice(0.0);

                        ///vehicle status = true = free for reservation
                        existVehicle.setStatus(true);
                        existReserve.setVehicle(existVehicle);
                        vehicleRepository.save(existVehicle);
                    }
                }
                ///reservation status =  0 =cancel
                existReserve.setStatus(false);
                ///update EstimatedFee =0 in reservation table
                existReserve.setEstimatedPrice(0.0);
                reservationRepository.save(existReserve);
                response.put("status", "200");
            }
        }
        return this.convertMapToJson(response);
    }

    public double getLateFeeForVehicleType(int vehicleTypeId) {

        Iterable<Price> itr = priceRepository.findAll();
        Iterator iter = itr.iterator();
        System.out.println("vehicleTypeId " + vehicleTypeId);
        while(iter.hasNext()){
            Price tempPrice = (Price) iter.next();
            System.out.println("tempPrice.getVehicleTypeId().getId() " + tempPrice.getVehicleTypeId().getId());
            //tempVehicle.isStatus() = false = occupied vehicle
            if((tempPrice.getVehicleTypeId().getId() == vehicleTypeId)) {
                System.out.println("remove tempVehicle.getId() "+ tempPrice.getId());
                return tempPrice.getLateFee();
            }
        }
        return 0.0;
    }

    //get and compute the estimated prices for all the available vehicles in a location
    public String getEstimatedPriceForVehicles (Integer locationId, String pickUpTime, String estimatedDropOffTime) throws Exception {
        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();

        Iterable<Vehicle> itr = this.getVehicleByLocation(locationId);
        Iterator iter = itr.iterator();
        while(iter.hasNext()){
            HashMap<String, String> row = new HashMap<String, String>();

            Vehicle tempVehicle = (Vehicle) iter.next();
            VehicleType tempVehicleType = tempVehicle.getVehicleTypeId();

            /// getting two range of price for vehicle types of a vehicle: location>>vehicle>>vehicletype>>price
            Iterable<Price> itr_price = this.getPriceListForVehicleType(tempVehicleType.getId());
            Iterator iter_price = itr_price.iterator();
            Price price_5hr = (Price) iter_price.next();
            Price price_10hr = (Price) iter_price.next();

            double hrDiff = this.DateDiff(pickUpTime,estimatedDropOffTime);  
            
            ///computing estimated price
            double estimatedPrice = 0.0;
            if( hrDiff >   Integer.parseInt(price_5hr.getHourlyRange())) {
                estimatedPrice = Integer.parseInt(price_5hr.getHourlyRange()) * price_5hr.getHourlyPrice() 
                                + (hrDiff - Integer.parseInt(price_5hr.getHourlyRange())) * price_10hr.getHourlyPrice();
            }
            else {
                estimatedPrice = hrDiff * price_5hr.getHourlyPrice() ;                  
            }
            System.out.println( "[" 
                                + " vehicle: " +  tempVehicle.getId()
                                + "  vehicle Type: " + tempVehicleType.getId()
                                + "  vehicle Type Name: " + tempVehicleType.getClass()
                                + "  5hr price: " + price_5hr.getHourlyPrice()
                                + "  10hr price: " + price_10hr.getHourlyPrice()
                                + "  hrDiff: " + hrDiff
                                + "  estimatedPrice: " +estimatedPrice
                                +" ]");
            row.put("vehicleId",String.valueOf(tempVehicle.getId()));
            row.put("vehicleTypeId",String.valueOf(tempVehicleType.getId()));
            row.put("vehicleTypeClass",String.valueOf(tempVehicleType.getClass()));
            row.put("5hrPrice",String.valueOf(price_5hr.getHourlyPrice()));
            row.put("10hrPrice",String.valueOf(price_10hr.getHourlyPrice()));
            row.put("hrDiff",String.valueOf(hrDiff));
            row.put("estimatedPrice",String.valueOf(estimatedPrice));
            result.add(row);
        }

        return this.convertArrayMapToJson(result);
       // return null;
    }

    //get price list for a vehicleType
    public Iterable<Price> getPriceListForVehicleType(int vehicleTypeId)  {
        Iterable<Price> itr = priceRepository.findAll();
        Iterator iter = itr.iterator();

        while(iter.hasNext()){
            Price tempPrice = (Price) iter.next();
            //tempVehicle.isStatus() = false = occupied vehicle
            if(tempPrice.getVehicleTypeId().getId() != vehicleTypeId) {
               // System.out.println("tempPrice.getVehicleTypeId() "+ tempPrice.getVehicleTypeId().getId());
                iter.remove();
            }
        }
        return itr;
    }

    //get available vehicle for a location
    public Iterable<Vehicle> getVehicleByLocation(Integer locationId)  {

        Iterable<Vehicle> itr = vehicleRepository.findAll();
        Iterator iter = itr.iterator();
        while(iter.hasNext()){
            Vehicle tempVehicle = (Vehicle) iter.next();
            //tempVehicle.isStatus() = false = occupied vehicle
            if(!tempVehicle.isStatus() || (tempVehicle.getLocation().getId() != locationId.intValue())) {
               // System.out.println("remove tempVehicle.getId() "+ tempVehicle.getId());
                iter.remove();
            }
        }
        return itr;
    }

    //get available vehicle for a location
    public Iterable<Vehicle> getPriceByVehicleType(Integer locationId)  {

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
        double diff =   d2.getTime()- d1.getTime(); //estimatedDropOffTime -pickUpTime
        double diffSeconds = diff / 1000 % 60;  
        double diffMinutes = diff / (60 * 1000) % 60; 
        double diffHours = diff / (60 * 60 * 1000);                             
        System.out.println("Time in hours: " + diffHours + " hours.");

        return Math.ceil(diffHours);
    }

    public String convertMapToJson(HashMap<String, String> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResponse = "{\"Status\" : 400, \"error_message\" : \"convertMapToJson failed. ReserveVehicle.java file\"}";
        }
    
        return jsonResponse;
    }

    public String convertArrayMapToJson(ArrayList<HashMap<String, String>> result) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResponse = "{\"Status\" : 400, \"error_message\" : \"convertMapToJson failed. ReserveVehicle.java file\"}";
        }
    
        return jsonResponse;
    }
}