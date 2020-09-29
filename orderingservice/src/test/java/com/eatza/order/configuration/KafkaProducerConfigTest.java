//package com.eatza.order.configuration;
//
//import static org.junit.Assert.assertNotNull;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.eatza.order.config.RestTemplateClient;
//import com.eatza.order.model.Order;
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = RestTemplateClient.class)
//class KafkaProducerConfigTest {
//
//	@Autowired
//	private KafkaTemplate<String, Order> kafkaTemplate;
//
//	@Test
//	public void kafkaTemplateTest() {
//		assertNotNull(kafkaTemplate);
//	}
//
//}
