package com.raaga.exceptions;

@SuppressWarnings("serial")
public class WrongPasswordException extends Exception  {

	public WrongPasswordException()
	{
		super("Wrong Password");
	}
}
