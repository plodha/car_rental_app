package themeansquare.service;

import java.util.ArrayList;

import themeansquare.model.Customer;

public interface ICustomer {

	ArrayList<Customer> getAllCustomers();

	Customer getCustomerInfo(String userId);

	String updateCustomer(Customer customer);

	String removeCustomer(String userId);
    
}