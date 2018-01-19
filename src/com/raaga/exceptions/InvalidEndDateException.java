package com.raaga.exceptions;

@SuppressWarnings("serial")
public class InvalidEndDateException extends Exception {
	public InvalidEndDateException() {
		super("End Date Must be after Start Date!!!");
	}
}
