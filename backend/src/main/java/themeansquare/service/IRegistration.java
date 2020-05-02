package themeansquare.service;

import themeansquare.model.Customer;

public interface IRegistration {
    public String register(Customer customer) throws Exception;
}