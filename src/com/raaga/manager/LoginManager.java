package com.raaga.manager;

import java.util.ArrayList;
import java.util.List;

import com.raaga.exceptions.InvalidUsernameException;
import com.raaga.exceptions.NoInstructorsToApproveOrRejectException;
import com.raaga.exceptions.WrongPasswordException;
import com.raaga.service.LeaveApplicationService;
import com.raaga.service.LoginService;
import com.raaga.to.InstructorCalendarTO;
import com.raaga.to.LoginTO;
import com.raaga.utilities.ErrorLogger;
public class LoginManager {


	public Character validateLogin(LoginTO loginTO) throws InvalidUsernameException,WrongPasswordException,Exception
	{
		try
		{
			Character role=null;
			role=new LoginService().validateLogin(loginTO);

			return role;
		}
		catch(InvalidUsernameException e1)
		{	ErrorLogger.logError(this.getClass().getName(), "validateLogin", e1.getMessage());
		throw e1;
		}
		catch(WrongPasswordException e2)
		{ErrorLogger.logError(this.getClass().getName(), "validateLogin", e2.getMessage());
		throw e2;
		}
		catch(Exception e)
		{ErrorLogger.logError(this.getClass().getName(), "validateLogin", e.getMessage());
		throw e;
		}

	}
	public List<InstructorCalendarTO> fetchLeaves() throws NoInstructorsToApproveOrRejectException,Exception{

		List<InstructorCalendarTO>list=new ArrayList<InstructorCalendarTO>();

		try {
			list=new LeaveApplicationService().fetchLeaves();
			if(list.isEmpty()){
				throw new NoInstructorsToApproveOrRejectException();
			}
		} 
		catch(NoInstructorsToApproveOrRejectException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "fetchLeaves", e.getMessage());
			throw e;
		}
		
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "fetchLeaves", e.getMessage());
			throw e;

		}

		return list;
	}
	public String addNewLogin(LoginTO loginTO) throws Exception
	{
		try
		{	
			String userId=new LoginService().addNewLogin(loginTO);
			return userId; 
		}
		catch(Exception e)
		{	ErrorLogger.logError(this.getClass().getName(), "addNewLogin",e.getMessage());
		throw e;
		}
	}
	public Integer getTraineeId(String loggedInUser) throws Exception
	{
		try

		{
			Integer id=new LoginService().getTraineeId(loggedInUser);
			return id;
		}
		catch(Exception e)
		{	ErrorLogger.logError(this.getClass().getName(), "getTraineeId",e.getMessage());
		throw e;
		}
	}

	public Integer getInstructorId(String loggedInUser)throws Exception
	{
		try

		{

			Integer id=new LoginService().getInstructorId(loggedInUser);

			return id;
		}
		catch(Exception e)
		{	ErrorLogger.logError(this.getClass().getName(), "getInstructorId", e.getMessage());
		throw e;
		}	
	}

}