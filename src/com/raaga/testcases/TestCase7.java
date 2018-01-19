
package com.raaga.testcases;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.raaga.exceptions.InvalidCourseLevelException;
import com.raaga.manager.CourseDetailsManager;
import com.raaga.to.CourseDetailsTO;

public class TestCase7 
{

	@Test
	public void testGetSelectedCourseLevels() throws  Exception {
		Set<String> set=new CourseDetailsManager().getSelectedCourseLevels("Bass Guitar");
		assertTrue(set!=null);
	}
	@Test(expected=InvalidCourseLevelException.class)
	public void testGetSelectedCourseLevelsCourseName() throws  Exception {
		new CourseDetailsManager().getSelectedCourseLevels("dance");

	}

	@Test
	public void testGetCoursesDetailsForEditing() throws  Exception {
		CourseDetailsTO cust=new CourseDetailsManager().getCoursesDetailsForEditing("Khatak", "Basic");
		assertTrue(cust!=null);
	}



	@Test
	public void testGetAllCourseNames() throws Exception {
		Set<String> set =new CourseDetailsManager().getAllCourseNames();
		assertTrue(set!=null);
	}


	@Test
	public void testGetCourseDetail() throws Exception {
		CourseDetailsTO cust=new CourseDetailsManager().getCourseDetail(5001);
		assertTrue(cust!=null);

	}
	@Test
	public void testEditCourse() throws Exception {
		CourseDetailsTO courseDetailsTO=new CourseDetailsTO();
		courseDetailsTO.setCertification('N');
		courseDetailsTO.setCourseId(5001);
		courseDetailsTO.setCourseLevel("Basic");
		courseDetailsTO.setCourseName("Guitar");
		courseDetailsTO.setCourseType("music");
		courseDetailsTO.setDuration(20);
		courseDetailsTO.setFee(2000.0);
		new CourseDetailsManager().editCourse(courseDetailsTO);


	}
	@Test
	public void testInvalidEditCourse() throws Exception {
		CourseDetailsTO courseDetailsTO=new CourseDetailsTO();
		courseDetailsTO.setCertification('N');
		courseDetailsTO.setCourseId(5001);
		courseDetailsTO.setCourseLevel("Advanced");
		courseDetailsTO.setCourseName("Drums");
		courseDetailsTO.setCourseType("Dance");
		courseDetailsTO.setDuration(20);
		courseDetailsTO.setFee(2000.0);
		new CourseDetailsManager().editCourse(courseDetailsTO);

	}
	

}


