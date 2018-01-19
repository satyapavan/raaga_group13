package com.raaga.exceptions;

@SuppressWarnings("serial")
public class NoPendingCustomerRegistrationException extends Exception {
public NoPendingCustomerRegistrationException() {
	super("No Pending Customer Registration!!");
}
}
