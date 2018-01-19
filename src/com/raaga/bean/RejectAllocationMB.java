package com.raaga.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.service.RejectAllocationService;
import com.raaga.utilities.ErrorLogger;

public class RejectAllocationMB {
	private String message;
	private Integer batchId;
	private List<SelectItem> batchIdList;



	public RejectAllocationMB() {


		List<Integer> batchList =new ArrayList<Integer>();
		try{
			this.message=null;
			this.batchIdList=new ArrayList<SelectItem>();
			String loggedInUser=null;

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpSession session = (HttpSession)externalContext.getSession(true);
			loggedInUser=(String) session.getAttribute("userId");


			RejectAllocationService rejectAllocation=new RejectAllocationService();

			batchList=rejectAllocation.viewAllocatedBatches(loggedInUser);

			if(batchList.isEmpty())
			{
				this.message="No Records Found";
				throw new NoDetailsAvailableException();

			}
			else
			{
				for(int i=0;i<batchList.size();i++)
				{

					this.batchIdList.add(new SelectItem(batchList.get(i)));

				}
			}

		}
		catch(NoDetailsAvailableException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "RejectAllocationMB", e.getMessage());
			this.message=e.getMessage();

		}

		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "RejectAllocationMB", e.getMessage());
			this.message=e.getMessage();
		}

	}

	public String rejectAllocation()
	{	
		this.message=null;
		RejectAllocationService service=new RejectAllocationService();

		try{

			Boolean b=service.rejectAllocation(batchId);

			if(b==true)
			{
				this.setMessage("Batch Rejected Succesfully");
				return "success";
			}
			else 
			{
				this.setMessage("Batch Already started cannot be rejected");
				return "failure";
			}

		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "rejectAllocation", e.getMessage());
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
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	public List<SelectItem> getBatchIdList() {
		return batchIdList;
	}
	public void setBatchIdList(List<SelectItem> batchIdList) {
		this.batchIdList = batchIdList;
	}


}
