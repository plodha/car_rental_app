package themeansquare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class VehicleType{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
	
	@NotNull
	private int VehicleSize;
	
	@NotNull
	private String VehicleClass;

	public int getId() { return Id; }

	public void setId(int id) {Id = id; }

	public int getVehicleSize() { return VehicleSize; }

	public void setVehicleSize(int vehicleSize) { VehicleSize = vehicleSize; }

	public String getVehicleClass() { return VehicleClass; }

	public void setVehicleClass(String vehicleClass) { VehicleClass = vehicleClass; }
	
	
}
    