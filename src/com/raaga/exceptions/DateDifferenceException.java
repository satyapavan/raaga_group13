package com.raaga.exceptions;

@SuppressWarnings("serial")
public class DateDifferenceException extends Exception {

	public  DateDifferenceException()
	{
		super("From Date should be less than To Date ");
	}
}
