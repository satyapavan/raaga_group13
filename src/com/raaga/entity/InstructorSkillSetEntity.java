package com.raaga.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="RAAGA_INSTRUCTORSKILLSET")
public class InstructorSkillSetEntity {
	@Id
	private Integer skillId;
	private Integer instructorId;
	
	//setters
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public Integer getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}

}
