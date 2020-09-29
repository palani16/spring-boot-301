package com.eatza.order.service.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eatza.order.dto.CustomerDTO;
import com.eatza.order.exception.CustomerException;

@FeignClient(name = "customer-service")
public interface CustomerService {

	@GetMapping("/id")
	public ResponseEntity<CustomerDTO> getCustomerById(@RequestParam long customerId) throws CustomerException;
}