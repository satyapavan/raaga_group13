package com.raaga.exceptions;


@SuppressWarnings("serial")
public class DuplicateEmailIdException extends Exception{

	public DuplicateEmailIdException()
	{
		super("This EmailId has already been used for registration.Please use another EmailId.");
	}
	
	
}
