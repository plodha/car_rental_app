package themeansquare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
    
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String Street;
    
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String City;
    
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String State;
    
    @NotNull
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