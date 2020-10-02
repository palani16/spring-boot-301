package com.mindtree.customer.service;

import com.mindtree.customer.dto.CustomerDTO;
import com.mindtree.customer.exception.CustomerException;

public interface CustomerService {

	public CustomerDTO addCustomer(CustomerDTO customerDTO);

	public CustomerDTO deleteCustomer(long customerId) throws CustomerException;

	public CustomerDTO getCustomerById(long customerId) throws CustomerException;

}
