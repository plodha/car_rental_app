package themeansquare.carrentalservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//import themeansquare.controller.VehicleRegControllerTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
//import org.junit.Test;

// @SpringBootTest
// @RunWith(Suite.class)
// @SuiteClasses({
// 				VehicleRegControllerTest.class
// 			 })
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = VehicleRegControllerTest.class)
@WebAppConfiguration
class CarRentalServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	
	// public static void main(String[] args) {
	// 	Result result = JUnitCore.runClasses(CarRentalServiceApplicationTests.class);
		  
	// 	for (Failure failure : result.getFailures()) {
	// 	   System.out.println(failure.toString());
	// 	}
	// 	System.out.println("Test successful? " + result.wasSuccessful());
		
	//  }

}
