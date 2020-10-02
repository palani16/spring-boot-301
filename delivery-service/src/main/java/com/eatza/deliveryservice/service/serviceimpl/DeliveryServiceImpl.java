package com.eatza.deliveryservice.service.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatza.deliveryservice.controller.DeliveryServiceController;
import com.eatza.deliveryservice.dto.OrderDeliveryDto;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.exception.ItemDeliveredException;
import com.eatza.deliveryservice.exception.OrderClosedException;
import com.eatza.deliveryservice.model.Delivery;
import com.eatza.deliveryservice.repository.DeliveryRepository;
import com.eatza.deliveryservice.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	DeliveryRepository deliveryRepository;

	private static final Logger logger = LoggerFactory.getLogger(DeliveryServiceController.class);

	@Override
	public Delivery loggingOrderToDelivery(OrderDeliveryDto deliveryDto) throws DeliveryException {
		logger.debug("Logg-in the order");
		try {
			Long restaurantId = deliveryDto.getRestaurantId();
			System.out.println("Res Id" + restaurantId);
			Long customerId = deliveryDto.getCustomerId();
			System.out.println("Cus Id" + customerId);
			Long orderId = deliveryDto.getOrderId();
			System.out.println("O.ID" + orderId);
			Delivery deliveryy = new Delivery(orderId, customerId, restaurantId, "Still Not Delivered");
			System.out.println(deliveryy);
			logger.debug("Registring the order");
			deliveryRepository.save(deliveryy);

			logger.debug("Order addded Successfully");
			return deliveryy;
		} catch (Exception e) {
			throw new DeliveryException("Oopss...Something went wrong, Please try again : " + e);
		}
	}

	@Override
	public Delivery itemsDelivering(Long orderId) throws ItemDeliveredException, DeliveryException {
		logger.debug("Delivering the itemss");
		Delivery deliveryItems = this.deliveryRepository.getDeliveryyBasedOnId(orderId);
		System.out.println("Item" + deliveryItems);
		if (deliveryItems.getStatus().equalsIgnoreCase("Still Not Delivered")) {
			deliveryItems.setStatus("Delivered");
			this.deliveryRepository.save(deliveryItems);
		} else {
			throw new ItemDeliveredException("Order already delivered, Please Check again");
		}
		return deliveryItems;
	}

	@Override
	public boolean closeOrder(Long orderId) throws OrderClosedException {
		logger.debug("Closing ORderss");
		Optional<Delivery> order = deliveryRepository.findById(orderId);
		if (order.isPresent()) {
			logger.debug("Order was found in db");
			if(order.get().getStatus().equalsIgnoreCase("Delivered")) {
			order.get().setStatus("Closed");
			deliveryRepository.save(order.get());
			return true;
		}else{
			throw new OrderClosedException("ORder already CLosed");
			}
		}
		else {
			logger.debug("OrderId not found");
			return false;
		}
	}

}
