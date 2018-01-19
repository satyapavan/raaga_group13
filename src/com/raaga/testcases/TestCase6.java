package com.raaga.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.raaga.exceptions.CourseAlreadyExistingException;
import com.raaga.exceptions.InvalidInstructorIdException;
import com.raaga.exceptions.SkillNotFoundException;
import com.raaga.manager.CourseDetailsManager;
import com.raaga.manager.InstructorManager;
import com.raaga.to.CourseDetailsTO;

public class TestCase6 {

	
	@Test(expected=CourseAlreadyExistingException.class)
	public void testAddCourseDetailsInvalidCourseName() throws CourseAlreadyExistingException,SkillNotFoundException,Exception
	{
		CourseDetailsTO courseDetailsTO=new CourseDetailsTO();
		courseDetailsTO.setCourseName("Flute");
		courseDetailsTO.setCourseLevel("basic");
		courseDetailsTO.setCourseType("Music");
		courseDetailsTO.setCertification('N');
		courseDetailsTO.setDuration(12);
		courseDetailsTO.setFee(4500.0);
		@SuppressWarnings("unused")
		Integer i=new CourseDetailsManager().addCourseDetails(courseDetailsTO);
		
	} 
	

	
	@Test
	public void testAddCourseDetails() throws CourseAlreadyExistingException,SkillNotFoundException,Exception
	{
		CourseDetailsTO courseDetailsTO=new CourseDetailsTO();
		courseDetailsTO.setCourseName("Bassicls");
		courseDetailsTO.setCourseLevel("Basic");
		courseDetailsTO.setCourseType("Music"); 
		courseDetailsTO.setCertification('N');
		courseDetailsTO.setDuration(25);
		courseDetailsTO.setFee(1000.0);
		Integer i=new CourseDetailsManager().addCourseDetails(courseDetailsTO);
		assertTrue(i!=0);
	}
	
	
	@Test(expected=SkillNotFoundException.class)
	public void testAddCourseDetailsInvalid() throws CourseAlreadyExistingException,SkillNotFoundException,Exception
	{
		CourseDetailsTO courseDetailsTO=new CourseDetailsTO();
		courseDetailsTO.setCourseName("tab1");
		courseDetailsTO.setCourseLevel("basic");
		courseDetailsTO.setCourseType("Music");
		courseDetailsTO.setCertification('Y');
		courseDetailsTO.setDuration(20);
		courseDetailsTO.setFee(2000.0);
		@SuppressWarnings("unused")
		Integer i=new CourseDetailsManager().addCourseDetails(courseDetailsTO);
		
	}

	 
	
	@Test
	public void testGetInstructorDetailValid() throws InvalidInstructorIdException,Exception
	{
		new InstructorManager().getInstructorDetail(10001);
	}
}
