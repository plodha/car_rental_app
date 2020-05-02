package themeansquare.controller;

import themeansquare.model.Customer;
import themeansquare.model.Location;
import themeansquare.model.Vehicle;
import themeansquare.model.Invoice;
import themeansquare.service.IReservation;
import themeansquare.service.internal.ReserveVehicle;
import themeansquare.repository.CustomerRepository;
import themeansquare.repository.LocationRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.repository.InvoiceRepository;

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
/*
ActualDropOffTime	datetime(6) NULL	
EstimateDropOffTime	datetime(6)	
EstimatedPrice	double	
PickUpTime	datetime(6)	
Customer	int	
Invoice	int	
Location	int	
Vehicle	int
*/
@RestController
public class ReserveVehicleController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping("/reservation")
    public String addReservation(@RequestParam(value = "actualDropOffTime") String actualDropOffTime,
            @RequestParam(value = "estimateDropOffTime") String estimateDropOffTime, 
            @RequestParam(value = "estimatedPrice") Double estimatedPrice,
            @RequestParam(value = "pickUpTime") String pickUpTime, 
            @RequestParam(value = "vehicleId") int vehicleId,
            @RequestParam(value = "vehicleTypeId") int vehicleTypeId,
            @RequestParam(value = "locationId") int locationId,
            @RequestParam(value = "status") Boolean status) throws Exception {

        IReservation reserve = new ReserveVehicle(actualDropOffTime, estimateDropOffTime, estimatedPrice, pickUpTime, status,
                                              vehicleId,vehicleTypeId, locationId,
                                              customerRepository, locationRepository,vehicleRepository, invoiceRepository);
        String response = reserve.addReservation();
        return response;
    }
    
}