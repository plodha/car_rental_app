package themeansquare.service;

import java.util.ArrayList;

import themeansquare.model.Damage;
import themeansquare.model.Price;

public interface IDamage {

	ArrayList<Damage> getDamageForVehicleType(String vehicleTypeId);
    String addDamage(Damage damage);
    String updateDamage(Damage damage);
    String deleteDamage(String damageId);
}