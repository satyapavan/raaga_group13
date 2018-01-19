package com.raaga.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.raaga.exceptions.NoVenuesAvailableException;
import com.raaga.to.BatchTO;
import com.raaga.to.ClassRoomTO;
import com.raaga.to.CustomerRegistrationTO;
import com.raaga.to.TraineeTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class BatchDetailsMB {
	private Character batchType;
	private List<BatchTO> batchList;
	private String message;
	private Date batchStartDate;
	private Integer classRoomId;
	private List<SelectItem> classRoomList;
	private List<SelectItem> batchIdList;
	private Integer batchId;

	public BatchDetailsMB(){

		RaagaWrapper wrapper=new RaagaWrapper();
		List<Integer> list=new ArrayList<Integer>();
		List<ClassRoomTO> list2=new ArrayList<ClassRoomTO>();

		try{
			this.message=null;
			this.batchIdList=new ArrayList<SelectItem>();
			this.classRoomList=new ArrayList<SelectItem>();

			list=wrapper.getBatchIdDetails();
			for(int i=0;i<list.size();i++){
				this.batchIdList.add(new SelectItem(list.get(i)));

			}
			list2=wrapper.getClassRoomDetails();
			for(int i=0;i<list2.size();i++){
				this.classRoomList.add(new SelectItem(list2.get(i).getClassRoomId(),list2.get(i).getClassRoomName()));

			}

		}
		catch(NoVenuesAvailableException e){
			ErrorLogger.logError(this.getClass().getName(), "getClassRoomDetails", e.getMessage());
			this.message=e.getMessage();
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "BatchDetailsMB", e
					.getMessage());
			this.message=e.getMessage();
		}

	}
	public void getBatchDetails(ValueChangeEvent event)
	{
		this.message=null;
		Character c=(Character)event.getNewValue();
		this.batchList = new ArrayList<BatchTO>();

		try
		{			
			batchList=new RaagaWrapper().viewBatchDetails(c);


		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getBatchDetails", e
					.getMessage());
			this.setMessage(e.getMessage());
		}
	}
	public void getBatchIdDetails(ValueChangeEvent event){
		this.message=null;
		RaagaWrapper wrap=new RaagaWrapper();

		try{
			batchId=Integer.parseInt(event.getNewValue().toString());
			this.batchList=wrap.viewBatchIdDetails(batchId);

			for(int i=0;i<batchList.size();i++){

				BatchTO batch=(BatchTO) this.batchList.get(i);
				this.batchType=batch.getBatchType();

			}

		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getBatchDetails", e
					.getMessage());
			this.setMessage(e.getMessage());
		}
	}

	public String editBatch() {
		this.message=null;
		RaagaWrapper wrap=new RaagaWrapper();

		try{
			wrap.editBatch(this.batchId,this.classRoomId,this.batchStartDate);

			this.setMessage("Batch edited Successfully");



			FacesContext c = FacesContext.getCurrentInstance(); 
			ExternalContext e = c.getExternalContext();
			HttpSession session = (HttpSession)e.getSession(true);


			List<String>UserIds=new ArrayList<String>();
			List<TraineeTO> list=new RaagaWrapper().getAllTrainee();
			String to="";
			for(int i=0;i<list.size();i++){
				Integer a=list.get(i).getRegistrationId();
				CustomerRegistrationTO cust=new RaagaWrapper().getCustomerDetail(a);
				String s=cust.getEmailId();
				UserIds.add(s);
				to=to+s+";";
			}
			session.setAttribute("to",to);
			String msg="Batch  "+this.batchId+ "Scheduled to "+this.batchStartDate+ 
			"at ClassRoom  "+ this.classRoomId;
			session.setAttribute("msg",msg);



			return "success";
		}

		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "editDetails", e
					.getMessage());
			this.setMessage(e.getMessage());

			return "failure";
		}
	}

	public Character getBatchType() {
		return batchType;
	}
	public void setBatchType(Character batchType) {
		this.batchType = batchType;
	}
	public List<BatchTO> getBatchList() {
		return batchList;
	}
	public void setBatchList(List<BatchTO> batchList) {
		this.batchList = batchList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getBatchStartDate() {
		return batchStartDate;
	}
	public void setBatchStartDate(Date batchStartDate) {
		this.batchStartDate = batchStartDate;
	}
	public Integer getClassRoomId() {
		return classRoomId;
	}
	public void setClassRoomId(Integer classRoomId) {
		this.classRoomId = classRoomId;
	}
	public List<SelectItem> getClassRoomList() {
		return classRoomList;
	}
	public void setClassRoomList(List<SelectItem> classRoomList) {
		this.classRoomList = classRoomList;
	}
	public List<SelectItem> getBatchIdList() {
		return batchIdList;
	}
	public void setBatchIdList(List<SelectItem> batchIdList) {
		this.batchIdList = batchIdList;
	}
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

}
