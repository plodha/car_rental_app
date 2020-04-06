package themeansquare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Invoice{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
	
	@NotNull
	private double EstimatedPrice;
	
	@NotNull
	private double DamageFee;
	
	@NotNull
	private double LateFee;
	
	@NotNull
	private double TotalPrice;

	public int getId() { return Id; }

	public void setId(int id) { Id = id; }

	public double getEstimatedPrice() { return EstimatedPrice; }

	public void setEstimatedPrice(double estimatedPrice) { EstimatedPrice = estimatedPrice; }

	public double getDamageFee() { return DamageFee; }

	public void setDamageFee(double damageFee) { DamageFee = damageFee; }

	public double getLateFee() { return LateFee; }

	public void setLateFee(double lateFee) { LateFee = lateFee; }

	public double getTotalPrice() { return TotalPrice; }

	public void setTotalPrice(double totalPrice) { TotalPrice = totalPrice; }
	
	
}
    
    