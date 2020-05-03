package themeansquare.controller;

import themeansquare.model.Customer;
import themeansquare.model.Location;
import themeansquare.model.Reservation;
import themeansquare.model.Vehicle;
import themeansquare.model.Invoice;
import themeansquare.service.IReservation;
import themeansquare.service.internal.ReserveVehicle;
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

    /**
        Input must be in this format (fields can be missing for some like id):
            {
                "status": true,
                "location": {
                    "address": {
                        "state": "CA",
                        "id": 2,
                        "street": "101",
                        "city": "mountain view",
                        "zipCode": 94043
                    },
                    "name": "rental_spot_2",
                    "id": 2,
                    "contactNumber": 432651234,
                    "vehicleCapacity": 4
                },
                "id": 1,
                "estimatedPrice": 100,
                "customer": {
                    "creditCard": "35485739857",
                    "creditCardExpDate": "2022-05-19",
                    "id": 1,
                    "address": {
                        "state": "ca",
                        "id": 9,
                        "street": "random street",
                        "city": "random city",
                        "zipCode": 94086
                    },
                    "licenseNumber": "747324",
                    "firstName": "subarna",
                    "cvv": "454",
                    "licenseExpDate": "1994-05-21",
                    "lastName": "chy",
                    "userId": {
                        "id": 1,
                        "username": "schy4",
                        "password": "notsecure"
                    },
                    "email": "different2@gmail.com",
                    "membershipEndDate": "2021-05-01",
                    "membershipStartDate": "2020-05-01"
                },
                "invoice": {
                    "id": 1,
                    "lateFee": 10,
                    "estimatedPrice": 100,
                    "damageFee": 20,
                    "totalPrice": 120
                },
                "pickUpTime": "2020-05-01",
                "vehicle": {
                    "vehicleCondition": "good",
                    "year": 2020,
                    "location": {
                        "address": {
                            "state": "CA",
                            "id": 2,
                            "street": "101",
                            "city": "mountain view",
                            "zipCode": 94043
                        },
                        "name": "rental_spot_2",
                        "id": 2,
                        "contactNumber": 432651234,
                        "vehicleCapacity": 4
                    },
                    "id": 1,
                    "status": false,
                    "vehicleTypeId": {
                        "id": 1,
                        "vehicleClass": "Micro",
                        "vehicleSize": 5
                    },
                    "vin": "2334",
                    "make": "EMPTY",
                    "licensePlate": "VN123",
                    "model": "BMW"
                },
                "actualDropOffTime": "2020-05-01",
                "estimateDropOffTime": "2020-05-01"
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
                                                  invoiceRepository,reservationRepository);
        String response = res.addReservation(newReservation);

        return response;
	}

    @PostMapping("/reservationOld")
    public String addReservation(@RequestParam(value = "actualDropOffTime") String actualDropOffTime,
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
                                              customerRepository, locationRepository,vehicleRepository, invoiceRepository,reservationRepository);
        String response = reserve.addReservationOld();
        return response;
    }

    // get all reservations
    @GetMapping("/reservation")
    public Iterable<Reservation> getReservations() throws Exception {
        IReservation reserve = new ReserveVehicle(customerRepository, locationRepository,vehicleRepository, 
                                                  invoiceRepository,reservationRepository);
        return reserve.getReservations();
    }

    // get reservation with a fixed reservation id
    @GetMapping(value = "/reservation/{id}")
    public Optional<Reservation> getReservationById (@PathVariable Integer id) throws Exception {
        IReservation reserve = new ReserveVehicle(customerRepository, locationRepository,vehicleRepository, 
                                                  invoiceRepository,reservationRepository);
        return reserve.getReservationById(id);
    }
    
}