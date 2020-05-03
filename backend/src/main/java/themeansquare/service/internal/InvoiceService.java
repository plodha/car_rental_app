package themeansquare.service.internal;

import themeansquare.service.IInvoice;
import themeansquare.model.Customer;
import themeansquare.model.Location;
import themeansquare.model.Reservation;
import themeansquare.model.Vehicle;
import themeansquare.model.Invoice;
import themeansquare.service.internal.ReserveVehicle;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.PriceRepository;
import themeansquare.repository.ReservationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.InvoiceRepository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InvoiceService implements IInvoice {

    private CustomerRepository customerRepository;
    private LocationRepository locationRepository;
    private VehicleRepository vehicleRepository;
    private InvoiceRepository invoiceRepository;
    private ReservationRepository reservationRepository;
    private PriceRepository priceRepository;

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

    public InvoiceService( CustomerRepository customerRepository, LocationRepository locationRepository,
                          VehicleRepository vehicleRepository, InvoiceRepository invoiceRepository,
                          ReservationRepository reservationRepository, PriceRepository priceRepository) {
        this.customerRepository = customerRepository; 
        this.locationRepository = locationRepository;
        this.vehicleRepository = vehicleRepository; 
        this.invoiceRepository = invoiceRepository;
        this.reservationRepository = reservationRepository;
        this.priceRepository = priceRepository;
    }

    public InvoiceService(String ActualDropOffTime, String EstimateDropOffTime,Double estimatedPrice,String PickUpTime, Boolean status,
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

    //final invoice computation
    /*
        Total Price = Late Fee + Damage Fee + Estimated fee
        1. compute actual -estimated time diff in hour
        2. compute late fee based on that 
        3. compute all type of damage fee for a vehicle type
        4. compute total price
        5. update reservation with the actualdropoff time 
    */
    public String computeInvoice(Integer reservationId, String actualDropOffTime, Boolean IsDamage,String[] damageId) throws Exception {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");
        if (reservationRepository.existsById(reservationId)) {
            Reservation existReserve = reservationRepository.findById(reservationId).get();
            String estimateDropOffTime = existReserve.getEstimateDropOffTime();
            /// time diff in hour
            double diffHours = DateDiff(actualDropOffTime,estimateDropOffTime);
            //double hourlyPrice = existReserve.
            System.out.println("diffHours "+ diffHours);
            Invoice invoice = existReserve.getInvoice();
        }

        response.put("status", "200");
        return this.convertMapToJson(response);
    }

    public double DateDiff(String actualDropOffTime, String estimateDropOffTime) {
            String dateStart = actualDropOffTime;//"1/15/2020 10:57:03 AM";
            String dateStop = estimateDropOffTime;//"1/15/2020 9:57:03 AM";

            // Custom date format
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");  

            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(dateStart);
                d2 = format.parse(dateStop);
            } catch (ParseException e) {
                e.printStackTrace();
            }    

            // Get msec from each, and subtract.
            double diff =  d1.getTime() - d2.getTime();
            double diffSeconds = diff / 1000 % 60;  
            double diffMinutes = diff / (60 * 1000) % 60; 
            double diffHours = diff / (60 * 60 * 1000);                      
            System.out.println("Time in seconds: " + diffSeconds + " seconds.");         
            System.out.println("Time in minutes: " + diffMinutes + " minutes.");         
            System.out.println("Time in hours: " + diffHours + " hours.");

            return diffHours;
    }

    public boolean isValidDate(String dateString) {
        List<String> formatStrings = Arrays.asList( "MM/dd/yyyy hh:mm:ss a", "MM/dd/yyyy", "M/y", "M/d/y", "M-d-y", "MMM-dd","dd-MMM", "dd-MMM-yyyy");
        for (String formatString : formatStrings)
        {
            try{
                 new SimpleDateFormat(formatString).parse(dateString);
                 return true;
            }
            catch (ParseException e) {
                
            }
        }
        return false;
    }

    public String convertMapToJson(HashMap<String, String> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResponse = "{\"Status\" : 400, \"error_message\" : \"convertMapToJson failed. InvoiceService.java file\"}";
        }
    
        return jsonResponse;
    }
    
}