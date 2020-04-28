package themeansquare.repository;

import org.springframework.data.repository.CrudRepository;

import themeansquare.model.Customer;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}