package com.raaga.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
@Entity
@Table(name="RAAGA_COURSEAPPLICATION")
public class CourseApplicationEntity {
	@Id
	@SequenceGenerator(sequenceName="Seq_appId",name="Seq_appId",initialValue=7011,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Seq_appId")
	private Integer applicationId;
	private Integer traineeId;
	private Integer courseId;
	private Double feePaid;
	private Character applicationStatus;
	@Temporal(TemporalType.DATE)
	private Date dateOfApplication;
	
	//setters
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


}
