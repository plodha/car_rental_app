package themeansquare.service.internal;

import themeansquare.service.IInvoice;
import themeansquare.model.Customer;
import themeansquare.model.Damage;
import themeansquare.model.Location;
import themeansquare.model.Price;
import themeansquare.model.Reservation;
import themeansquare.model.Vehicle;
import themeansquare.model.Invoice;
import themeansquare.service.internal.ReserveVehicle;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.DamageRepository;
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
    private DamageRepository damageRepository;

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
                          ReservationRepository reservationRepository, PriceRepository priceRepository,
                          DamageRepository damageRepository) {
        this.customerRepository = customerRepository; 
        this.locationRepository = locationRepository;
        this.vehicleRepository = vehicleRepository; 
        this.invoiceRepository = invoiceRepository;
        this.reservationRepository = reservationRepository;
        this.priceRepository = priceRepository;
        this.damageRepository = damageRepository;
    }

    public InvoiceService(String ActualDropOffTime, String EstimateDropOffTime,Double estimatedPrice,String PickUpTime, Boolean status,
                          Double damageFee,Double estimatedPriceInvoice,Double lateFee,Double totalPrice,                      
                          int customerId, int vehicleId, int vehicleTypeId, int locationId, 
                          CustomerRepository customerRepository, LocationRepository locationRepository,
                          VehicleRepository vehicleRepository, InvoiceRepository invoiceRepository,
                          ReservationRepository reservationRepository, PriceRepository priceRepository,
                          DamageRepository damageRepository) {

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
        this.damageRepository = damageRepository;
    }

    //final invoice computation
    /*
        Total Price = Late Fee + Damage Fee + Estimated fee
        1. compute actual -estimated time diff in hour
        2. compute late fee based on that 
        3. compute all type of damage fee for a vehicle type
        4. compute total price
        5. update invoice damage fee
        6. update the vehicle table status as free
        7. update reservation with the actualdropoff time 
        
    */
    public String computeInvoice(Integer reservationId, String actualDropOffTime, Boolean IsDamage,String[] damageId) throws Exception {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");
        if (reservationRepository.existsById(reservationId)) {
            Reservation existReserve = reservationRepository.findById(reservationId).get();
            if(existReserve != null) {
                String estimateDropOffTime = existReserve.getEstimateDropOffTime();
                ///1 time diff in hour
                double diffHours = DateDiff(actualDropOffTime,estimateDropOffTime);
                Vehicle existVehicle = existReserve.getVehicle();
                if(existVehicle != null) {
                    /// get vehicleType
                    int vehicleTypeId = existVehicle.getVehicleTypeId().getId();

                    ///2 get latefee and compute total late fee
                    double lateFeeHourly = 0.0;
                    double totalLateFee = 0.0;
                    if (diffHours > 0.0) {
                        lateFeeHourly = getLateFeeForVehicleType(vehicleTypeId);
                        totalLateFee = diffHours * lateFeeHourly;
                        System.out.println("lateFeeHourly "+ lateFeeHourly);
                        System.out.println("totalLateFee "+ totalLateFee);
                    }
                    ///3 compute total damage fee for a vehicleType
                    double totalDamageFee = 0.0;
                    if(IsDamage) {
                        totalDamageFee = getTotalDamageFeeForVehicleType(vehicleTypeId,damageId);
                    }
                    
                    /// 4, 5 get invoce id and update the total latefee and total fee
                    Invoice invoice = existReserve.getInvoice();
                    double totalPrice = invoice.getEstimatedPrice() + totalLateFee + totalDamageFee;
                    invoice.setLateFee(totalLateFee);
                    invoice.setDamageFee(totalDamageFee);
                    invoice.setTotalPrice(totalPrice);
                    invoiceRepository.save(invoice);

                    ///6 vehicle status = true = free for reservation
                    existVehicle.setStatus(true);
                    existReserve.setVehicle(existVehicle);
                    vehicleRepository.save(existVehicle);
                }
                ///7 update reservation with the actualdropoff time
                existReserve.setActualDropOffTime(actualDropOffTime);
                reservationRepository.save(existReserve);
                response.put("status", "200");
            }
              
        }

        response.put("status", "200");
        return this.convertMapToJson(response);
    }

    public double getTotalDamageFeeForVehicleType(int vehicleTypeId, String[] damageId) {
        double totalDamageFee = 0.0;
        Iterable<Damage> itr = damageRepository.findAll();
        Iterator iter = itr.iterator();
        System.out.println("vehicleTypeId " + vehicleTypeId);
        while(iter.hasNext()){
            Damage tempDamage = (Damage) iter.next();
            System.out.println("tempPrice.getVehicleTypeId().getId() " + tempDamage.getVehicleTypeId().getId());
            //tempVehicle.isStatus() = false = occupied vehicle
            if((tempDamage.getVehicleTypeId().getId() == vehicleTypeId)) {
                for (String strTemp : damageId) {
                    if(tempDamage.getId() == Integer.parseInt(strTemp)) {
                        totalDamageFee = totalDamageFee + tempDamage.getDamageFee();
                    }    
                }
            }
        }
        return totalDamageFee;
    }

    public double getHourlyPriceForVehicleType(int vehicleTypeId) {

        Iterable<Price> itr = priceRepository.findAll();
        Iterator iter = itr.iterator();
        System.out.println("vehicleTypeId " + vehicleTypeId);
        while(iter.hasNext()){
            Price tempPrice = (Price) iter.next();
            System.out.println("tempPrice.getVehicleTypeId().getId() " + tempPrice.getVehicleTypeId().getId());
            //tempVehicle.isStatus() = false = occupied vehicle
            if((tempPrice.getVehicleTypeId().getId() == vehicleTypeId)) {
                System.out.println("remove tempVehicle.getId() "+ tempPrice.getId());
                return tempPrice.getHourlyPrice();
            }
        }
        return 0.0;
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

    public double DateDiff(String actualDropOffTime, String estimateDropOffTime) {
            String dateStart = actualDropOffTime;//"1/15/2020 10:57";
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
            double diff =  d1.getTime() - d2.getTime();
            double diffSeconds = diff / 1000 % 60;  
            double diffMinutes = diff / (60 * 1000) % 60; 
            double diffHours = diff / (60 * 60 * 1000);                      
            //System.out.println("Time in seconds: " + diffSeconds + " seconds.");         
            //System.out.println("Time in minutes: " + diffMinutes + " minutes.");         
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

    //get-all api 
    public Iterable<Invoice> getInvoices() throws Exception {
        
        HashMap<String, String> response = new HashMap<>();
        Iterable<Invoice> itr = invoiceRepository.findAll();
        response.put("status", "400");
        if(itr != null){
            response.put("status", "200");
            return  itr;
        }
        return null;
    }

    //get by id api
    public Optional<Invoice> getInvoiceById(Integer id) throws Exception {

        return invoiceRepository.findById(id);
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