package themeansquare.model;

import java.time.LocalDateTime;  // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;  // Import the DateTimeFormatter class
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

@Entity
public class Reservation{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
	
	@NotNull
	//@OneToOne(cascade = CascadeType.ALL)
	@ManyToOne()
    @JoinColumn(name = "Customer", referencedColumnName = "Id")
	private Customer Customer;
	
	@NotNull
	//@OneToOne(cascade = CascadeType.ALL)
	@ManyToOne()
    @JoinColumn(name = "Vehicle", referencedColumnName = "Id")
	private Vehicle Vehicle;
	
	@NotNull
	//@OneToOne(cascade = CascadeType.ALL)
	@ManyToOne()
    @JoinColumn(name = "Location", referencedColumnName = "Id")
	private Location Location;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date PickUpTime;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date EstimateDropOffTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ActualDropOffTime;
	
	@NotNull
	private double EstimatedPrice;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Invoice", referencedColumnName = "Id")
	private Invoice Invoice;
	
	@NotNull
	private boolean status;

	public boolean isStatus() {return status; }

	public void setStatus(boolean status) { this.status = status; }

	public int getId() { return Id; }

	public void setId(int id) { Id = id; }

	public Customer getCustomer() { return Customer; }

	public void setCustomer(Customer customer) { Customer = customer; }

	public Vehicle getVehicle() { return Vehicle; }

	public void setVehicle(Vehicle vehicle) { Vehicle = vehicle; }

	public Location getLocation() { return Location; }

	public void setLocation(Location location) { Location = location; }

	public Date getPickUpTime() { return PickUpTime; }

	public void setPickUpTime(Date pickUpTime) { PickUpTime = pickUpTime; }

	public Date getEstimateDropOffTime() { return EstimateDropOffTime; }

	public void setEstimateDropOffTime(Date estimateDropOffTime) { EstimateDropOffTime = estimateDropOffTime; }

	public Date getActualDropOffTime() { return ActualDropOffTime; }

	public void setActualDropOffTime(Date actualDropOffTime) { ActualDropOffTime = actualDropOffTime; }

	public double getEstimatedPrice() { return EstimatedPrice; }

	public void setEstimatedPrice(double estimatedPrice) { EstimatedPrice = estimatedPrice; }

	public Invoice getInvoice() { return Invoice; }

	public void setInvoice(Invoice invoice) { Invoice = invoice; }
	
	
}
    