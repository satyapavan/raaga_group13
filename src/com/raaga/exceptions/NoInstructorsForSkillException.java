package com.raaga.exceptions;

@SuppressWarnings("serial")
public class NoInstructorsForSkillException  extends Exception{

	public  NoInstructorsForSkillException()
	{
		super("No Instructors for Skill Set");
	}
	
}
