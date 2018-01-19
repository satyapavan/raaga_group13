package com.raaga.exceptions;

@SuppressWarnings("serial")
public class NoCoursesAvailableException extends Exception {
	public NoCoursesAvailableException() {
		super("No Courses Available");
	}
}
