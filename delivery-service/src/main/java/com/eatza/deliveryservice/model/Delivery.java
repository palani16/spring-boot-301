package com.eatza.deliveryservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long orderId;
	private Long restaurantId;

	private Long customerId;

	private String status;

	@CreationTimestamp
	private LocalDateTime logTime;

	@CreationTimestamp
	private LocalDateTime deliveredTime;

	public Delivery(Long orderId, Long restaurantId, Long customerId, String status) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.customerId = customerId;
		this.status = status;
	}

}
