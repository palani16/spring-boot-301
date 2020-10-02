package com.eatza.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class OrderDeliveryDto {

	private Long orderId;
	private Long restaurantId;
	private Long customerId;
	// private String status;

	public OrderDeliveryDto(Long orderId, Long restaurantId, Long customerId) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.customerId = customerId;
	}

}
