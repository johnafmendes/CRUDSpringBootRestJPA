package com.johnmendes.crudspringbootrestjpa.exceptions;

public class AuthorExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public AuthorExistException (String message){
		super(message);
	}
	
	public AuthorExistException (String message, Throwable motivation){
		super(message, motivation);
	}
}
