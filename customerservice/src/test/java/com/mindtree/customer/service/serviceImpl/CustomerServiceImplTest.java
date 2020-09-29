package com.mindtree.customer.service.serviceImpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.mindtree.customer.dto.CustomerDTO;
import com.mindtree.customer.dto.Order;
import com.mindtree.customer.exception.CustomerException;
import com.mindtree.customer.model.Customer;
import com.mindtree.customer.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = CustomerServiceImpl.class)
public class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRespository;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Test
	public void addCustomerTest() {
		CustomerDTO customerDTO = new CustomerDTO(1L, "mourya", 90909L, (byte) 22, "isActive", null, null);
		Customer customer = new Customer(1L, "mourya", 90909L, (byte) 22, "isActive", null, null);

		when(modelMapper.map(customerDTO, Customer.class)).thenReturn(customer);
		when(customerRespository.save(customer)).thenReturn(customer);
		when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);
		CustomerDTO response = customerService.addCustomer(customerDTO);
		assertNotNull(response);
	}

	@Test
	public void deleteCustomerTest() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO(1L, "mourya", 90909L, (byte) 22, "isActive", null, null);
		Customer customer = new Customer(1L, "mourya", 90909L, (byte) 22, "isActive", null, null);

		when(customerRespository.findById(1L)).thenReturn(Optional.of(customer));
		when(customerRespository.saveAndFlush(customer)).thenReturn(customer);
		when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);
		CustomerDTO response = customerService.deleteCustomer(1L);
		assertNotNull(response);
	}

	@Test
	public void getCustomerByIdTest() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO(1L, "mourya", 90909L, (byte) 22, "isActive", null, null);
		Customer customer = new Customer(1L, "mourya", 90909L, (byte) 22, "isActive", null, null);

		when(customerRespository.findById(1L)).thenReturn(Optional.of(customer));
		// assertThrows(CustomerException.class,()->customerService.getCustomerById(1L));

		when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);
		CustomerDTO response = customerService.getCustomerById(1L);
		assertNotNull(response);
	}

	@Test
	public void updateOrderStatus() throws CustomerException {
		Customer customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerName("mouri");
		customer.setCustomerMobile(90965432);
		customer.setCustomerAge((byte) 22);
		customer.setCustomerActiveStatus("isActive");
		Order order = new Order(1L, 1L, null, 1L, null, null);
		when(customerRespository.findById(order.getCustomerId())).thenReturn(Optional.of(customer));
		when(customerRespository.saveAndFlush(customer)).thenReturn(customer);
		String response = customerService.updateOrderStatus(order);
		assertNotNull(response);
	}

}
