package com.raaga.exceptions;

@SuppressWarnings("serial")
public class InvalidUsernameException extends Exception {

	public InvalidUsernameException()
	{
		super("Invalid User Name");
	}
}
