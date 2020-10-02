package com.eatza.deliveryservice.service;

import org.springframework.stereotype.Service;

import com.eatza.deliveryservice.dto.OrderDeliveryDto;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.exception.ItemDeliveredException;
import com.eatza.deliveryservice.exception.OrderClosedException;
import com.eatza.deliveryservice.model.Delivery;
@Service
public interface DeliveryService {

	Delivery loggingOrderToDelivery(OrderDeliveryDto deliveryDto) throws DeliveryException;

	Delivery itemsDelivering(Long orderId) throws ItemDeliveredException, DeliveryException;

	boolean closeOrder(Long orderId) throws OrderClosedException;

}
