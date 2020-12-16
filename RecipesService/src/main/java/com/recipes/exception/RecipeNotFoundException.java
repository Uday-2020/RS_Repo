package com.recipes.exception;

/**
 * This is RecipeNotFoundException class.
 * @author Uday
 */


public class RecipeNotFoundException extends RuntimeException {

	public RecipeNotFoundException(String message) {
		super(message);
	}

	public RecipeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecipeNotFoundException(Throwable cause) {
		super(cause);
	}
}
