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
import themeansquare.repository.DamageRepository;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.PriceRepository;
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
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private DamageRepository damageRepository;

     //final invoice computation
     //reservation id, damage id[], IsDamage, actualdropofftime, front end will send date format =1/15/2020 10:57
     //late fee = estimated -actual > 1hour 
     /*from postman:
      for single damage id: 
        http://localhost:8080/computeInvoice/1?actualDropOffTime=1/15/2020 4:57&reservationId=1&IsDamage=true
      for multiple damage id:
      http://localhost:8080/computeInvoice/1?actualDropOffTime=1/15/2020 4:57&reservationId=1&IsDamage=true
     */
     @PutMapping("/computeInvoice/{damageId}")
     public int computeInvoice (@RequestParam(value = "actualDropOffTime") String actualDropOffTime,
             @RequestParam(value = "reservationId") Integer reservationId,
             @RequestParam(value = "IsDamage") Boolean IsDamage,
             @PathVariable(value = "damageId") String[] damageId) throws Exception {
 
             IInvoice invoice = new InvoiceService  ( customerRepository, locationRepository,vehicleRepository, 
                                                      invoiceRepository,reservationRepository,priceRepository,damageRepository);
             int invoiceId = invoice.computeInvoice(reservationId, actualDropOffTime, IsDamage,damageId);  
             return invoiceId;  
     }

     // get all invoice
        @GetMapping("/invoice")
        public Iterable<Invoice> getInvoices() throws Exception {
                IInvoice invoice = new InvoiceService  ( customerRepository, locationRepository,vehicleRepository, 
                                                      invoiceRepository,reservationRepository,priceRepository,damageRepository);
                return invoice.getInvoices();
                
        }

    // get invoice with a fixed invoice id
        @GetMapping(value = "/invoice/{id}")
        public Optional<Invoice> getInvoiceById(@PathVariable Integer id) throws Exception {
                IInvoice invoice = new InvoiceService  ( customerRepository, locationRepository,vehicleRepository, 
                                                      invoiceRepository,reservationRepository,priceRepository,damageRepository);
                return invoice.getInvoiceById(id);
        }
}