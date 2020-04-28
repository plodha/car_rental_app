package themeansquare.repository;

import org.springframework.data.repository.CrudRepository;

import themeansquare.model.Price;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PriceRepository extends CrudRepository<Price, Integer> {

}