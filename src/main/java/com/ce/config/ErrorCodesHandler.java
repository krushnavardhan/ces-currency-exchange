package com.ce.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ce.beans.ErrorResponse;

@RestControllerAdvice
public class ErrorCodesHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoHandlerFound(NoHandlerFoundException ex) {
		log.info("The URL you are trying to access does not exist.");
		ErrorResponse error = new ErrorResponse("NOT_FOUND", "The URL you are trying to access does not exist.");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
		log.info("HTTP method not allowed for this URL: {}", ex.getMethod());

		ErrorResponse error = new ErrorResponse("METHOD_NOT_ALLOWED", "This HTTP method is not allowed for this endpoint.");
		return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}
}
