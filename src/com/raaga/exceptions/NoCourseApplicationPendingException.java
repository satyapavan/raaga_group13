package com.raaga.exceptions;

@SuppressWarnings("serial")
public class NoCourseApplicationPendingException extends Exception {
	public NoCourseApplicationPendingException() {
		super("No Course Applications Pending");
	}
}
