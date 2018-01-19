package com.raaga.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RAAGA_INSTRUCTORALLOCATION")

public class InstructorAllocationEntity {
	@Id
	private Integer instructorAllocationId;
	private Integer batchId;
	private Integer instructorId;
	private Character acceptanceStatus;

	public Integer getInstructorAllocationId() {
		return instructorAllocationId;
	}
	public void setInstructorAllocationId(Integer instructorAllocationId) {
		this.instructorAllocationId = instructorAllocationId;
	}
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	public Integer getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}
	public Character getAcceptanceStatus() {
		return acceptanceStatus;
	}
	public void setAcceptanceStatus(Character acceptanceStatus) {
		this.acceptanceStatus = acceptanceStatus;
	}


}
