package com.raaga.exceptions;

@SuppressWarnings("serial")
public class NoCourseForEditingException extends Exception {

	public NoCourseForEditingException() {
		
		super("Courses cannot be edited as batches are scheduled for this course");
	}

	

}
