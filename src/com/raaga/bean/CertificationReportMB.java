package com.raaga.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.raaga.exceptions.InvalidEndDateException;
import com.raaga.exceptions.NoCertificationsException;
import com.raaga.to.CertificationTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class CertificationReportMB {
	private Date startDate;
	private Date endDate;
	private List<CertificationTO> certificationList;
	private String message;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<CertificationTO> getCertificationList() {
		return certificationList;
	}
	public void setCertificationList(List<CertificationTO> certificationList) {
		this.certificationList = certificationList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCertificationReport(){
		try {
			this.certificationList=new ArrayList<CertificationTO>();
			this.message=null;
			this.certificationList=new RaagaWrapper().getCertificationReport(this.startDate, this.endDate);
			return "success";
		} catch (InvalidEndDateException e) {
			ErrorLogger.logError(this.getClass().getName(), "getCertificationReport",
					e.getMessage());
			this.setMessage(e.getMessage());

			return "failure";
		} catch (NoCertificationsException e) {
			ErrorLogger.logError(this.getClass().getName(), "getCertificationReport",
					e.getMessage());
			this.setMessage(e.getMessage());

			return "failure";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getCertificationReport", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
	}

}
