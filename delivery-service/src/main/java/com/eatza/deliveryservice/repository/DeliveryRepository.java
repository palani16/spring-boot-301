package com.eatza.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eatza.deliveryservice.model.Delivery;
@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>{

	@Query(value="Select i from Delivery i where i.orderId=:id")
	Delivery getDeliveryyBasedOnId(@Param("id") Long orderId);

	
}
