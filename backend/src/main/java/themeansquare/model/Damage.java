package themeansquare.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Damage{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
	
	@NotNull
	@ManyToOne()
    @JoinColumn(name = "VehicleType", referencedColumnName = "Id")
	private VehicleType VehicleTypeId;
	
	@NotNull
    @Column(columnDefinition = "TEXT")
    private String DamageType;
	
	@NotNull
	private double DamageFee;

	public int getId() { return Id; }

	public void setId(int id) { Id = id; }

	public VehicleType getVehicleTypeId() { return VehicleTypeId; }

	public void setVehicleTypeId(VehicleType vehicleTypeId) { VehicleTypeId = vehicleTypeId; }

	public String getDamageType() { return DamageType; }

	public void setDamageType(String damageType) { DamageType = damageType; }

	public double getDamageFee() { return DamageFee; }

	public void setDamageFee(double damageFee) { DamageFee = damageFee; }
	

}
