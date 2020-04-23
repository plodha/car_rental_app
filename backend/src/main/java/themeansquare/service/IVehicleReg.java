package themeansquare.service;

import themeansquare.model.Vehicle;

import java.util.List;

public interface IVehicleReg {

    public String addVehicle() throws Exception;

    public Iterable<Vehicle> getVehicles() throws Exception;

    public void delVehicle(Integer id) throws Exception;

    public String updateVehicleById(Vehicle existVehicle, Vehicle vehicle) throws Exception;
}