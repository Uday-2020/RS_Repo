package com.recipes.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This is RecipeExceptionResponse.
 * @author Uday
 */
public class RecipeExceptionResponse {
	private int status;
	private String message;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime timeStamp;

	public RecipeExceptionResponse() {

	}

	public RecipeExceptionResponse(int status, String message, LocalDateTime timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
}
