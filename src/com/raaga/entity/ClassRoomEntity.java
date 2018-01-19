package com.raaga.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="RAAGA_CLASSROOM")
public class ClassRoomEntity {
	@Id
	private Integer classRoomId;
	private String classRoomName;
	private Integer capacity;
	private Character availability;
	
	//setters
	public Integer getClassRoomId() {
		return classRoomId;
	}
	public void setClassRoomId(Integer classRoomId) {
		this.classRoomId = classRoomId;
	}
	public String getClassRoomName() {
		return classRoomName;
	}
	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Character getAvailability() {
		return availability;
	}
	public void setAvailability(Character availability) {
		this.availability = availability;
	}

}
