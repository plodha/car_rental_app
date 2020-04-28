package themeansquare.repository;

import org.springframework.data.repository.CrudRepository;

import themeansquare.model.Vehicle;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {

}