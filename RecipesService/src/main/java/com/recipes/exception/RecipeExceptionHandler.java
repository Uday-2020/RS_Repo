package com.recipes.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This is RecipeExceptionHandler class. it handles all exceptions occurs in
 * recipes service.
 * 
 * @author Uday
 */

@ControllerAdvice
public class RecipeExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RecipeExceptionHandler.class);

	@ExceptionHandler(RecipeNotFoundException.class)
	public ResponseEntity<RecipeExceptionResponse> handleException(RecipeNotFoundException rnfe) {
		RecipeExceptionResponse errorResponse = new RecipeExceptionResponse();
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(rnfe.getMessage());
		errorResponse.setTimeStamp(LocalDateTime.now());
		logger.error(getString(rnfe));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RecipeExceptionResponse> handleException(MethodArgumentNotValidException ex) {
		RecipeExceptionResponse errorResponse = new RecipeExceptionResponse();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(LocalDateTime.now());
		logger.error(getString(ex));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RecipeExceptionResponse> handleException(Exception ex) {
		RecipeExceptionResponse errorResponse = new RecipeExceptionResponse();
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(LocalDateTime.now());
		logger.error(getString(ex));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	
	/**
	 * This method is used to convert the printstacktrace to string.
	 * 
	 * @param Throwable
	 * @return String.
	 */
	private String getString(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
}
