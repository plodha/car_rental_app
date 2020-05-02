package themeansquare.service;

import themeansquare.model.Customer;

public interface ICustomer {

	String getAllCustomers();

	Customer getCustomerInfo(String userId);

	String updateCustomer(Customer customer);
    
}