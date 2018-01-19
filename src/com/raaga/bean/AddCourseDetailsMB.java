package com.raaga.bean;

import com.raaga.to.CourseDetailsTO;
import com.raaga.utilities.ErrorLogger;

import com.raaga.wrapper.RaagaWrapper;

public class AddCourseDetailsMB {
	public String courseName;
	public String courseLevel;
	public String courseType;
	public Character certification;
	public Integer duration;
	public Double fee;
	public String message;

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseLevel() {
		return courseLevel;
	}
	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String addCourse()
	{

		try {
			this.message=null;
			CourseDetailsTO cto=new CourseDetailsTO();
			cto.setCertification(certification);
			cto.setCourseLevel(courseLevel);
			cto.setCourseName(courseName);
			cto.setCourseType(courseType);
			cto.setDuration(duration);
			cto.setFee(fee);
			RaagaWrapper wrap=new RaagaWrapper();
			this.message="Course added successfully with courseId:"+wrap.addCourseDetails(cto).toString();
			return "success";

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addCourse", e.getMessage());
			this.setMessage(e.getMessage());

			return "failure";
		}




	}
}
