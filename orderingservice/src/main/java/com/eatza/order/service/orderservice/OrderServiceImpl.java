package com.eatza.order.service.orderservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.eatza.order.dto.CustomerDTO;
import com.eatza.order.dto.ItemFetchDto;
import com.eatza.order.dto.OrderRequestDto;
import com.eatza.order.dto.OrderUpdateDto;
import com.eatza.order.dto.OrderUpdateResponseDto;
import com.eatza.order.dto.OrderedItemsDto;
import com.eatza.order.exception.CustomerException;
import com.eatza.order.exception.OrderException;
import com.eatza.order.model.Order;
import com.eatza.order.model.OrderedItem;
import com.eatza.order.repository.OrderRepository;
import com.eatza.order.service.itemservice.ItemService;

import feign.FeignException;

@Service
public class OrderServiceImpl implements OrderService {

	

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	ItemService itemService;

	@Value("${restaurant.search.item.url}")
	private String restaurantServiceItemUrl;

	@Autowired
	RestTemplate restTemplate;
	

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

//	public Order defaultPlaceOrder(OrderRequestDto orderRequest) {
//		Order order = new Order();
//		order.setId(1l);
//		order.setStatus("server down with restaurent");
//		order.setRestaurantId(1L);
//		return order;
//	}

//	@Transactional(propagation = Propagation.REQUIRED)
////	@HystrixCommand(fallbackMethod="defaultPlaceOrder")
//	@Override
//	public Order placeOrder(String authorization, OrderRequestDto orderRequest)
//			throws OrderException, CustomerException {
//		ResponseEntity<CustomerDTO> customer = null;
//		try {
//			customer = customerService.getCustomerById(authorization, orderRequest.getCustomerId());
//		} catch (FeignException e) {
//			throw new CustomerException(e.getMessage());
//		}
//
//		if (customer.getBody() == null)
//			throw new CustomerException("customer not found");
//
//		Order order = new Order(orderRequest.getCustomerId(), "CREATED", orderRequest.getRestaurantId(),
//				new ArrayList<OrderedItem>());
//
//		logger.debug("Getting all ordered items to persist");
//		List<OrderedItemsDto> itemsDtoList = orderRequest.getItems();
//
//		for (OrderedItemsDto itemDto : itemsDtoList) {
//
//			try {
//				logger.debug("Calling restaurant search service to get item details");
//				ItemFetchDto item = restTemplate.getForObject(restaurantServiceItemUrl + itemDto.getItemId(),
//						ItemFetchDto.class);
//				if (item == null || (!(item.getMenu().getRestaurant().getId().equals(order.getRestaurantId())))
//						|| (itemDto.getQuantity() <= 0))
//					throw new OrderException("Item not found");
//
//				order.getOrderedItems()
//						.add(new OrderedItem(item.getName(), itemDto.getQuantity(), item.getPrice(), item.getId()));
//			} catch (ResourceAccessException e) {
//				throw new OrderException(
//						"Something went wrong, looks " + "like restaurant is currently not accepting orders");
//			}
//		}
//		logger.debug("Saved order to db");
//		Order savedOrder = orderRepository.saveAndFlush(order);
//
//		return savedOrder;
//	}

//	@HystrixCommand(fallbackMethod="defaultPlaceOrder")
	@Override
	public Order placeOrder(OrderRequestDto orderRequest) throws OrderException, CustomerException {
		ResponseEntity<CustomerDTO> customer;
		try {
			customer = customerService.getCustomerById(orderRequest.getCustomerId());

			// if (customer.getBody().getCustomerId() != orderRequest.getCustomerId())
			// throw new CustomerException("customer not exists with this id");
		} catch (FeignException | CustomerException e) {
			throw new CustomerException(e.getMessage());
		}

		logger.debug("In place order method, creating order object to persist");

		Order order = new Order(orderRequest.getCustomerId(), "CREATED", orderRequest.getRestaurantId());

		logger.debug("saving order in db");
		Order savedOrder = orderRepository.save(order);

		logger.debug("Getting all ordered items to persist");
		List<OrderedItemsDto> itemsDtoList = orderRequest.getItems();

		for (OrderedItemsDto itemDto : itemsDtoList) {
//			MappingJackson2HttpMessageConverter map = new MappingJackson2HttpMessageConverter();
//			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//			messageConverters.add(map);
//			messageConverters.add(new FormHttpMessageConverter());
//			restTemplate.setMessageConverters(messageConverters);
//			
			try {
				logger.debug("Calling restaurant search service to get item details");
				ItemFetchDto item = restTemplate.getForObject(restaurantServiceItemUrl + itemDto.getItemId(),
						ItemFetchDto.class);

				if (item == null) {

					orderRepository.delete(order);
					throw new OrderException("Item not found");

				}
				if (!(item.getMenu().getRestaurant().getId().equals(order.getRestaurantId()))) {
					// handle exception here later.
					orderRepository.delete(order);
					throw new OrderException("Item not in given restaurant");
				}
				if (itemDto.getQuantity() <= 0) {
					orderRepository.delete(order);
					throw new OrderException("Quantity of item cannot be 0");
				}
				OrderedItem itemToPersist = new OrderedItem(item.getName(), itemDto.getQuantity(), item.getPrice(),
						savedOrder, item.getId());
				itemService.saveItem(itemToPersist);
			} catch (ResourceAccessException e) {
				throw new OrderException(
						"Something went wrong, looks " + "like restaurant is currently not accepting orders");
			}
		}
		logger.debug("Saved order to db");
	//	customer.getBody().setOrderStatus("Ordered");
		//kafkaTemplate.send(TOPIC, customer.getBody());
		return savedOrder;
	}

