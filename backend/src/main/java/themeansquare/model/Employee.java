package themeansquare.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Employee{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
	
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

	public int getId() { return Id; }

	public void setId(int id) { Id = id; }

	public User getUserId() { return UserId; }

	public void setUserId(User userId) { UserId = userId; }

	public String getFirstName() { return FirstName; }

	public void setFirstName(String firstName) { FirstName = firstName; }

	public String getLastName() { return LastName; }

	public void setLastName(String lastName) { LastName = lastName; }
    
}
    