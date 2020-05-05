package themeansquare.model;
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ForeignKey;


@Entity
public class Customer{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
	
	// Set Foreign Key
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User", referencedColumnName = "Id")
	private User UserId;

	@NotNull
    @Column(columnDefinition = "TEXT")
    private String FirstName;
	
	@NotNull
    @Column(columnDefinition = "TEXT")
    private String LastName;
	
	// Set Foreign Key
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Address", referencedColumnName = "Id")
    private Address Address;
	
	@NotNull
    @Column(columnDefinition = "TEXT")
    private String LicenseNumber;
	
	@NotNull
	//@Temporal(TemporalType.DATE)
    private String LicenseExpDate;
	
	@NotNull
	//@Temporal(TemporalType.DATE)
    private String MembershipStartDate;
	
	@NotNull
	//@Temporal(TemporalType.DATE)
	private String MembershipEndDate;
	
	@NotNull
    @Column(columnDefinition = "TEXT")
    private String Email;
	
	@NotNull
	private String creditCard;
	
	@NotNull
	//@Temporal(TemporalType.DATE)
	private String creditCardExpDate;
	
	@NotNull
	private String CVV;
	
	public String getCreditCardExpDate() { return creditCardExpDate; }

	public void setCreditCardExpDate(String creditCardExpDate) { this.creditCardExpDate = creditCardExpDate; }

	public String getCVV() {return CVV; }

	public void setCVV(String cVV) {CVV = cVV; }

	public String getCreditCard() { return creditCard; }

	public void setCreditCard(String creditCard) {this.creditCard = creditCard; }

	public int getId() { return Id; }

	public void setId(int id) { Id = id; }

	public User getUserId() { return UserId; }

	public void setUserId(User userId) { UserId = userId; }

	public String getFirstName() { return FirstName; }

	public void setFirstName(String firstName) { FirstName = firstName; }

	public String getLastName() { return LastName; }

	public void setLastName(String lastName) { LastName = lastName; }

	public Address getAddress() { return Address; }

	public void setAddress(Address address) { Address = address; }

	public String getLicenseNumber() { return LicenseNumber; }

	public void setLicenseNumber(String licenseNumber) { LicenseNumber = licenseNumber; }

	public String getLicenseExpDate() { return LicenseExpDate; }

	public void setLicenseExpDate(String licenseExpDate) { LicenseExpDate = licenseExpDate; }

	public String getMembershipStartDate() { return MembershipStartDate; }

	public void setMembershipStartDate(String membershipStartDate) { MembershipStartDate = membershipStartDate; }

	public String getMembershipEndDate() { return MembershipEndDate; }

	public void setMembershipEndDate(String membershipEndDate) { MembershipEndDate = membershipEndDate; }

	public String getEmail() { return Email; }

	public void setEmail(String email) { Email = email; }
	
    
}
    