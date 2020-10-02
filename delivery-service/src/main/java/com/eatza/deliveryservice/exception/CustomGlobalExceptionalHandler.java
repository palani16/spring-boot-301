package com.eatza.deliveryservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomGlobalExceptionalHandler extends ResponseEntityExceptionHandler{

	
	private static final Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionalHandler.class);

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> exception(UnauthorizedException exception) {
		logger.debug("Handling UnauthorizedException");
		 return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(DeliveryException.class)
	public ResponseEntity<Object> exception(DeliveryException exception) {
		logger.debug("Handling Delivery Exception");
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ItemDeliveredException.class)
	public ResponseEntity<Object> exception(ItemDeliveredException exception) {
		logger.debug("Handling Delivery Exception");
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	
}
