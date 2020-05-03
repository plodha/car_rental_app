package themeansquare.service.internal;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import themeansquare.model.Employee;
import themeansquare.repository.EmployeeRepository;
import themeansquare.service.IEmployee;

public class EmployeeService implements IEmployee {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String createEmployee(Employee employee) {
        HashMap<String, String> response = new HashMap<>();
       
        response.put("status", "200");
        this.employeeRepository.save(employee);

        return convertMapToJson(response);
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