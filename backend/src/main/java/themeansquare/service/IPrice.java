package themeansquare.service;

import java.util.ArrayList;

import themeansquare.model.Price;

public interface IPrice {

	ArrayList<Price> getPriceForVehicleType(String vehicleTypeId);

	String addPrice(Price price);

	String updatePrice(Price price);

	String deletePrice(String price);

	ArrayList<Price> getAllPrices();
    
}