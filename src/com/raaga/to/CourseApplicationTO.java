package com.raaga.to;
import java.util.Date;


public class CourseApplicationTO {
private Integer applicationId;
private Integer traineeId;
private String traineeName;
private Integer courseId;
private Double feePaid;
private Character applicationStatus;
private Date dateOfApplication;
private boolean checked;
public Integer getApplicationId() {
	return applicationId;
}
public void setApplicationId(Integer applicationId) {
	this.applicationId = applicationId;
}
public Integer getTraineeId() {
	return traineeId;
}
public void setTraineeId(Integer traineeId) {
	this.traineeId = traineeId;
}
public String getTraineeName() {
	return traineeName;
}
public void setTraineeName(String traineeName) {
	this.traineeName = traineeName;
}
public Integer getCourseId() {
	return courseId;
}
public void setCourseId(Integer courseId) {
	this.courseId = courseId;
}
public Double getFeePaid() {
	return feePaid;
}
public void setFeePaid(Double feePaid) {
	this.feePaid = feePaid;
}
public Character getApplicationStatus() {
	return applicationStatus;
}
public void setApplicationStatus(Character applicationStatus) {
	this.applicationStatus = applicationStatus;
}
public Date getDateOfApplication() {
	return dateOfApplication;
}
public void setDateOfApplication(Date dateOfApplication) {
	this.dateOfApplication = dateOfApplication;
}
public boolean isChecked() {
	return checked;
}
public void setChecked(boolean checked) {
	this.checked = checked;
}

}
