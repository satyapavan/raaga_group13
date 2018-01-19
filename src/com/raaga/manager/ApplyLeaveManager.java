package com.raaga.manager;

import com.raaga.exceptions.DateDifferenceException;
import com.raaga.exceptions.LeaveExceededException;
import com.raaga.exceptions.LeaveInAdvanceException;
import com.raaga.service.ApplyLeaveService;
import com.raaga.to.InstructorCalendarTO;
import com.raaga.utilities.ErrorLogger;


public class ApplyLeaveManager {
public Integer applyLeave(InstructorCalendarTO instTO) throws DateDifferenceException,
LeaveExceededException,LeaveInAdvanceException,Exception{
		
		try {
			ApplyLeaveService service=new ApplyLeaveService();
			return service.applyLeave(instTO);
		} catch(DateDifferenceException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			throw e;
		}
		catch(LeaveInAdvanceException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			throw e;
		}
		catch(LeaveExceededException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			throw e;		}

		
	}

public Integer fetchInstructor(String s) throws Exception{
	
	
	ApplyLeaveService service=new ApplyLeaveService();
	
	try {
		return service.fetchInstructor(s);
	} catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(), "fetchInstructor", e
				.getMessage());
		throw e;
	}
}

}
