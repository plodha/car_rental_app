package themeansquare.controller;

import themeansquare.TestUtils;
import themeansquare.controller.*;
import themeansquare.model.*;
import themeansquare.repository.*;
import themeansquare.service.*;
import themeansquare.service.internal.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.JUnitCore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VehicleRegController.class)
public class VehicleRegControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AddressRepository addressRepository;
    @MockBean
    private IVehicleReg vehicle;
    @MockBean
    private VehicleReg vehicleReg;

    private final String URL = "/vehicle";

    // @Test
    // public void addVehicleTest() throws Exception {
        
    //     // prepare data and mock's behaviour
    //     vehicle = new VehicleReg ( "van", 1 , "VN02020" , "bmw-test","corha", true, "1010", 
    //                                 2020, 432651234, "bmw-test", 20, "101", "mountain view", "CA", "94043",
    //                                 vehicleRepository, vehicleTypeRepository, locationRepository,addressRepository);
    //     String response = vehicle.addVehicle();
        
    //     // execute
	// 	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
    //     .accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(response))).andReturn();

    //     // verify
	// 	int status = result.getResponse().getStatus();
	// 	assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);
    // }
   
    @Test
	public void getVehicleByIdTest() throws Exception {

        // IVehicleReg reg = new VehicleReg ( ,  ,  , ,, , ,
        //                                   , , , , , , , ,
        //                                   vehicleRepository, vehicleTypeRepository, locationRepository,addressRepository);
        //     vehicle = new VehicleReg ( "",  , "VN02020" , "bmw-test","corha", true, "1010", 
    //                                 2020, 432651234, "bmw-test", 20, "101", "mountain view", "CA", "zipcode",
    //                                 vehicleRepository, vehicleTypeRepository, locationRepository,addressRepository);

		mockMvc.perform(get("/vehicle/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.vehicleClass").value("MICROooo")).andExpect(jsonPath("$.vehicleSize").value(1))
                .andExpect(jsonPath("$.model").value("corollllla")).andExpect(jsonPath("$.make").value("corha"))
                .andExpect(jsonPath("$.status").value(false)).andExpect(jsonPath("$.vIN").value("1"))
                .andExpect(jsonPath("$.year").value(2050)).andExpect(jsonPath("$.contactNumber").value(432651234))
                .andExpect(jsonPath("$.name").value("BMW")).andExpect(jsonPath("$.vehicleCapacity").value(40))
                .andExpect(jsonPath("$.street").value("101")).andExpect(jsonPath("$.city").value("mountain view"))
                .andExpect(jsonPath("$.state").value("CA")).andExpect(jsonPath("$.zipcode").value("94043"));

        }
   
}