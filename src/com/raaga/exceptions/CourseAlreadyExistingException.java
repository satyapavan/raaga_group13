package com.raaga.exceptions;


@SuppressWarnings("serial")
public class CourseAlreadyExistingException extends Exception {
	public CourseAlreadyExistingException() {
		super("Course Already Existing!!!");
	}
}