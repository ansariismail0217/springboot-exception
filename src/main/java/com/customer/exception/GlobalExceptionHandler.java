package com.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NoSuchCustomerExistsException.class)
	public @ResponseBody ErrorResponse handleNoSuchCustomerException(NoSuchCustomerExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(value = CustomerAlreadyExistsException.class)
	public @ResponseBody ErrorResponse handleCustomerExistsException(CustomerAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.toString(), ex.getMessage());
	}
}