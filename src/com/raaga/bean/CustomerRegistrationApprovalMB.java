package com.raaga.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.raaga.exceptions.InvalidCustomerIdException;
import com.raaga.exceptions.NoPendingCustomerRegistrationException;
import com.raaga.to.CustomerRegistrationTO;
import com.raaga.to.LoginTO;
import com.raaga.to.TraineeTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class CustomerRegistrationApprovalMB {
	private Integer customerRegistrationId;
	private String message;
	private String firstName;
	private String lastName;
	private Date dateOfRegistration;
	private List<SelectItem> pendingCustomers=new ArrayList<SelectItem>();
	private String reasonForRejection;
	private String emailID;
	private Character gender;
	private Date dateOfBirth;
	private String address;
	private String occupation;
	private String company;
	private Long contactNo;
	public Integer getCustomerRegistrationId() {
		return customerRegistrationId;
	}
	public void setCustomerRegistrationId(Integer customerRegistrationId) {
		this.customerRegistrationId = customerRegistrationId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public List<SelectItem> getPendingCustomers() {
		return pendingCustomers;
	}
	public void setPendingCustomers(List<SelectItem> pendingCustomers) {
		this.pendingCustomers = pendingCustomers;
	}
	public String getReasonForRejection() {
		return reasonForRejection;
	}
	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	public CustomerRegistrationApprovalMB(){
		try {
			this.message=null;
			List<CustomerRegistrationTO> list=new ArrayList<CustomerRegistrationTO>();
			RaagaWrapper wrap=new RaagaWrapper();
			list=wrap.getPendingCustomers();
			for(CustomerRegistrationTO customerRegTO: list){
				this.pendingCustomers.add(new SelectItem(customerRegTO.getCustomerRegistrationId(),customerRegTO.getFirstName()+" "+customerRegTO.getLastName()));
			}
		}
		catch (NoPendingCustomerRegistrationException e) 
		{	ErrorLogger.logError(this.getClass().getName(),
				"CustomerRegistrationApprovalMB", e.getMessage());
		this.message=e.getMessage();
		}
		catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),
					"CustomerRegistrationApprovalMB", e.getMessage());
			this.message=e.getMessage();
		}

	}
	public void showPendingCustomerDetail(ValueChangeEvent vce){
		this.message=null;
		this.customerRegistrationId=(Integer)vce.getNewValue();
		try {
			CustomerRegistrationTO cust=new RaagaWrapper().getCustomerDetail(this.customerRegistrationId);
				this.address=cust.getAddress();
				this.company=cust.getCompany();
				this.contactNo=cust.getContactNumber();
				this.customerRegistrationId=cust.getCustomerRegistrationId();
				this.dateOfBirth=cust.getDateOfBirth();
				this.dateOfRegistration=cust.getDateOfRegistration();
				this.emailID=cust.getEmailId();
				this.firstName=cust.getFirstName();
				this.gender=cust.getGender();
				this.lastName=cust.getLastName();
				this.occupation=cust.getOccupation();
			
			
			
		}
		catch (InvalidCustomerIdException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"showPendingCustomerDetail", e.getMessage());
			this.setMessage(e.getMessage());
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"showPendingCustomerDetail", e.getMessage());
			this.setMessage(e.getMessage());
		}


	}
	public String approveRegistration(){
		this.message=null;
		TraineeTO traineeTO=new TraineeTO();
		traineeTO.setRegistrationId(this.customerRegistrationId);
		traineeTO.setTraineeName(this.firstName+this.lastName);
		traineeTO.setDateOfJoining(this.dateOfRegistration);
		
		try {
			
		Integer traineeno=new RaagaWrapper().approveRegistration(traineeTO);
		
		traineeTO.setTraineeId(traineeno);
			

			String name=this.firstName + "_"+traineeno;
			String password=this.firstName+"@"+traineeno;
			LoginTO loginTO=new LoginTO();
			loginTO.setUserId(name);
			loginTO.setTraineeId(traineeno);
			loginTO.setRole('T');
			loginTO.setPassword(password);
			String user=new RaagaWrapper().addNewLogin(loginTO);
			
			this.message="Trainee Added with TraineeID :"+traineeTO.getTraineeId()+" and UserId :"+user;
			return "success";

		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"approveRegistration", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}


	}
	public String rejectCustomerApplication(){
		try {
			this.message=null;
			if(this.reasonForRejection.length()==0){
				this.setMessage("Reason for rejection is mandatory");
			}
			else{
			new RaagaWrapper().rejectCustomerApplication(this.customerRegistrationId,this.reasonForRejection);
			List<CustomerRegistrationTO> list=new ArrayList<CustomerRegistrationTO>();
			list=new RaagaWrapper().getPendingCustomers();
			for(CustomerRegistrationTO cust1: list){
				this.pendingCustomers.add(new SelectItem(cust1.getCustomerRegistrationId(),cust1.getFirstName()));
			}
			this.message="Application of the customer with registration Id :" +this.customerRegistrationId+" has been rejected" ;
			
		}
		}
		catch (NoPendingCustomerRegistrationException e) 
		{	ErrorLogger.logError(this.getClass().getName(),
				"rejectCustomerApplication", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"rejectCustomerApplication", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
		return "success";

	}

}
