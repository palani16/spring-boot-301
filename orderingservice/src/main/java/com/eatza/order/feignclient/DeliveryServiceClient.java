package com.eatza.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.eatza.order.dto.OrderDeliveryDto;

@FeignClient("delivery-service")
public interface DeliveryServiceClient {

	@PostMapping("/logging")
	public ResponseEntity<OrderDeliveryDto> loggingOrderToDelivery(OrderDeliveryDto order);

}
