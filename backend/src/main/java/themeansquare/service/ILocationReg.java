package themeansquare.service;

import themeansquare.model.Location;

import java.util.Optional;

public interface ILocationReg {

    public String addLocation() throws Exception;
    public Iterable<Location> getLocations() throws Exception;
    public Optional<Location> getLocationById(Integer id) throws Exception;
    public String delLocation(Integer id) throws Exception;
    public String updateLocationById(Integer id, Location location) throws Exception;
}