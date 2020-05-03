package themeansquare.service;

import java.util.ArrayList;

import themeansquare.model.VehicleType;

public interface IVehicleType {

	ArrayList<VehicleType> getAllVehicleTypes();

	String addVehicleType(VehicleType vehicleType);

	String updateVehicleType(VehicleType vehicleType);

	String deleteVehicleType(String vehicleTypeId) throws Exception;
    
}