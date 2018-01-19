package com.raaga.to;

public class CourseDetailsTO {
private Integer courseId;
private String courseName;
private String courseType;
private String courseLevel;
private Character certification;
private Integer duration;
private  Double fee;
public Integer getCourseId() {
	return courseId;
}
public void setCourseId(Integer courseId) {
	this.courseId = courseId;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getCourseType() {
	return courseType;
}
public void setCourseType(String courseType) {
	this.courseType = courseType;
}
public String getCourseLevel() {
	return courseLevel;
}
public void setCourseLevel(String courseLevel) {
	this.courseLevel = courseLevel;
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



}
