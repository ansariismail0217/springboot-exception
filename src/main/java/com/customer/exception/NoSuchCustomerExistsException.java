package com.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchCustomerExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NoSuchCustomerExistsException() {}
	
	public NoSuchCustomerExistsException(String msg) {
		super(msg);
	}

}
