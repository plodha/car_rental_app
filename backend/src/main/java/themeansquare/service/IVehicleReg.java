package themeansquare.service;

import themeansquare.model.Vehicle;
import themeansquare.model.VehicleType;

import java.util.List;
import java.util.Optional;

public interface IVehicleReg {

    public String addVehicle() throws Exception;

    public Iterable<Vehicle> getVehicles() throws Exception;

    public Optional<Vehicle> getVehicleById(Integer id) throws Exception;

    public String delVehicle(Integer id) throws Exception;

    public String updateVehicleById(Integer id, Vehicle vehicle) throws Exception; //Vehicle existVehicle, Vehicle vehicle
}