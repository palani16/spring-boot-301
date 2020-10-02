//package com.example.deliveryservice.service;
//
//import static org.junit.Assert.assertNotNull;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.lenient;
//import static org.mockito.Mockito.when;
//
//import org.junit.Test;
//
//import org.junit.runner.RunWith;
//
//import org.mockito.InjectMocks;
//
//import org.mockito.Mock;
//
//import org.mockito.junit.MockitoJUnitRunner;
//
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.eatza.deliveryservice.dto.OrderDeliveryDto;
//import com.eatza.deliveryservice.exception.DeliveryException;
//import com.eatza.deliveryservice.exception.ItemDeliveredException;
//import com.eatza.deliveryservice.model.Delivery;
//import com.eatza.deliveryservice.repository.DeliveryRepository;
//import com.eatza.deliveryservice.service.serviceimpl.DeliveryServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//public class DeliveryServiceTest {
//
//	@InjectMocks
//	private DeliveryServiceImpl deliveryServiceImpl;
//
//	@Mock
//	private DeliveryRepository deliveryRepository;
//
//	@Test
//	public void loggingOrderToDelivery() throws DeliveryException {
//		OrderDeliveryDto deliverItemDto = new OrderDeliveryDto(1L, 1L, 1L);
//		Delivery item = new Delivery(1L, 1L, 1L, "Not Yet Deliver");
//		Delivery logged = new Delivery(1L, 1L, 1L, 1L, "Not Yet Deliver", java.time.LocalDateTime.now(),
//				java.time.LocalDateTime.now());
//		logged.setId(1L);
//		logged.setLogTime(java.time.LocalDateTime.now());
//		logged.setDeliveredTime(java.time.LocalDateTime.now());
//		System.out.println("Items\n" + logged);
//		when(deliveryRepository.save(any(Delivery.class))).thenReturn(logged);
//		assertNotNull(deliveryServiceImpl.loggingOrderToDelivery(deliverItemDto));
//	}
//
//	@Test
//	public void itemsDelivering() throws DeliveryException, ItemDeliveredException {
//		OrderDeliveryDto deliveryItemDto = new OrderDeliveryDto(1L, 1L, 1L);
//		lenient().when(deliveryRepository.getDeliveryyBasedOnId(anyLong())).thenReturn(deliveryItemDto);
//		OrderDeliveryDto deliveryItemDto1 = new OrderDeliveryDto(1L, 1L, 1L);
//
//	}
//
//}
