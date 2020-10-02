package com.eatza.deliveryservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.deliveryservice.dto.OrderDeliveryDto;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.exception.ItemDeliveredException;
import com.eatza.deliveryservice.model.Delivery;
import com.eatza.deliveryservice.service.DeliveryService;

@RestController
public class DeliveryServiceController {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryServiceController.class);

	@Autowired
	DeliveryService deliverServicee;

//	@PostMapping("/logging")
	
	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.consumer.group.id}")
	public ResponseEntity<Delivery> loggingFromOrderToDelivery(@RequestBody OrderDeliveryDto deliveryDto)
			throws DeliveryException {
		logger.info("Data - " + deliveryDto.toString() + " recieved");
		logger.debug("callling sevice method in delivering items");
		System.out.println("ITem" + deliveryDto);
		Delivery deliveryy = this.deliverServicee.loggingOrderToDelivery(deliveryDto);
		logger.debug("Item Logged Succesfullyy");
		return ResponseEntity.status(HttpStatus.OK).body(deliveryy);
	}

	@PutMapping("/delivery/{orderId}")
	public ResponseEntity<Delivery> itemsDelivering(@RequestHeader String authorizationn, @PathVariable Long orderId)
			throws ItemDeliveredException, DeliveryException {

		logger.debug("callling sevice method in delivering items");
		Delivery deliveryItem = this.deliverServicee.itemsDelivering(orderId);
		logger.debug("Item Delivered Succesfullyy");
		return ResponseEntity.status(HttpStatus.OK).body(deliveryItem);
	}
	
	@PutMapping("/delivery/close/{orderId}")
	public ResponseEntity<String> cancel(@RequestHeader String authorization, @PathVariable Long orderId) throws DeliveryException{
		logger.debug("In service method");
		boolean result =deliverServicee.closeOrder(orderId);
		if(result) {
			logger.debug("Order CLosed Successfully");
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Order Closed Successfully");
		} else {
			logger.debug("Order id Not Foundd");
			throw new DeliveryException("No records found for respective order id, it might be closed already.!");
		}	
	}
	
}
