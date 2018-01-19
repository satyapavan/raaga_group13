package com.raaga.exceptions;

@SuppressWarnings("serial")
public class InvalidClosureDateException extends Exception {

	public  InvalidClosureDateException()
	{
		super("Closure Date is not today");
	}
}
