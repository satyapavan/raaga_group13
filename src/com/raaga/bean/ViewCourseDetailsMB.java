package com.raaga.bean;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.raaga.to.CourseDetailsTO;
import com.raaga.to.InstructorTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class ViewCourseDetailsMB {

	private List<SelectItem> courses=new ArrayList<SelectItem>();
	private String selectedCourseName;
	private List<CourseDetailsTO>  detailsTOs=new ArrayList<CourseDetailsTO>();
	private List<InstructorTO> instructorTOs=new ArrayList<InstructorTO>();
	private String message;

	public ViewCourseDetailsMB()
	{

		RaagaWrapper raagaWrapper=new RaagaWrapper();
		Set<String> courseNames=new LinkedHashSet<String>();
		this.message=null;
		try{
			courseNames=raagaWrapper.getAllCourseNames();

			
			for(String s:courseNames)
			{
				courses.add(new SelectItem(s));
				
			}

		}
		catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),
                    "ViewCourseDetailsMB", e.getMessage());

			this.message=e.getMessage();
			
		}

	}


	public void getSelectedCourse(ValueChangeEvent event)
	{

		this.selectedCourseName=event.getNewValue().toString();
		this.message=null;
		RaagaWrapper raagaWrapper=new RaagaWrapper();
		try{
			this.detailsTOs=raagaWrapper.getCourseDetails(this.selectedCourseName);
			this.instructorTOs=raagaWrapper.getInstructorDetails(this.selectedCourseName);
		}
		catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),
                    "getSelectedCourse", e.getMessage());
			this.setMessage(e.getMessage());

		}
	}

	public List<SelectItem> getCourses() {
		return courses;
	}
	public void setCourses(List<SelectItem> courses) {
		this.courses = courses;
	}
	public String getSelectedCourseName() {
		return selectedCourseName;
	}
	public void setSelectedCourseName(String selectedCourseName) {
		this.selectedCourseName = selectedCourseName;
	}
	public List<CourseDetailsTO> getDetailsTOs() {
		return detailsTOs;
	}
	public void setDetailsTOs(List<CourseDetailsTO> detailsTOs) {
		this.detailsTOs = detailsTOs;
	}
	public List<InstructorTO> getInstructorTOs() {
		return instructorTOs;
	}
	public void setInstructorTOs(List<InstructorTO> instructorTOs) {
		this.instructorTOs = instructorTOs;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}



}
