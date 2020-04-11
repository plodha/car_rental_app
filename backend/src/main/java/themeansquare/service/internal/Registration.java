package themeansquare.service.internal;

import themeansquare.model.User;
import themeansquare.repository.UserRepository;
import themeansquare.service.IRegistration;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Registration implements IRegistration {

    @Autowired
    private UserRepository userRepository;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String licenseNumber;
    private String licenseExpDate;
    private String email;

    public Registration(String username, String password, String firstName, String lastName,
    String address, String licenseNumber, String licenseExpDate, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.licenseNumber = licenseNumber;
        this.licenseExpDate = licenseExpDate;
        this.email = email;
    }

    public String isValidParams() {
        
        return null;
    }

    public boolean insertUser() {
        User newUser = new User();
        newUser.setUsername(this.username);
        newUser.setPassword(this.password);
        userRepository.save(newUser);

        return false;
    }

    public boolean isValidEmail() {
        
        return false;
    }

    public boolean isUserNameTaken() {
        
        return false;
    }
}
