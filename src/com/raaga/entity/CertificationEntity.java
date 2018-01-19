package com.raaga.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="RAAGA_CERTIFICATION")
public class CertificationEntity {
	@Id
	@SequenceGenerator(name="cerId",sequenceName="db_cerId",initialValue=8001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cerId")
	private Integer certificationId;
	private Integer traineeId;
	private Integer courseId;
	@Temporal(TemporalType.DATE)
	private Date testDate;
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
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

}
