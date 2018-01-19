package com.raaga.exceptions;
@SuppressWarnings("serial")
public class InvalidBatchClosureDateException extends Exception {
	public InvalidBatchClosureDateException() {
		super("Batch Closing Date Must be greater than or equal to actual last date");
	}

}
