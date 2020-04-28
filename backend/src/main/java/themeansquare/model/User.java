package themeansquare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@NotNull
	private String Username;
	
    @NotNull
    private String Password;

	public int getId() { return Id; }

	public void setId(int id) { Id = id; }

	public String getUsername() { return Username; }

	public void setUsername(String username) { Username = username; }

	public String getPassword() { return Password; }

	public void setPassword(String password) { Password = password; }
    
    
}
    