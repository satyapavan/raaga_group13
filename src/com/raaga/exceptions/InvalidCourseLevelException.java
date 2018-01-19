package com.raaga.exceptions;

@SuppressWarnings("serial")
public class InvalidCourseLevelException extends Exception {
	public InvalidCourseLevelException(){
		super("Invalid Course Level");
	}
}
