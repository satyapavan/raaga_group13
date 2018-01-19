package com.raaga.to;

import java.util.Date;

public class CertificationTO {
private Integer certificationId;
private Integer traineeId;
private Date testDate;
private String courseName;
private String courseLevel;
private String courseType;
private String traineeName;
private Date dateOfJoining;
public Integer getCertificationId() {
	return certificationId;
}
public void setCertificationId(Integer certificationId) {
	this.certificationId = certificationId;
}
public Integer getTraineeId() {
	return traineeId;
}
public void setTraineeId(Integer traineeId) {
	this.traineeId = traineeId;
}
public Date getTestDate() {
	return testDate;
}
public void setTestDate(Date testDate) {
	this.testDate = testDate;
}
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
public String getTraineeName() {
	return traineeName;
}
public void setTraineeName(String traineeName) {
	this.traineeName = traineeName;
}
public Date getDateOfJoining() {
	return dateOfJoining;
}
public void setDateOfJoining(Date dateOfJoining) {
	this.dateOfJoining = dateOfJoining;
}



}
