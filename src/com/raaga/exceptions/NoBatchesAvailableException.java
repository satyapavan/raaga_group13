package com.raaga.exceptions;

@SuppressWarnings("serial")
public class NoBatchesAvailableException extends Exception {

	public  NoBatchesAvailableException ()
	{
		super("No Batches Available");
	}
}
