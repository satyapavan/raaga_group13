package com.raaga.exceptions;

@SuppressWarnings("serial")
public class InvalidBatchStartDateException extends Exception{

	public InvalidBatchStartDateException()
	{
	super("Invalid batch start date");
	}
}
