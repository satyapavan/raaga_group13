package com.raaga.exceptions;

@SuppressWarnings("serial")
public class NotAValidBatchException extends Exception {
public NotAValidBatchException() {
	super("Not a Valid Batch");
}
}
