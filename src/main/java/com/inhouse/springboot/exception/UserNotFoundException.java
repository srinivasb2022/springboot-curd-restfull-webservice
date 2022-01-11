package com.inhouse.springboot.exception;

public class UserNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserNotFoundException(String message)
	{
		super(message);
	}

}
