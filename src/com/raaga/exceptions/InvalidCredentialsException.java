package com.raaga.exceptions;

@SuppressWarnings("serial")
public class InvalidCredentialsException extends Exception {

	public InvalidCredentialsException()
	{
		super("Invalid Login Credentials");
	}
}
