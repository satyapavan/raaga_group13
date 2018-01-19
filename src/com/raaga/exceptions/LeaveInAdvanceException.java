package com.raaga.exceptions;

@SuppressWarnings("serial")
public class LeaveInAdvanceException extends Exception {
	
	public  LeaveInAdvanceException()
	{
		super("Leave should be applied 5 days in advance");
	}

}
