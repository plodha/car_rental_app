package themeansquare.service.internal;

import themeansquare.service.IReservation;
import themeansquare.model.Customer;
import themeansquare.model.Location;
import themeansquare.model.Reservation;
import themeansquare.model.Vehicle;
import themeansquare.model.Invoice;
import themeansquare.service.internal.ReserveVehicle;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.LocationRepository;
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
import java.util.Calendar;
import java.util.Date;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReserveVehicle implements IReservation {
    
    private CustomerRepository customerRepository;
    private LocationRepository locationRepository;
    private VehicleRepository vehicleRepository;
    private InvoiceRepository invoiceRepository;
    private ReservationRepository reservationRepository;

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
                          ReservationRepository reservationRepository) {
        this.customerRepository = customerRepository; 
        this.locationRepository = locationRepository;
        this.vehicleRepository = vehicleRepository; 
        this.invoiceRepository = invoiceRepository;
        this.reservationRepository = reservationRepository;
    }

    public ReserveVehicle(String ActualDropOffTime, String EstimateDropOffTime,Double estimatedPrice,String PickUpTime, Boolean status,
                          Double damageFee,Double estimatedPriceInvoice,Double lateFee,Double totalPrice,                      
                          int customerId, int vehicleId, int vehicleTypeId, int locationId, 
                          CustomerRepository customerRepository, LocationRepository locationRepository,
                          VehicleRepository vehicleRepository, InvoiceRepository invoiceRepository,
                          ReservationRepository reservationRepository) {

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
    }

    public String addReservation(Reservation newReservation) throws Exception {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");

        Reservation reservation = new Reservation();
        reservation.setActualDropOffTime(newReservation.getActualDropOffTime());
        reservation.setEstimateDropOffTime(newReservation.getEstimateDropOffTime());
        reservation.setEstimatedPrice(newReservation.getEstimatedPrice());
        reservation.setPickUpTime(newReservation.getPickUpTime());
        reservation.setStatus(newReservation.isStatus());

        reservation.setCustomer(newReservation.getCustomer());
        reservation.setVehicle(newReservation.getVehicle());
        reservation.setLocation(newReservation.getLocation());
        reservation.setInvoice(this.createInvoice(newReservation));
        reservationRepository.save(reservation);
        
        response.put("status", "200");
        return this.convertMapToJson(response);
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

    public String addReservationOld() throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");

        Reservation reservation = new Reservation();
        Date ActualDropOffTimeformat= new SimpleDateFormat("yyyy-MM-dd").parse(this.actualDropOffTime);
        reservation.setActualDropOffTime(ActualDropOffTimeformat);
        Date EstimateDropOffTimeformat= new SimpleDateFormat("yyyy-MM-dd").parse(this.estimateDropOffTime);
        reservation.setEstimateDropOffTime(EstimateDropOffTimeformat);
        reservation.setEstimatedPrice(estimatedPrice);
        Date setPickUpTimeformat= new SimpleDateFormat("yyyy-MM-dd").parse(this.pickUpTime);
        reservation.setPickUpTime(setPickUpTimeformat);
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

    //cancel reservation
    public String cancelReservation(Integer id) throws Exception {

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "400");

        if (reservationRepository.existsById(id)) {
            Reservation existReserve = reservationRepository.findById(id).get();
            if(existReserve != null) {
                //reservation status = 1 -active ; 0 =cancel
                existReserve.setStatus(false);
                Vehicle existVehicle = existReserve.getVehicle();
                if(existVehicle != null) {
                    //vehicle status = true = free for reservation
                    existVehicle.setStatus(true);
                    existReserve.setVehicle(existVehicle);
                }
                reservationRepository.save(existReserve);
                response.put("status", "200");
            }
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