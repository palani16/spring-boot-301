
package com.mindtree.customer.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class KafkaConfigConsumerTest {

	@InjectMocks
	KafkaConfigConsumer kafkaConfiguration;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void userConsumerFactory() {
		kafkaConfiguration.userConsumerFactory();
		assertNotNull(kafkaConfiguration.userConsumerFactory());
	}

	@Test
	public void userKafkaListenerFactory() {
		kafkaConfiguration.userKafkaListenerFactory();
		assertNotNull(kafkaConfiguration.userKafkaListenerFactory());
	}
}
