package com.raaga.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.raaga.to.BatchTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class ConfirmBatchClosureMB {
	private int batchId;
	private List<SelectItem> batchIdList;
	private List<BatchTO> batchList;
	private String message;
	private Integer courseId;
	private Date startDate;
	private Date batchEndDate;
	private Date batchClosureDate;
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public List<SelectItem> getBatchIdList() {
		return batchIdList;
	}
	public void setBatchIdList(List<SelectItem> batchIdList) {
		this.batchIdList = batchIdList;
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
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getBatchEndDate() {
		return batchEndDate;
	}
	public void setBatchEndDate(Date batchEndDate) {
		this.batchEndDate = batchEndDate;
	}
	public Date getBatchClosureDate() {
		return batchClosureDate;
	}
	public void setBatchClosureDate(Date batchClosureDate) {
		this.batchClosureDate = batchClosureDate;
	}
	public ConfirmBatchClosureMB()
	{	this.message=null;
	batchIdList=new ArrayList<SelectItem>();
	batchList=new ArrayList<BatchTO>();
	try
	{
		batchList=new RaagaWrapper().getBatchesForClosure();
		for(BatchTO b:batchList)
		{
			this.batchIdList.add(new SelectItem(b.getBatchId()));
		}

	}
	catch (Exception e) {

		ErrorLogger.logError(this.getClass().getName(),
				"ConfirmBatchClosureMB", e .getMessage());

		this.message=e.getMessage();
	}

	}
	public void getBatchDetails(ValueChangeEvent vce)
	{	
		this.message=null;
		this.batchId=(Integer)vce.getNewValue();
		for (BatchTO batchto:batchList) {
			if(batchto.getBatchId()==this.batchId)
			{
				this.batchClosureDate=batchto.getBatchClosureDate();
				this.startDate=batchto.getStartDate();
				this.batchEndDate=batchto.getBatchEndDate();
				this.courseId=batchto.getCourseId();

			}

		}


	}
	public String confirmBatchClosure()
	{
		try
		{this.message=null;
		BatchTO bto=new BatchTO();
		bto.setBatchId(batchId);
		bto.setBatchClosureDate(batchClosureDate);
		bto.setBatchEndDate(batchEndDate);
		bto.setStartDate(startDate);
		bto.setCourseId(courseId);
		new RaagaWrapper().confirmBatchClosure(bto);
		this.setMessage("Batch closed and trainees added for certification successfully");
		return "success";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),
					"confirmBatchClosure", e .getMessage());

			this.setMessage(e.getMessage());
			return "failure";
		}
	}
}
