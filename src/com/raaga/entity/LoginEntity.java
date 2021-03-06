package com.raaga.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Raaga_Login")
public class LoginEntity {
	@Id
	private String userId;
	private String password;
	private Integer instructorId;
	private Integer traineeId;
	private Character role;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}
	public Integer getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}
	public Character getRole() {
		return role;
	}
	public void setRole(Character role) {
		this.role = role;
	}
	
	
}
