package com.raaga.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="RAAGA_COURSEDETAILS")
public class CourseDetailsEntity {
	@Id
	@SequenceGenerator(sequenceName="seq_id",name="cId",initialValue=5001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cId")
	
	private Integer courseId;
	private String courseName;
	private String courseType;
	private Character certification;
	private Integer duration;
	private Double fee;
	private String courseLevel;
	

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
	public String getCourseLevel() {
		return courseLevel;
	}
	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}

}
