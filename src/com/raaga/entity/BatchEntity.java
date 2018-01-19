package com.raaga.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="RAAGA_BATCHDETAILS")
public class BatchEntity {
	@Id
	@SequenceGenerator(name="batchid_seq",sequenceName="DB_batchid_seq",allocationSize=1,initialValue=2010)
	@GeneratedValue(generator="batchid_seq",strategy=GenerationType.SEQUENCE)
	private Integer batchId;
	private Integer courseId;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	private Integer batchStrength;
	private Integer venueId;
	private Character batchType;
	private Integer instructorId;
	@Temporal(TemporalType.DATE)
	private Date batchEndDate;
	@Temporal(TemporalType.DATE)
	private Date batchClosureDate;
	private Character batchClosureStatus;
	private List<TraineeEntity> traineeEntity;

	//setters
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getBatchStrength() {
		return batchStrength;
	}
	public void setBatchStrength(Integer batchStrength) {
		this.batchStrength = batchStrength;
	}
	public Integer getVenueId() {
		return venueId;
	}
	public void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}
	public Character getBatchType() {
		return batchType;
	}
	public void setBatchType(Character batchType) {
		this.batchType = batchType;
	}
	public Integer getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}
	public Date getBatchEndDate() {
		return batchEndDate;
	}
	public void setBatchEndDate(Date batchEndDate) {
		this.batchEndDate = batchEndDate;
	}
	public Date getBatchClosureDate() {
		return batchClosureDate;
	}
	public void setBatchClosureDate(Date batchClosureDate) {
		this.batchClosureDate = batchClosureDate;
	}
	public Character getBatchClosureStatus() {
		return batchClosureStatus;
	}
	public void setBatchClosureStatus(Character batchClosureStatus) {
		this.batchClosureStatus = batchClosureStatus;
	}
	public List<TraineeEntity> getTraineeEntity() {
		return traineeEntity;
	}
	public void setTraineeEntity(List<TraineeEntity> traineeEntity) {
		this.traineeEntity = traineeEntity;
	}
}
