package themeansquare.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VehicleType", referencedColumnName = "Id")
	private VehicleType VehicleTypeId;

	public int getId() { return Id; }

	public void setId(int id) { Id = id; }

	public double getLateFee() { return LateFee; }

	public void setLateFee(double lateFee) { LateFee = lateFee; }

	public double getLicensePlate() { return hourlyPrice; }

	public void setLicensePlate(double hourlyPrice) { this.hourlyPrice = hourlyPrice; }

	public VehicleType getVehicleTypeId() { return VehicleTypeId; }

	public void setVehicleTypeId(VehicleType vehicleTypeId) { VehicleTypeId = vehicleTypeId; }
	
}
    