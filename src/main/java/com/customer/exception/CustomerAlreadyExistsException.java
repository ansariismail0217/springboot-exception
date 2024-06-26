package com.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CustomerAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CustomerAlreadyExistsException() {}
	
	public CustomerAlreadyExistsException(String msg) {
		super(msg);
	}	

}
