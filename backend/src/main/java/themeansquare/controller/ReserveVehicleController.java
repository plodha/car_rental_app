package themeansquare.controller;

import themeansquare.service.IReservation;
import themeansquare.model.Customer;
import themeansquare.model.Location;
import themeansquare.model.Price;
import themeansquare.model.Reservation;
import themeansquare.model.Vehicle;
import themeansquare.model.Invoice;
import themeansquare.service.internal.ReserveVehicle;
import themeansquare.repository.CustomerRepository;
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
/*
 //reservation
    ActualDropOffTime	datetime(6) NULL	
    EstimateDropOffTime	datetime(6)	
    EstimatedPrice	double	
    PickUpTime	datetime(6)	
    Customer	int	
    Invoice	int	
    Location	int	
    Vehicle	int
 //invoice
    DamageFee	double	
    EstimatedPrice	double	
    LateFee	double	
    TotalPrice
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
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PriceRepository priceRepository;

    /**
        Input must be in this format (fields can be missing for some like id):
            {
                "status": true,
                "location": {
                    
                    "id": 2
                   
                },
                "estimatedPrice": 5000,
                "customer": {
                   
                    "id": 1
                },
                "invoice": {
                    "id": 1
                },
                "pickUpTime": "1/15/2020 9:57",
                "vehicle": {
                    "id": 1
                },
                "actualDropOffTime": "1/15/2020 9:57",
                "estimateDropOffTime": "1/15/2020 9:57"
            }
        Use case:
            This is used by the user to for reservation confirmation. After success, the reservation is confirmed.
        
        Response:
            Success:
                response.put("status", "200");
            Failure:
                response.put("status", "400");
     */

    @PostMapping("/reservation")
    public String register(@RequestBody Reservation newReservation) throws Exception {
        
        IReservation res = new ReserveVehicle(customerRepository, locationRepository,vehicleRepository, 
                                              invoiceRepository,reservationRepository, priceRepository);
        String response = res.addReservation(newReservation);

        return response;
	}

    //can be used for frontend
    @PostMapping("/reservationNew")
    public String addReservation (@RequestParam(value = "actualDropOffTime") String actualDropOffTime,
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
            @RequestParam(value = "locationId") int locationId) throws Exception {

        IReservation reserve = new ReserveVehicle(actualDropOffTime, estimateDropOffTime, estimatedPrice, pickUpTime, status,
                                                damageFee,estimatedPriceInvoice,lateFee,totalPrice,customerId,vehicleId,vehicleTypeId, locationId,
                                              customerRepository, locationRepository,vehicleRepository, invoiceRepository,reservationRepository,priceRepository);
        String response = reserve.addReservationOld();
        return response;
    }

    // get all reservations
    @GetMapping("/reservation")
    public Iterable<Reservation> getReservations() throws Exception {
        IReservation reserve = new ReserveVehicle(customerRepository, locationRepository,vehicleRepository, 
                                                  invoiceRepository,reservationRepository,priceRepository);
        return reserve.getReservations();
    }

    // get reservation with a fixed reservation id
    @GetMapping(value = "/reservation/{id}")
    public Optional<Reservation> getReservationById (@PathVariable Integer id) throws Exception {
        IReservation reserve = new ReserveVehicle(customerRepository, locationRepository,vehicleRepository, 
                                                  invoiceRepository,reservationRepository,priceRepository);
        return reserve.getReservationById(id);
    }

    // get reservation with a fixed customer id
    @GetMapping(value = "/reservationByCustomerId")
    public Iterable<Reservation> getReservationByCustomerId (
        @RequestParam(value = "customerId") Integer customerId) throws Exception {
        IReservation reserve = new ReserveVehicle(customerRepository, locationRepository,vehicleRepository, 
                                                  invoiceRepository,reservationRepository,priceRepository);
        return reserve.getReservationByCustomerId(customerId);
    }

   
    //Cancel a reservation
    ///frontend will send me resevation id only,isLatefee

    @PutMapping("/reservationCancel/{reservationId}/{isLatefee}")
    public String cancelReservation (@PathVariable Integer reservationId, @PathVariable Boolean isLatefee) throws Exception {

            IReservation reserve = new ReserveVehicle(customerRepository, locationRepository,vehicleRepository, invoiceRepository,reservationRepository,priceRepository);
            String response = reserve.cancelReservation(reservationId, isLatefee);  
            return response;  
    }

    

    ///get and compute the estimated prices for all the available vehicles in a location
    /*
        from postman:
        http://localhost:8080/getEstimatedPriceForVehicles?locationId=2&pickUpTime=1/15/2020 10:00&estimatedDropOffTime=1/15/2020 16:57
    */
    @GetMapping("/getEstimatedPriceForVehicles")
    public String getEstimatedPriceForVehicles(@RequestParam(value = "locationId") Integer locationId,
                                               @RequestParam(value = "pickUpTime") String pickUpTime,
                                               @RequestParam(value = "estimatedDropOffTime") String estimatedDropOffTime) throws Exception {

        IReservation reserve = new ReserveVehicle(customerRepository, locationRepository,vehicleRepository, invoiceRepository,reservationRepository,priceRepository);
        //IPrice priceService = new PriceService(priceRepository);
        
        return reserve.getEstimatedPriceForVehicles(locationId,pickUpTime,estimatedDropOffTime);
      // return null;
    }
    
}