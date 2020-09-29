package com.mindtree.customer.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.customer.dto.CustomerDTO;
import com.mindtree.customer.service.CustomerService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@Autowired
	private ObjectMapper objectMapper;

	String jwt = "";
	private static final long EXPIRATIONTIME = 900000;

	@Before
	public void setup() {
		jwt = "Bearer " + Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).compact();
	}

	@Test
	public void addCustomerTest() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerName("mourya");
		customer.setCustomerAge((byte) 22);
		when(customerService.addCustomer(customer)).thenReturn(new CustomerDTO());
		RequestBuilder request = MockMvcRequestBuilders.post("/customer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((customer))).header(HttpHeaders.AUTHORIZATION, jwt);
		mockMvc.perform(request).andExpect(status().is(200)).andExpect(status().is(200)).andReturn();

	}

	@Test(expected = com.mindtree.customer.exception.InvalidTokenException.class)
	public void updateOrder_invalid() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerName("mourya");
		customer.setCustomerAge((byte) 22);
//		List<OrderedItem> orderedList = new ArrayList<>();
//		List<OrderedItemsDto> orderedDto = new ArrayList<>();
		jwt = "Bearer " + Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.setExpiration(new Date(System.currentTimeMillis() - EXPIRATIONTIME)).compact();
		// OrderUpdateDto orderUpdateDto = new OrderUpdateDto(1L, 1L, orderedDto, 1L);
//		when(orderService.updateOrder(any(OrderUpdateDto.class)))
//				.thenReturn(new OrderUpdateResponseDto(1L, 1L, "UPDATED", 1L, orderedList));
		when(customerService.addCustomer(customer)).thenReturn(new CustomerDTO());

		RequestBuilder request = MockMvcRequestBuilders.post("/customer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((customer))).header(HttpHeaders.AUTHORIZATION, jwt);
		mockMvc.perform(request).andExpect(status().is(401)).andReturn();

	}

	@Test(expected = com.mindtree.customer.exception.InvalidTokenException.class)
	public void updateOrder_null_token() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerName("mourya");
		customer.setCustomerAge((byte) 22);
		// List<OrderedItem> orderedList = new ArrayList<>();
		// List<OrderedItemsDto> orderedDto = new ArrayList<>();
		jwt = "Bearer " + Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.setExpiration(new Date(System.currentTimeMillis() - EXPIRATIONTIME)).compact();
		// OrderUpdateDto orderUpdateDto = new OrderUpdateDto(1L, 1L, orderedDto, 1L);
		// when(orderService.updateOrder(any(OrderUpdateDto.class)))
		// .thenReturn(new OrderUpdateResponseDto(1L, 1L, "UPDATED", 1L, orderedList));
		when(customerService.addCustomer(customer)).thenReturn(new CustomerDTO());

		RequestBuilder request = MockMvcRequestBuilders.post("/customer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((customer))).header(HttpHeaders.AUTHORIZATION, " help");
		mockMvc.perform(request).andExpect(status().is(401)).andReturn();

	}

	@Test
	public void getCustomerByIdTest() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerMobile(90944912577L);
		customer.setCustomerId(1L);
		customer.setCustomerAge((byte) 22);
		customer.setCustomerActiveStatus("ACTIVE");
		customer.setCustomerName("mourya");

		// ResponseEntity<CustomerDTO> customerResponse = new
		// ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);

		when(customerService.getCustomerById(1L)).thenReturn(customer);

		// request
		RequestBuilder request = MockMvcRequestBuilders.get("/id?customerId=1").contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, jwt);

		// response
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}

	@Test
	public void deleteCustomerTest() throws Exception {

		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerMobile(90944912577L);
		customer.setCustomerId(1L);
		customer.setCustomerAge((byte) 22);
		customer.setCustomerActiveStatus("IN_ACTIVE");
		customer.setCustomerName("mourya");
		when(customerService.deleteCustomer(1L)).thenReturn(customer);

		// request
		RequestBuilder request = MockMvcRequestBuilders.delete("/customer?customerId=1")
				.contentType(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, jwt);

		// response
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
	}

}
