package com.raaga.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="Raaga_Batch_TraineeDetails")
@IdClass(BatchDetailsPK.class)
public class BatchTraineeDetailsEntity {
		
	@Id
	private Integer batchId;
	@Id
	private Integer traineeId;
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	public Integer getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}
	
		

		
		
	}
