package com.johnmendes.crudspringbootrestjpa.exceptions;

public class BookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public BookNotFoundException (String message){
		super(message);
	}
	
	public BookNotFoundException (String message, Throwable motivation){
		super(message, motivation);
	}
}
