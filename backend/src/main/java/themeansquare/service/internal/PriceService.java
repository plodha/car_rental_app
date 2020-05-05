package themeansquare.service.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import themeansquare.model.Price;
import themeansquare.repository.PriceRepository;
import themeansquare.service.IPrice;

public class PriceService implements IPrice {

    PriceRepository priceRepository;

	public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
	}

    @Override
    public ArrayList<Price> getPriceForVehicleType(String vehicleTypeId) {
        
        Iterable<Price> itr = this.priceRepository.findAll();
        Iterator it = itr.iterator();
        ArrayList<Price> list = new ArrayList<Price>();
        
        while (it.hasNext()) {
            Price p = (Price) it.next();
            if (p.getVehicleTypeId().getId() == Integer.parseInt(vehicleTypeId)) {
                list.add(p);
            }
        }
        
        return list;
    }

    @Override
    public String addPrice(Price price) {
        
        
        HashMap<String, String> response = new HashMap<String, String>();
        
        if (ifHourlyRangeExistsForVehicleType(price)) {
            response.put("status", "400");
            response.put("message", "Price for that hourly range and vehicle type exist already");
            return convertMapToJson(response);
        }
                    
        this.priceRepository.save(price);
        
        response.put("status", "200");

        return convertMapToJson(response);
    }

    public boolean ifHourlyRangeExistsForVehicleType(Price price) {
        Iterable<Price> itr = this.priceRepository.findAll();
        Iterator it = itr.iterator();
        while (it.hasNext()) {
            Price p = (Price) it.next();
            if (p.getVehicleTypeId().getId() == price.getVehicleTypeId().getId()) {
                if (p.getHourlyRange().equals(price.getHourlyRange())) {
                    return true;
                }
            }
        }

        return false;
    }

    public String convertMapToJson(HashMap<String, String> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResponse = "{\"Status\" : 400, \"error_message\" : \"convertMapToJson failed. Registration.java file\"}";
        }
    
        return jsonResponse;
    }

    @Override
    public String updatePrice(Price price) {
        Optional<Price> op = this.priceRepository.findById(price.getId());
        Price og = op.get();

        HashMap<String, String> response = new HashMap<String, String>();
        
        if (!og.getHourlyRange().equals(price.getHourlyRange())) {
            if (ifHourlyRangeExistsForVehicleType(price)) {
                response.put("status", "400");
                response.put("message", "Price for that hourly range and vehicle type exist already");
                return convertMapToJson(response);
            }
        }
        
        this.priceRepository.save(price);
        
        response.put("status", "200");

        return convertMapToJson(response);
    }

    @Override
    public String deletePrice(String priceId) {
        HashMap<String, String> response = new HashMap<String, String>();
        this.priceRepository.deleteById(Integer.parseInt(priceId));
        
        response.put("status", "200");
        return convertMapToJson(response);
    }

    @Override
    public ArrayList<Price> getAllPrices() {
        Iterable<Price> itr = this.priceRepository.findAll();
        Iterator it = itr.iterator();
        ArrayList<Price> list = new ArrayList<Price>();
        
        while (it.hasNext()) {
            Price p = (Price) it.next();
            list.add(p);
        }
        
        return list;
    }
    
}