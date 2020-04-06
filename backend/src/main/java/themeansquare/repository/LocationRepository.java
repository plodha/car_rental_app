package themeansquare.repository;

import org.springframework.data.repository.CrudRepository;

import themeansquare.model.Location;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LocationRepository extends CrudRepository<Location, Integer> {

}