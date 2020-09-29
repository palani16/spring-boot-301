package com.eatza.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class CustomerDTO {

	private long customerId;

	private String customerName;

	private long customerMobile;

	private byte customerAge;

	private String customerActiveStatus;
	
	private String orderStatus;
	

}
