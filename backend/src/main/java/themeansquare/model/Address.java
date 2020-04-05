package themeansquare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int Id;
    
    private String Street;
    
    private String City;
    
    private String State;
    
    private int ZipCode;
    
    // Getters and Setters
    
    public int getId() { return Id; }

	public void setId(int id) { Id = id; }

	public String getStreet() { return Street; }

	public void setStreet(String street) {Street = street; }

	public String getCity() { return City; }

	public void setCity(String city) { City = city; }

	public String getState() { return State; }

	public void setState(String state) {State = state; }

	public int getZipCode() { return ZipCode; }

	public void setZipCode(int zipCode) { ZipCode = zipCode; }
	
}

