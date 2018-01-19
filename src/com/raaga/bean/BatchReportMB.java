package com.raaga.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.to.BatchTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class BatchReportMB {
	private Character reportType;
	private Integer month;
	private Integer year;
	private List<BatchTO> batchList;
	private List<SelectItem> months;
	private List<SelectItem> years;
	private Integer totalBatchNo;
	private Long totalBatchStrength;
	private String message;

	public void getSelectedType(ValueChangeEvent event){
		this.message=null;
		this.reportType=(Character) event.getNewValue();

		try
		{

			this.months=new ArrayList<SelectItem>();
			this.years=new ArrayList<SelectItem>();
			Set<Integer> set=new TreeSet<Integer>();
			set=new RaagaWrapper().getBatchYears();
			for(Integer integer:set)
			{
				this.years.add(new SelectItem(integer));
			}



			if(this.reportType=='M')
			{
				this.months.add(new SelectItem(1,"Jan"));
				this.months.add(new SelectItem(2,"Feb"));
				this.months.add(new SelectItem(3,"Mar"));
				this.months.add(new SelectItem(4,"Apr"));
				this.months.add(new SelectItem(5,"May"));
				this.months.add(new SelectItem(6,"Jun"));
				this.months.add(new SelectItem(7,"Jul"));
				this.months.add(new SelectItem(8,"Aug"));
				this.months.add(new SelectItem(9,"Sep"));
				this.months.add(new SelectItem(10,"Oct"));
				this.months.add(new SelectItem(11,"Nov"));
				this.months.add(new SelectItem(12,"Dec"));
			}

		}

		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "getSelectedType", e
					.getMessage());
			this.setMessage(e.getMessage());


		}
	}
	public String getBatchDetails()
	{	this.message=null;
	this.totalBatchNo=0;
	this.totalBatchStrength=0L;
	try
	{
		if(this.reportType=='Y')
		{
			this.month=null;
			this.batchList=new ArrayList<BatchTO>();
			this.batchList=new RaagaWrapper().getBatchReport(this.month,this.year);
			this.totalBatchNo=this.batchList.size();

			for(BatchTO batchTO:this.batchList)
			{

				this.totalBatchNo=this.totalBatchNo+1;
				this.totalBatchStrength=this.totalBatchStrength+batchTO.getBatchStrength();

			}
			if(this.batchList.isEmpty())
			{
				this.setMessage("No Details in Selected Type");
			}
		}
		else if(this.reportType=='M')
		{	
			this.batchList=new ArrayList<BatchTO>();

			this.batchList=new RaagaWrapper().getBatchReport(this.month,this.year);

			for(BatchTO batchTO:this.batchList)
			{

				this.totalBatchNo=this.totalBatchNo+1;
				this.totalBatchStrength=this.totalBatchStrength+batchTO.getBatchStrength();

			}

			if(this.batchList.isEmpty())
			{
				this.setMessage("No Details in Selected Type");
			}
		}




		return "success";
	}
	catch (NoDetailsAvailableException e1) {

		ErrorLogger.logError(this.getClass().getName(), "getBatchDetails", e1
				.getMessage());
		this.setMessage(e1.getMessage());

		return "failure";
	}
	catch(Exception e)
	{
		ErrorLogger.logError(this.getClass().getName(), "getBatchDetails", e
				.getMessage());
		this.setMessage(e.getMessage());

		return "failure";

	}
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Character getReportType() {
		return reportType;
	}
	public void setReportType(Character reportType) {
		this.reportType = reportType;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public List<BatchTO> getBatchList() {
		return batchList;
	}
	public void setBatchList(List<BatchTO> batchList) {
		this.batchList = batchList;
	}
	public List<SelectItem> getMonths() {
		return months;
	}
	public void setMonths(List<SelectItem> months) {
		this.months = months;
	}
	public List<SelectItem> getYears() {
		return years;
	}
	public void setYears(List<SelectItem> years) {
		this.years = years;
	}
	public Integer getTotalBatchNo() {
		return totalBatchNo;
	}
	public void setTotalBatchNo(Integer totalBatchNo) {
		this.totalBatchNo = totalBatchNo;
	}
	public Long getTotalBatchStrength() {
		return totalBatchStrength;
	}
	public void setTotalBatchStrength(Long totalBatchStrength) {
		this.totalBatchStrength = totalBatchStrength;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