	@Override
	public boolean cancelOrder(Long orderId) {
		logger.debug("In cancel order service method, calling repository");
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.isPresent()) {
			logger.debug("Order was found in db");
			order.get().setStatus("CANCELLED");
			orderRepository.save(order.get());
			return true;
		} else {
			logger.debug("Order not found");
			return false;
		}

	}

	@Override
	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public double getOrderAmountByOrderId(Long id) {

		Optional<Order> order = orderRepository.findById(id);
		if (order.isPresent()) {
			List<OrderedItem> itemsOrdered = itemService.findbyOrderId(id);
			double total = 0;
			for (OrderedItem item : itemsOrdered) {
				total = total + (item.getPrice() * item.getQuantity());
			}
			return total;
		} else {
			// handle exception here later.
			return 0;
		}

	}

	@Override
	public OrderUpdateResponseDto updateOrder(OrderUpdateDto orderUpdateRequest) throws OrderException {

		Order order = new Order(orderUpdateRequest.getCustomerId(), "UPDATED", orderUpdateRequest.getRestaurantId());
		Optional<Order> previouslyPersistedOrder = orderRepository.findById(orderUpdateRequest.getOrderId());

		if (!previouslyPersistedOrder.isPresent()) {
			// handle exception properly later
			throw new OrderException("Update Failed, respective order not found");
		}
		if (!(orderUpdateRequest.getRestaurantId().equals(previouslyPersistedOrder.get().getRestaurantId()))) {
			// handle exception properly later
			throw new OrderException("Update Failed, cannot change restaurants while updating order");

		}
		List<OrderedItem> previouslyOrderedItems = itemService.findbyOrderId(previouslyPersistedOrder.get().getId());
		order.setId(previouslyPersistedOrder.get().getId());
		order.setCreateDateTime(previouslyPersistedOrder.get().getCreateDateTime());
		List<OrderedItemsDto> itemsDtoList = orderUpdateRequest.getItems();
		List<OrderedItem> updateItemsListToReturn = new ArrayList<>();
		for (OrderedItemsDto itemDto : itemsDtoList) {
//			MappingJackson2HttpMessageConverter map = new MappingJackson2HttpMessageConverter();
//			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//			messageConverters.add(map);
//			messageConverters.add(new FormHttpMessageConverter());
//			restTemplate.setMessageConverters(messageConverters);
			try {
				ItemFetchDto item = restTemplate.getForObject(restaurantServiceItemUrl + itemDto.getItemId(),
						ItemFetchDto.class);
				if (item == null) {
					// deleting previously updated items
					for (OrderedItem itemsUpdateToBeReverted : updateItemsListToReturn) {
						itemService.deleteItemsById(itemsUpdateToBeReverted.getId());
					}
					throw new OrderException("Update Failed, item not found in menu");
				}
				if (item.getMenu().getRestaurant().getId() != order.getRestaurantId()) {
					// deleting previously updated items
					for (OrderedItem itemsUpdateToBeReverted : updateItemsListToReturn) {
						itemService.deleteItemsById(itemsUpdateToBeReverted.getId());
					}
					throw new OrderException("Update Failed, item does not belong to respective restaurant");
				}

				if (itemDto.getQuantity() <= 0) {
					// deleting previously updated items
					for (OrderedItem itemsUpdateToBeReverted : updateItemsListToReturn) {
						itemService.deleteItemsById(itemsUpdateToBeReverted.getId());
					}
					throw new OrderException("Update Failed, quantity cannot be zero");
				}

				OrderedItem itemToPersist = new OrderedItem(item.getName(), itemDto.getQuantity(), item.getPrice(),
						previouslyPersistedOrder.get(), item.getId());
				itemToPersist.setId(itemDto.getItemId());
				OrderedItem savedItem = itemService.saveItem(itemToPersist);
				updateItemsListToReturn.add(savedItem);
			} catch (ResourceAccessException e) {
				throw new OrderException(
						"Something went wrong, looks " + "like restaurant is currently not operatable");
			}

		}
		for (OrderedItem previouslyOrderedItem : previouslyOrderedItems) {
			itemService.deleteItemsById(previouslyOrderedItem.getId());
		}
		Order savedOrder = orderRepository.save(order);

		return new OrderUpdateResponseDto(savedOrder.getId(), savedOrder.getCustomerId(), savedOrder.getStatus(),
				savedOrder.getRestaurantId(), updateItemsListToReturn);

	}
}
