package com.raaga.bean;

import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.raaga.exceptions.DateDifferenceException;
import com.raaga.exceptions.LeaveExceededException;
import com.raaga.exceptions.LeaveInAdvanceException;
import com.raaga.to.InstructorCalendarTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class ApplyLeaveMB {

	public Date blockedFrom;
	public Date blockedTo;
	public String reason;
	public Integer instructorId;
	public String message;
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
	public Integer getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ApplyLeaveMB()
	{


		try {
			this.message=null;
			FacesContext c = FacesContext.getCurrentInstance();
			ExternalContext e = c.getExternalContext();
			HttpSession session = (HttpSession) e.getSession(true);


			String loggedInUser=session.getAttribute("userId").toString();

			RaagaWrapper wrap=new RaagaWrapper();
			this.instructorId=wrap.getInstructorId(loggedInUser);

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "ApplyLeaveMB", e.getMessage());
			this.message=e.getMessage();

		}

	}

	public String applyLeave(){

		try {
			this.message=null;
			Date today=new Date();
			Date blockedFrom=this.blockedFrom;
			Date blockedTo=this.blockedTo;
			long DateDifference1=(blockedTo.getTime()-blockedFrom.getTime())/(1000*60*60*24);
			long DateDifference2=(blockedFrom.getTime()-today.getTime())/(1000*60*60*24);	
			
			
			
			if(blockedTo.before(blockedFrom))
			{
				throw new DateDifferenceException();
			}

			if(DateDifference2<5){
				throw new LeaveInAdvanceException();

			}
			if(DateDifference1>5)
			{
				throw new LeaveExceededException();

			}
			InstructorCalendarTO instTO=new InstructorCalendarTO();
			instTO.setInstructorId(this.instructorId);

			instTO.setBlockedFrom(this.blockedFrom);
			instTO.setBlockedTo(this.blockedTo);
			instTO.setReason(this.reason);
			instTO.setApprovalStatus('P');
			new RaagaWrapper().applyLeave(instTO);

			this.setMessage("Leave application submitted successfully");
			return "success";

		} 
		catch(DateDifferenceException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			this.setMessage(e.getMessage());

			return "failure";
		}
		catch(LeaveInAdvanceException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			this.setMessage(e.getMessage());

			return "failure";
		}
		catch(LeaveExceededException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			this.setMessage(e.getMessage());

			return "failure";
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			this.setMessage(e.getMessage());

			return "failure";
		}


	}
}
