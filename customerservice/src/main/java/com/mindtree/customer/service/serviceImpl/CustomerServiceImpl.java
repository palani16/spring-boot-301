package com.mindtree.customer.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mindtree.customer.dto.CustomerDTO;
import com.mindtree.customer.dto.Order;
import com.mindtree.customer.exception.CustomerException;
import com.mindtree.customer.model.Customer;
import com.mindtree.customer.repository.CustomerRepository;
import com.mindtree.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public CustomerDTO addCustomer(CustomerDTO customerDTO) {

		Customer customer = modelMapper.map(customerDTO, Customer.class);

		customer.setCustomerActiveStatus("isActive");

		customerDTO = modelMapper.map(customerRepository.save(customer), CustomerDTO.class);

		return customerDTO;
	}

	@Override
	public CustomerDTO deleteCustomer(long customerId) throws CustomerException {
		Customer customer = customerRepository.findById(customerId).get();
				
		customer.setCustomerActiveStatus("inActive");

		CustomerDTO customerDTO = modelMapper.map(customerRepository.saveAndFlush(customer), CustomerDTO.class);

		return customerDTO;
	}

	@Override
	public CustomerDTO getCustomerById(long customerId) throws CustomerException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerException("customer not found"));
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		return customerDTO;
	}

	

}
