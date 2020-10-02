//package com.mindtree.customer.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.ContainerProperties.AckMode;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import com.mindtree.customer.dto.Order;
//
//@EnableKafka
//@Configuration
//public class KafkaConfigConsumer {
//
////	@Bean
////	public ConsumerFactory<String, Order> userConsumerFactory() {
////		Map<String, Object> props = new HashMap<>();
////		JsonDeserializer<Order> deserializer = new JsonDeserializer<>(Order.class);
////		deserializer.setRemoveTypeHeaders(false);
////		deserializer.addTrustedPackages("*");
////		deserializer.setUseTypeMapperForKey(true);
////		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
////		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
////		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
////
////		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
////	}
////
////	@Bean
////	public ConcurrentKafkaListenerContainerFactory<String, Order> userKafkaListenerFactory() {
////		ConcurrentKafkaListenerContainerFactory<String, Order> factory = new ConcurrentKafkaListenerContainerFactory<>();
////		factory.setConsumerFactory(userConsumerFactory());
////		return factory;
////	}
////
//	@Value("${kafka.url}")
//	private String kafka_Url;
//
//	@Bean
//	public ConsumerFactory<String, Order> userConsumerFactory() {
//		Map<String, Object> config = new HashMap<>();
//		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka_Url);
//		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
//		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//		//config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//      
//		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
//				new JsonDeserializer<>(Order.class, false));
//	}
//
////	@Bean
////	public ConsumerFactory<String, Order> userConsumerFactory() {
////		Map<String, Object> config = new HashMap<>();
////		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
////		// config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
////		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
////		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
////				new JsonDeserializer<>(Order.class, false));
////	}
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, Order> userKafkaListenerFactory() {
//		ConcurrentKafkaListenerContainerFactory<String, Order> factory = new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(userConsumerFactory());
//		// to avoid exceptions in kafka listner
//		factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
//		return factory;
//	}
//}
