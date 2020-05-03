package themeansquare.service;

import java.text.ParseException;

import themeansquare.model.Customer;

public interface IMembership {

	String cancelMembership(String userId) throws ParseException;
    
}