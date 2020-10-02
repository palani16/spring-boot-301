//package com.example.deliveryservice.controller;
//
//import static org.junit.Assert.fail;
//
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.eatza.deliveryservice.controller.DeliveryServiceController;
//import com.eatza.deliveryservice.service.serviceimpl.DeliveryServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value= DeliveryServiceController.class)
//public class DeliveryServiceControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private DeliveryServiceImpl deliveryService;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//	
//	private static final long EXPIRATIONTIME = 900000;
//	String jwt="";
//	String invalidjwt="";
//
//	@Before
//	public void setup() {
//		jwt = "Bearer "+Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
//				.signWith(SignatureAlgorithm.HS256, "secretkey").setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).compact();
//	
//	
//	}
//
////	@Test
////	public void testLoggingFromOrderToDelivery() {
////		fail("Not yet implemented");
////	}
//
//	@Test
//	public void testItemsDelivering() throws Exception{
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCancel() {
//		fail("Not yet implemented");
//	}
//
//}
