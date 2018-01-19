package com.raaga.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.raaga.exceptions.CourseAlreadyExistingException;
import com.raaga.exceptions.InvalidCourseLevelException;
import com.raaga.exceptions.InvalidCourseNameException;
import com.raaga.exceptions.NoCourseForEditingException;
import com.raaga.exceptions.NoCourseFoundException;
import com.raaga.exceptions.NoCoursesAvailableException;
import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.exceptions.SkillNotFoundException;
import com.raaga.service.CourseDetailsService;
import com.raaga.to.CourseDetailsTO;
import com.raaga.utilities.ErrorLogger;


public class CourseDetailsManager {

	public CourseDetailsManager() {
	}
	public List<CourseDetailsTO> getCourseDetailsByInstructor(Integer courseId)
	throws Exception {
		CourseDetailsService courseDetailsService = new CourseDetailsService();
		List<CourseDetailsTO> list = new ArrayList<CourseDetailsTO>();
		try {
			list = courseDetailsService.getCourseDetailsByInstructor(courseId);
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getCourseDetailsByInstructor", e.getMessage());
			throw e;

		}
	}


	public Set<String> getAllCourseNames() throws NoCoursesAvailableException,
	Exception {


		CourseDetailsService courseDetailsService = new CourseDetailsService();
		Set<String> set = new TreeSet<String>();
		try {
			set = courseDetailsService.getAllCourseNames();
			if(set==null){
				throw new NoCoursesAvailableException();
			}
		} 
		catch (NoCoursesAvailableException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getAllCourseNames", e.getMessage());
			throw e;
		}

		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getAllCourseNames", e
					.getMessage());
			throw e;
		}
		return set;
	}
	public CourseDetailsTO getCourseId(String courseName) throws Exception {
		CourseDetailsService courseDetailsService = new CourseDetailsService();

		CourseDetailsTO courseDetailsTO = new CourseDetailsTO();
		try {
			courseDetailsTO = courseDetailsService.getCourseId(courseName);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseId", e
					.getMessage());
			throw e;
		}
		return courseDetailsTO;
	}

	
	public List<CourseDetailsTO> getCourseDetails(String courseName) throws Exception
	{
		try
		{
		List<CourseDetailsTO> l=new ArrayList<CourseDetailsTO>();
		
		CourseDetailsService service=new CourseDetailsService();
		l=service.getCourseDetails(courseName);


		return l;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseDetails", e
					.getMessage());
			throw e;
		}
	}



	public Integer addCourseDetails(CourseDetailsTO detailsTO) throws CourseAlreadyExistingException
	,SkillNotFoundException,Exception
	{
		try
		{
			CourseDetailsTO details=new CourseDetailsService().getCourseDetailsForEditing(detailsTO.getCourseName(),detailsTO.getCourseLevel());
			if(details!=null){
				throw new SkillNotFoundException();
			}
			else{
				Boolean bool=new CourseDetailsService().checkCourseForAdding(detailsTO.getCourseName());
				if(bool==false)
				{
					throw new CourseAlreadyExistingException();
					
				}
				Integer i=new CourseDetailsService().addCourseDetails(detailsTO);
				return i;
			}

		} 
		catch(CourseAlreadyExistingException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "addCourseDetails",
					e.getMessage());
			throw e;  
		}
		catch(SkillNotFoundException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "addCourseDetails",
					e.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addCourseDetails",
					e.getMessage());
			throw e;
		}

	}
	public List<CourseDetailsTO> getAllCourse() throws NoCourseFoundException,
	Exception {
		CourseDetailsService courseDetailsService = new CourseDetailsService();
		List<CourseDetailsTO> list = new ArrayList<CourseDetailsTO>();
		try {
			list = courseDetailsService.getAllCourse();
			if (list.isEmpty()) {
				throw new NoCourseFoundException();
			}

		} catch (NoCourseFoundException e) {
			ErrorLogger.logError(this.getClass().getName(), "getAllCourse", e
					.getMessage());
			throw e;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getAllCourse", e
					.getMessage());
			throw e;
		}

		return list;
	}



	public CourseDetailsTO getCourseDetail(Integer courseId) throws NoDetailsAvailableException,Exception
	{

		try {
			CourseDetailsService cd=new CourseDetailsService();

			CourseDetailsTO course=cd.getCourseDetail(courseId);
			if(course==null)
			{	  
				throw new NoDetailsAvailableException();  
			}
			return course;

		} 
		catch(NoDetailsAvailableException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "getCourseDetail", e
					.getMessage());
			throw e; 
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseDetail", e
					.getMessage());
			throw e;

		}
	}


	public String getSkillName(Integer skill) throws Exception
	{
		try {
			CourseDetailsService cds=new CourseDetailsService();
			return cds.getSkillName(skill);

		} catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(), "getSkillName", e
					.getMessage());
			throw e;
		}

	}
	public Set<String> getSelectedCourseLevels(String courseName)
	throws InvalidCourseLevelException,Exception {
		try {
			Set<String> set = new CourseDetailsService()
			.getSelectedCourseLevels(courseName);

			if (set.isEmpty()) {
				throw new InvalidCourseLevelException();
			}
			return set;
		}
		catch (InvalidCourseLevelException e) {

			ErrorLogger.logError(this.getClass().getName(),
					"getSelectedCourseLevels", e.getMessage());
			throw e;	
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getSelectedCourseLevels", e.getMessage());
			throw e;
		}
	}
	public void editCourse(CourseDetailsTO detailsTO) throws Exception {

		CourseDetailsService courseDetailsService = new CourseDetailsService();
		try {
			courseDetailsService.editCourse(detailsTO);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "editCourse", e.getMessage());
			throw e;
		}
	}
	public CourseDetailsTO getCoursesDetailsForEditing(String courseName,
			String courseLevel) throws NoCourseForEditingException,
			InvalidCourseNameException, Exception {
		CourseDetailsService courseDetailsService = new CourseDetailsService();
		CourseDetailsTO courseDetailsTO = new CourseDetailsTO();
		try {
			courseDetailsTO = courseDetailsService.getCoursesDetailsForEditing(
					courseName, courseLevel);
			if (courseDetailsTO == null) {

				throw new NoCourseForEditingException();
			}
		} catch (NoCourseForEditingException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getCoursesDetailsForEditing", e.getMessage());
			throw e;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCoursesDetailsForEditing",
					e.getMessage());
			throw e;
		}
		return courseDetailsTO;
	}	


}





