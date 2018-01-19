package com.raaga.exceptions;

@SuppressWarnings("serial")
public class NoVenuesAvailableException extends Exception{
	public NoVenuesAvailableException(){
		super("No Venues Available");
	}
}
