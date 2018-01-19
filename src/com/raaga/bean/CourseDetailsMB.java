package com.raaga.bean;

import java.util.ArrayList;
import java.util.List;

import com.raaga.to.CourseDetailsTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class CourseDetailsMB {
	private List<CourseDetailsTO> courseList;
	private String message;
	public List<CourseDetailsTO> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<CourseDetailsTO> courseList) {
		this.courseList = courseList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CourseDetailsMB()
	{
		this.message=null;
		this.courseList=new ArrayList<CourseDetailsTO>();
		try
		{	
			this.courseList=new RaagaWrapper().getAllCourse();

		}

		catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(),
					"CourseDetailsMB", e .getMessage());

			this.message=e.getMessage();
		}
	}

}
