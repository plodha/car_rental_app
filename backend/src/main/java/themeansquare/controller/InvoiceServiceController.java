package themeansquare.controller;

import themeansquare.service.IInvoice;
import themeansquare.service.IReservation;
import themeansquare.model.Customer;
import themeansquare.model.Location;
import themeansquare.model.Reservation;
import themeansquare.model.Vehicle;
import themeansquare.model.Invoice;
import themeansquare.service.internal.InvoiceService;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.InvoiceRepository;
import themeansquare.repository.ReservationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceServiceController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ReservationRepository reservationRepository;

     //final invoice computation
     //reservation id, damage id[], IsDamage, actualdropofftime, 
     //late fee = estimated -actual > 1hour 
     @PutMapping("/reservationCancel/{id}")
     public String cancelReservation (@RequestParam(value = "actualDropOffTime") String actualDropOffTime,
             @RequestParam(value = "estimateDropOffTime") String estimateDropOffTime, 
             @RequestParam(value = "estimatedPrice") Double estimatedPrice,
             @RequestParam(value = "pickUpTime") String pickUpTime,
             @RequestParam(value = "status") Boolean status,
             @RequestParam(value = "damageFee") Double damageFee,
             @RequestParam(value = "estimatedPrice") Double estimatedPriceInvoice,
             @RequestParam(value = "lateFee") Double lateFee,
             @RequestParam(value = "totalPrice") Double totalPrice, 
             @RequestParam(value = "customerId") int customerId,
             @RequestParam(value = "vehicleId") int vehicleId,
             @RequestParam(value = "vehicleTypeId") int vehicleTypeId,
             @RequestParam(value = "locationId") int locationId,
             @PathVariable Integer id) throws Exception {
 
             IInvoice invoice = new InvoiceService  ( actualDropOffTime, estimateDropOffTime, estimatedPrice, pickUpTime, status,
                                                     damageFee,estimatedPriceInvoice,lateFee,totalPrice,customerId,vehicleId,vehicleTypeId, locationId,
                                                 customerRepository, locationRepository,vehicleRepository, invoiceRepository,reservationRepository);
             String response = invoice.computeInvoice(id);  
             return response;  
     }
}