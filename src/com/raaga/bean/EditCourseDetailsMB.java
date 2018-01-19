package com.raaga.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.raaga.to.CourseDetailsTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class EditCourseDetailsMB {
	private Character certification;
	private Integer duration;
	private Double fee;
	private String selectedCourse;
	private String selectedLevel;
	private CourseDetailsTO detailsTO;
	private List<SelectItem> courseNames;
	private List<SelectItem> courseLevels;
	private Boolean editCheck;
	private String message;

	public Character getCertification() {
		return certification;
	}

	public void setCertification(Character certification) {
		this.certification = certification;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(String selectedCourse) {
		this.selectedCourse = selectedCourse;
	}

	public String getSelectedLevel() {
		return selectedLevel;
	}

	public void setSelectedLevel(String selectedLevel) {
		this.selectedLevel = selectedLevel;
	}

	public CourseDetailsTO getDetailsTO() {
		return detailsTO;
	}

	public void setDetailsTO(CourseDetailsTO detailsTO) {
		this.detailsTO = detailsTO;
	}

	public List<SelectItem> getCourseNames() {
		return courseNames;
	}

	public void setCourseNames(List<SelectItem> courseNames) {
		this.courseNames = courseNames;
	}

	public List<SelectItem> getCourseLevels() {
		return courseLevels;
	}

	public void setCourseLevels(List<SelectItem> courseLevels) {
		this.courseLevels = courseLevels;
	}

	public Boolean getEditCheck() {
		return editCheck;
	}

	public void setEditCheck(Boolean editCheck) {
		this.editCheck = editCheck;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EditCourseDetailsMB() {
		this.message=null;
		this.courseNames = new ArrayList<SelectItem>();
		try {
			Set<String> set = new RaagaWrapper().getAllCourseNames();
			for (String iti : set) {
				this.courseNames.add(new SelectItem(iti));
			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"EditCourseDetailsMB", e.getMessage());
			this.message=e.getMessage();
		}

	}

	public void getSelectedCourseName(ValueChangeEvent event) {
		this.message = null;
		this.courseLevels = new ArrayList<SelectItem>();
		String str = (String) event.getNewValue();
		try {
			Set<String> set = new RaagaWrapper().getSelectedCourseLevels(str);
			for (String iti : set) {
				this.courseLevels.add(new SelectItem(iti));
			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getSelectedCourseName", e.getMessage());
			this.setMessage(e.getMessage());
		}
	}

	public void getSelectedLevelName(ValueChangeEvent event) {
		this.message = null;
		String str = (String) event.getNewValue();
		try {
			this.detailsTO = new RaagaWrapper().getCoursesDetailsForEditing(this.selectedCourse, str);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getSelectedLevelName", e.getMessage());
			this.setMessage(e.getMessage());
		}
	}

	public String editCourse() {
		try {
			this.message=null;
			detailsTO.setCourseName(this.selectedCourse);
			detailsTO.setCourseLevel(this.selectedLevel);
			detailsTO.setCertification(this.certification);
			detailsTO.setDuration(this.duration);
			detailsTO.setFee(this.fee);
			new RaagaWrapper().editCourse(detailsTO);
			this.setMessage("Course Edited Successfully");
			return "success";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "editCourse", e
					.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
	}
}
