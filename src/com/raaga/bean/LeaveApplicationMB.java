package com.raaga.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;

import com.raaga.exceptions.NoInstructorsToApproveOrRejectException;
import com.raaga.to.InstructorCalendarTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;


public class LeaveApplicationMB {
	private String message;
	private List<InstructorCalendarTO> instructorList;
	private HtmlDataTable hTable;
	private Boolean flag;


	public  LeaveApplicationMB()
	{	
		try
		{	
			this.message=null;
			this.instructorList=new ArrayList<InstructorCalendarTO>();
			this.instructorList=new RaagaWrapper().fetchLeaves();



		}
		catch(NoInstructorsToApproveOrRejectException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "LeaveApplicationMB", e.getMessage());
			this.message=e.getMessage();
		}
		catch(Exception e)
		{	ErrorLogger.logError(this.getClass().getName(), "LeaveApplicationMB", e.getMessage());
		this.message=e.getMessage();

		}
	}



	public String approveLeave(){
		try
		{	
			this.message=null;
			List<InstructorCalendarTO> list1=new ArrayList<InstructorCalendarTO>();

			for(InstructorCalendarTO instructorCalendarTO :this.instructorList)
			{	
				if(instructorCalendarTO.getFlag()==true){

					new RaagaWrapper().approveLeave(instructorCalendarTO.getCalendarId());

				}
				else
				{
					list1.add(instructorCalendarTO);

				}
			}
			this.instructorList=list1;
			if(this.instructorList.isEmpty())
			{
				this.message="No leave applications available!!!";	
			}
			this.message="Leaves are successfully approved";
			return "success";


		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "approveLeave", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
	}


	public String rejectLeave(){

		try
		{	
			this.message=null;
			List<InstructorCalendarTO> list1=new ArrayList<InstructorCalendarTO>();
			for(InstructorCalendarTO instructorCalendarTO :this.instructorList)
			{

				if(instructorCalendarTO.getFlag()==true){

					new RaagaWrapper().rejectLeave(instructorCalendarTO.getCalendarId());

				}
				else
				{
					list1.add(instructorCalendarTO);

				}
			}
			this.instructorList=list1;
			this.setMessage("Leaves are successfully rejected");
			return "success";


		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "rejectLeave", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<InstructorCalendarTO> getInstructorList() {
		return instructorList;
	}
	public void setInstructorList(List<InstructorCalendarTO> instructorList) {
		this.instructorList = instructorList;
	}
	public HtmlDataTable getHTable() {
		return hTable;
	}
	public void setHTable(HtmlDataTable table) {
		hTable = table;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
