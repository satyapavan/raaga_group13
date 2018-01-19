package com.raaga.exceptions;

@SuppressWarnings("serial")
public class NoApplicationsSelectedException extends Exception {
	public NoApplicationsSelectedException() {
		super("No Applications Selected");
	}
}
