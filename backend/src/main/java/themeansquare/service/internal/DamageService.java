package themeansquare.service.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import themeansquare.model.Damage;
import themeansquare.model.Price;
import themeansquare.repository.DamageRepository;
import themeansquare.repository.PriceRepository;
import themeansquare.service.IDamage;
import themeansquare.service.IPrice;

public class DamageService implements IDamage {

    DamageRepository damageRepository;

    public DamageService(DamageRepository damageRepository) {
        this.damageRepository = damageRepository;
	}

    @Override
    public ArrayList<Damage> getDamageForVehicleType(String vehicleTypeId) {
        Iterable<Damage> itr = this.damageRepository.findAll();
        Iterator it = itr.iterator();
        ArrayList<Damage> list = new ArrayList<Damage>();
        
        while (it.hasNext()) {
            Damage p = (Damage) it.next();
            if (p.getVehicleTypeId().getId() == Integer.parseInt(vehicleTypeId)) {
                list.add(p);
            }
        }
        
        return list;
    }

    public String addDamage(Damage damage) {
        
        
        HashMap<String, String> response = new HashMap<String, String>();
                    
        this.damageRepository.save(damage);
        
        response.put("status", "200");

        return convertMapToJson(response);
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


    public String updateDamage(Damage damage) {

        HashMap<String, String> response = new HashMap<String, String>();
        
        this.damageRepository.save(damage);
        
        response.put("status", "200");

        return convertMapToJson(response);
    }


    public String deleteDamage(String damageId) {
        HashMap<String, String> response = new HashMap<String, String>();
        this.damageRepository.deleteById(Integer.parseInt(damageId));
        
        response.put("status", "200");
        return convertMapToJson(response);
    }
    
}