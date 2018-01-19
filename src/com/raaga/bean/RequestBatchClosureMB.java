package com.raaga.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.raaga.exceptions.InvalidBatchClosureDateException;
import com.raaga.exceptions.InvalidClosureRequestException;
import com.raaga.exceptions.NoBatchesAvailableException;
import com.raaga.to.InstructorTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class RequestBatchClosureMB {
	private String message;
	private Integer batchId;
	private List<SelectItem> batchIdList;
	private Date batchClosureDate;
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
	public Date getBatchClosureDate() {
		return batchClosureDate;
	}
	public void setBatchClosureDate(Date batchClosureDate) {
		this.batchClosureDate = batchClosureDate;
	}
	public RequestBatchClosureMB() {
		FacesContext ctx1 = FacesContext.getCurrentInstance(); 
		ExternalContext etx1 = ctx1.getExternalContext();
		HttpSession session = (HttpSession)etx1.getSession(true);
 
		String name=(String)session.getAttribute("userId");
		this.message=null;
		try {
			this.batchIdList=new ArrayList<SelectItem>();
			Integer iId=new RaagaWrapper().getInstructorId(name);

			InstructorTO inst=new RaagaWrapper().getInstructorDetail(iId);

			String instructorName=inst.getInstructorName();

			List<Integer> list=new ArrayList<Integer>();
			list=new RaagaWrapper().viewAllocatedBatches(instructorName);

			if(list==null ){
				throw new NoBatchesAvailableException();
			}
			else{
				for(int i=0;i<list.size();i++){
					this.batchIdList.add(new SelectItem(list.get(i)));

				}	

			}
		}
		catch(NoBatchesAvailableException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "RequestBatchClosureMB", e.getMessage());
			this.message=e.getMessage();
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "RequestBatchClosureMB", e.getMessage());
			this.message=e.getMessage();


		}

	}
	public String requestClosure(){
		try {
			this.message=null;
			Boolean bol=new RaagaWrapper().requestClosure(this.batchId,this.batchClosureDate);
			if(bol==true){
				this.message="Request for closure is sent";
				return "success";
			}
			else{

				this.message="Request Error";
				return "failure";
			}
		} 
		catch (InvalidClosureRequestException e) {
			ErrorLogger.logError(this.getClass().getName(), "requestClosure", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
		catch (InvalidBatchClosureDateException e) {
			ErrorLogger.logError(this.getClass().getName(), "requestClosure", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "requestClosure", e
					.getMessage());
			return "failure";
		}

	}

}
