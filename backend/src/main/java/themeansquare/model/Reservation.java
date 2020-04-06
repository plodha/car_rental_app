package themeansquare.model;

import java.time.LocalDateTime;  // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class

public class Reservation{
   
    int _id ;
    float estimatePrice ;
    DateTimeFormatter pickupTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    DateTimeFormatter estimateDropoffTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
    DateTimeFormatter actualDropoffTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") ;
   //Location location ;
    //Vehicle vehicle ;
    Customer customer ;
    Invoice invoice ;
}
    