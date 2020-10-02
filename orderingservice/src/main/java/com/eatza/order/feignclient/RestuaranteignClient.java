package com.eatza.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eatza.order.dto.ItemFetchDto;

@FeignClient("RESTAURANT-SERVICE")
public interface RestuaranteignClient {

	@GetMapping("/item/id/{id}")
	public ItemFetchDto getItemById( @PathVariable Long id);
	
}
