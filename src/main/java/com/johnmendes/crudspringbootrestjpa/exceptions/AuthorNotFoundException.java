package com.johnmendes.crudspringbootrestjpa.exceptions;

public class AuthorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public AuthorNotFoundException (String message){
		super(message);
	}
	
	public AuthorNotFoundException (String message, Throwable motivation){
		super(message, motivation);
	}
}
