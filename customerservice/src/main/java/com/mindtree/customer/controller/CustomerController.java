package com.mindtree.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.customer.dto.CustomerDTO;
import com.mindtree.customer.exception.CustomerException;
import com.mindtree.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@PostMapping("/customer")
	public ResponseEntity<CustomerDTO> addCustomer(@RequestHeader String authorization,
			@RequestBody CustomerDTO customerDTO) {
		logger.info("customer added successfully");
		return new ResponseEntity<CustomerDTO>(customerService.addCustomer(customerDTO), HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<CustomerDTO> getCustomerById(@RequestParam long customerId) throws CustomerException {
		logger.info("fetching customer by using id  successfully");
		return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}

	
	@DeleteMapping("/customer")
	public ResponseEntity<CustomerDTO> deleteCustomer(@RequestHeader String authorization,
			@RequestParam long customerId) throws CustomerException {
		logger.info("customer deleted successfully");

		return new ResponseEntity<CustomerDTO>(customerService.deleteCustomer(customerId), HttpStatus.OK);
	}

}
