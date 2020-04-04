package themeansquare.service.internal;

import themeansquare.service.IRegistration;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class Registration implements IRegistration {

    HashMap<String, String> params;

    public Registration(HashMap<String, String> params) {
        this.params = params;    
    }

	public String isValidParams() {
        HashMap<String, String> response = new HashMap<>();
        response.put("isValidEmail", "false");
        response.put("isUserNameTaken", "false");
        response.put("isLicenseNumberValid", "false");
        response.put("status", "400");

        if (this.isValidEmail()) {
            response.put("isValidEmail", "true");
            if (this.isUserNameTaken()) {
                response.put("isUserNameTaken", "true");
                if (this.isLicenseNumberValid()) {
                    response.put("isLicenseNumberValid", "true");
                    response.put("status", "200");
                    this.insertUser();
                }
            }
        }
        
        return this.convertMapToJson(response);
    }

    public boolean isValidEmail() {
        String email = this.params.get("email");
        String emailRegex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }

    public boolean isUserNameTaken() {
        String username = params.get("username");
        // Make query call and return if it exists or not
        return false;
    }

    public boolean isLicenseNumberValid() {
        
        String licenseNumber = params.get("licenseNumber");
        String licenseNumberRegex = "[a-zA-Z]{2}\\d{6}";
        Pattern pattern = Pattern.compile(licenseNumberRegex);
        Matcher matcher = pattern.matcher(licenseNumber);

        return matcher.matches();
    }

    public boolean insertUser() {
        // Do SQL stuff here
        return true;
    }

    public String convertMapToJson(HashMap<String, String> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResponse = "{\"Status\" : 400, \"error_message\" : \"convertMapToJson failed. Registration.java file\"}";
        }

        return jsonResponse;
    }
}
