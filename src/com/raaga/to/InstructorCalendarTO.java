package com.raaga.to;

import java.util.Date;

public class InstructorCalendarTO {
private Integer calendarId;
private Integer instructorId;
private Date blockedFrom;
private Date blockedTo;
private String reason;
private Character approvalStatus;
private Boolean flag;
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
public Character getApprovalStatus() {
	return approvalStatus;
}
public void setApprovalStatus(Character approvalStatus) {
	this.approvalStatus = approvalStatus;
}
public Boolean getFlag() {
	return flag;
}
public void setFlag(Boolean flag) {
	this.flag = flag;
}



}
