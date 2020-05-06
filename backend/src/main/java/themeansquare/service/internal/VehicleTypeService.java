package themeansquare.service.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import themeansquare.model.VehicleType;
import themeansquare.model.Damage;
import themeansquare.model.Price;
import themeansquare.model.Vehicle;
import themeansquare.repository.VehicleTypeRepository;
import themeansquare.repository.DamageRepository;
import themeansquare.repository.PriceRepository;
import themeansquare.repository.VehicleRepository;
import themeansquare.service.IVehicleType;
import themeansquare.service.IPrice;

public class VehicleTypeService implements IVehicleType {

    VehicleTypeRepository vehicleTypeRepository;
    PriceRepository priceRepository;
    DamageRepository damageRepository;
    VehicleRepository vehicleRepository;

    public VehicleTypeService(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
	}

    public VehicleTypeService(VehicleTypeRepository vehicleTypeRepository, PriceRepository priceRepository, DamageRepository damageRepository, VehicleRepository vehicleRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.priceRepository = priceRepository;
        this.damageRepository = damageRepository;
        this.vehicleRepository = vehicleRepository;
	}

    @Override
    public ArrayList<VehicleType> getAllVehicleTypes() {
        Iterable<VehicleType> itr = this.vehicleTypeRepository.findAll();
        Iterator it = itr.iterator();
        ArrayList<VehicleType> list = new ArrayList<VehicleType>();
        
        while (it.hasNext()) {
            VehicleType p = (VehicleType) it.next();
            
            list.add(p);
            
        }
        
        return list;
    }
    
    public boolean ifVehicleTypeExists(VehicleType vehicleType) {
        Iterable<VehicleType> itr = this.vehicleTypeRepository.findAll();
        Iterator it = itr.iterator();

        while (it.hasNext()) {
            VehicleType p = (VehicleType) it.next();
            
            if (p.getVehicleClass().equals(vehicleType.getVehicleClass())) {
                return true;
            }
            
        }

        return false;
    }

    public String addVehicleType(VehicleType vehicleType) {
        
        HashMap<String, String> response = new HashMap<String, String>();
        if (ifVehicleTypeExists(vehicleType)) {
            response.put("status", "400");
            response.put("message", "Vehicle class exists");
            return convertMapToJson(response);
        }
        
        this.vehicleTypeRepository.save(vehicleType);
        
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


    public String updateVehicleType(VehicleType vehicleType) {

        Optional<VehicleType> optionalVehicleType = vehicleTypeRepository.findById(vehicleType.getId());
        VehicleType inDBVehicleType = optionalVehicleType.get();
        HashMap<String, String> response = new HashMap<String, String>();
        
        if (!inDBVehicleType.getVehicleClass().equals(vehicleType.getVehicleClass())) {
            if (ifVehicleTypeExists(vehicleType)) {
                response.put("status", "400");
                response.put("message", "Vehicle class is taken");
                return convertMapToJson(response);
            }
        }

        this.vehicleTypeRepository.save(vehicleType);
        
        response.put("status", "200");

        return convertMapToJson(response);
    }


    public String deleteVehicleType(String vehicleTypeId) throws Exception {
        
        PriceService ps = new PriceService(priceRepository);
        ArrayList<Price> psList = ps.getPriceForVehicleType(vehicleTypeId);

        Iterable<Price> psIter = new Iterable<Price>(){
        
            @Override
            public Iterator<Price> iterator() {
               return psList.iterator();
            }
        };
        
        this.priceRepository.deleteAll(psIter);

        DamageService ds = new DamageService(damageRepository);
        ArrayList<Damage> dsList =  ds.getDamageForVehicleType(vehicleTypeId);
        Iterable<Damage> dsIter = new Iterable<Damage>(){
        
            @Override
            public Iterator<Damage> iterator() {
               return dsList.iterator();
            }
        };
        
        this.damageRepository.deleteAll(dsIter);
        
        VehicleReg vr = new VehicleReg(vehicleRepository, vehicleTypeRepository, null, null, null);
        Iterable<Vehicle> vitr = vr.getVehicleByVehicleType(Integer.parseInt(vehicleTypeId));
        this.vehicleRepository.deleteAll(vitr);
        

        HashMap<String, String> response = new HashMap<String, String>();
        this.vehicleTypeRepository.deleteById(Integer.parseInt(vehicleTypeId));
        
        response.put("status", "200");
        return convertMapToJson(response);
    }
    
}