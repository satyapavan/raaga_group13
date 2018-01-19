package com.raaga.exceptions;

@SuppressWarnings("serial")
public class InvalidClosureRequestException extends Exception {
	public InvalidClosureRequestException() {
		super("Batch Cannot be Closed");
	}
	}
