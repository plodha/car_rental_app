package themeansquare.controller;

import themeansquare.controller.*;
import themeansquare.model.*;
import themeansquare.repository.*;
import themeansquare.service.*;
import themeansquare.service.internal.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleRegController.class)
public class VehicleRegControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private VehicleRepository vehicleRepository;
    @MockBean
    private VehicleTypeRepository vehicleTypeRepository;
    @MockBean
    private LocationRepository locationRepository;
    @MockBean
    private AddressRepository addressRepository;
    @MockBean
    private IVehicleReg vehicle;
    @MockBean
    private VehicleReg vehicleReg;

    @Test
    public void getVehiclesTest() throws Exception {
        
        // Employee alex = new Employee("alex");
    
        // List<Employee> allEmployees = Arrays.asList(alex);
    
        // given(service.getAllEmployees()).willReturn(allEmployees);
    
        // mvc.perform(get("/api/employees")
        // .contentType(MediaType.APPLICATION_JSON))
        // .andExpect(status().isOk())
        // .andExpect(jsonPath("$", hasSize(1)))
        // .andExpect(jsonPath("$[0].name", is(alex.getName())));

        Iterable<Vehicle> itr = vehicleRepository.findAll();
        mockMvc.perform(get("/vehicle")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.vehicleClass").value("MICROooo"));
    }

    // @Test
    // public void getVehicleByIdTest() throws Exception {
        
    //     mockMvc.perform(get("/vehicle/1")).andExpect(status().isOk())
	// 			.andExpect(content().contentType("application/json;charset=UTF-8"))
	// 			.andExpect(jsonPath("$.vehicleClass").value("MICROooo")).andExpect(jsonPath("$.vehicleSize").value(1))
    //             .andExpect(jsonPath("$.model").value("corollllla")).andExpect(jsonPath("$.make").value("corha"))
    //             .andExpect(jsonPath("$.status").value(false)).andExpect(jsonPath("$.vIN").value("1"))
    //             .andExpect(jsonPath("$.year").value(2050)).andExpect(jsonPath("$.contactNumber").value(432651234))
    //             .andExpect(jsonPath("$.name").value("BMW")).andExpect(jsonPath("$.vehicleCapacity").value(40))
    //             .andExpect(jsonPath("$.street").value("101")).andExpect(jsonPath("$.city").value("mountain view"))
    //             .andExpect(jsonPath("$.state").value("CA")).andExpect(jsonPath("$.zipcode").value("94043"));

    // }
}
    
