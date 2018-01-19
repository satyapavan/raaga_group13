package com.raaga.exceptions;

@SuppressWarnings("serial")
public class LeaveExceededException extends Exception {

	public  LeaveExceededException()
	{
		super("Leave cannot be exceeded more than 5 days");
	}
	
}
