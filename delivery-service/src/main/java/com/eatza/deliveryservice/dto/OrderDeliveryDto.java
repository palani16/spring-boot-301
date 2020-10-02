package com.eatza.deliveryservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OrderDeliveryDto {

	private Long orderId;
	private Long restaurantId;
	private Long customerId;

	public OrderDeliveryDto(Long orderId, Long restaurantId, Long customerId) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.customerId = customerId;
	}

}
