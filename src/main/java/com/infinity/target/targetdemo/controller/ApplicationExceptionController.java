package com.infinity.target.targetdemo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.infinity.target.targetdemo.exception.ExceptionResponse;

@ControllerAdvice
public class ApplicationExceptionController extends ResponseEntityExceptionHandler {


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ExceptionResponse error = new ExceptionResponse();
		error.setMessage("Server Error");
		error.setDetails(details);
		error.setTimestamp( LocalDateTime.now());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ExceptionResponse error = new ExceptionResponse();
		error.setMessage("Validation Failed");
		error.setDetails(details);
		error.setTimestamp( LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ExceptionResponse error = new ExceptionResponse();
		error.setMessage("Invalid request method name");
		error.setDetails(details);
		error.setTimestamp( LocalDateTime.now());
		error.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity(error, HttpStatus.METHOD_NOT_ALLOWED);
	}
}
