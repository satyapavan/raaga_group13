package com.raaga.to;

import java.util.Date;

public class TraineeTO {
private Integer traineeId;
private String traineeName;
private Date dateOfJoining;

private Integer registrationId;

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

public Date getDateOfJoining() {
	return dateOfJoining;
}

public void setDateOfJoining(Date dateOfJoining) {
	this.dateOfJoining = dateOfJoining;
}

public Integer getRegistrationId() {
	return registrationId;
}

public void setRegistrationId(Integer registrationId) {
	this.registrationId = registrationId;
}



}
