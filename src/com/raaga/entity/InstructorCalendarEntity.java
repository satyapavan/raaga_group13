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
@Table(name="Raaga_InstructorCalendar")
public class InstructorCalendarEntity {
	@Id
	
	@SequenceGenerator(sequenceName="seq_id",name="id",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id")
	private Integer calendarId;
	private Integer instructorId;
	@Temporal(TemporalType.DATE)
	private Date blockedFrom;
	@Temporal(TemporalType.DATE)
	private Date blockedTo;
	private String reason;
	private Character blockType;
	private Character approvalStatus;
	public Integer getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}
	public Integer getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}
	public Date getBlockedFrom() {
		return blockedFrom;
	}
	public void setBlockedFrom(Date blockedFrom) {
		this.blockedFrom = blockedFrom;
	}
	public Date getBlockedTo() {
		return blockedTo;
	}
	public void setBlockedTo(Date blockedTo) {
		this.blockedTo = blockedTo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Character getBlockType() {
		return blockType;
	}
	public void setBlockType(Character blockType) {
		this.blockType = blockType;
	}
	public Character getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Character approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	
}
