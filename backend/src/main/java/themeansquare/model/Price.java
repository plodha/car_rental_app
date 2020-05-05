package themeansquare.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Price{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
	
	@NotNull
	private double LateFee;
	
	@NotNull
	private double hourlyPrice;
	
	@NotNull
	@ManyToOne()
	// @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VehicleType", referencedColumnName = "Id")
	private VehicleType VehicleTypeId;
	
	@NotNull
	private String hourlyRange;

	public double getHourlyPrice() {return hourlyPrice; }

	public void setHourlyPrice(double hourlyPrice) { this.hourlyPrice = hourlyPrice; }

	public String getHourlyRange() { return hourlyRange; }

	public void setHourlyRange(String hourlyRange) { this.hourlyRange = hourlyRange; }

	public int getId() { return Id; }

	public void setId(int id) { Id = id; }

	public double getLateFee() { return LateFee; }

	public void setLateFee(double lateFee) { LateFee = lateFee; }

	public VehicleType getVehicleTypeId() { return VehicleTypeId; }

	public void setVehicleTypeId(VehicleType vehicleTypeId) { VehicleTypeId = vehicleTypeId; }
	
	
	
}
    