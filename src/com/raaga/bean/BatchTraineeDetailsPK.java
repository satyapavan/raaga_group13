package com.raaga.bean;

import javax.persistence.Embeddable;

@Embeddable
public class BatchTraineeDetailsPK {

	private int batchId;
	private int traineeId;
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public int getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	
}
