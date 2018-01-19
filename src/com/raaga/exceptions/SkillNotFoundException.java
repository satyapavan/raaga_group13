package com.raaga.exceptions;

@SuppressWarnings("serial")
public class SkillNotFoundException extends Exception {
	public SkillNotFoundException() {
		super("Skill Not Found!!");
	}
}