package com.johnmendes.crudspringbootrestjpa.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.johnmendes.crudspringbootrestjpa.entity.ErrorDetail;
import com.johnmendes.crudspringbootrestjpa.exceptions.AuthorExistException;
import com.johnmendes.crudspringbootrestjpa.exceptions.AuthorNotFoundException;
import com.johnmendes.crudspringbootrestjpa.exceptions.BookNotFoundException;

@ControllerAdvice //Used to catch all exception of the code
public class ResourceExceptionHandler {

	//Any error of BookNotFoundException will be catch.
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleBookNotFoundException(
			BookNotFoundException e, HttpServletRequest request){
		ErrorDetail error = new ErrorDetail();
		error.setStatus(404l);
		error.setTitle("The book cannot be found.");
		error.setMessageDeveloper("http://error.johnmendes.com/404");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(AuthorExistException.class)
	public ResponseEntity<ErrorDetail> handleAuthorExistException(
			AuthorExistException e, HttpServletRequest request){
		ErrorDetail error = new ErrorDetail();
		error.setStatus(409l);
		error.setTitle("Author doesn't exist.");
		error.setMessageDeveloper("http://error.johnmendes.com/409");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(AuthorNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleAuthorNotFoundException(
			AuthorNotFoundException e, HttpServletRequest request){
		ErrorDetail error = new ErrorDetail();
		error.setStatus(404l);
		error.setTitle("The Author cannot be found.");
		error.setMessageDeveloper("http://error.johnmendes.com/404");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorDetail> handleDataIntegrityViolationException(
			DataIntegrityViolationException e, HttpServletRequest request){
		ErrorDetail error = new ErrorDetail();
		error.setStatus(400l);
		error.setTitle("Invalid required.");
		error.setMessageDeveloper("http://error.johnmendes.com/409");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
}
