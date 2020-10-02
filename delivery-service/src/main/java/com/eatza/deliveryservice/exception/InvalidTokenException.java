package com.eatza.deliveryservice.exception;

public class InvalidTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;

	public InvalidTokenException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidTokenException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
		// TODO Auto-generated constructor stub
	}

	public InvalidTokenException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidTokenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidTokenException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
