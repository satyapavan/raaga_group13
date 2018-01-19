package com.raaga.bean;

import java.util.Date;


import com.raaga.exceptions.DuplicateEmailIdException;
import com.raaga.to.CustomerRegistrationTO;
import com.raaga.to.LoginTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class CustomerRegistrationMB {

	private Integer customerRegistrationId;
	private String firstName;
	private String lastName;
	private Long contactNumber;
	private String address;
	private String emailID;
	private String occupation;
	private String company;
	private Date dateOfBirth;
	private Character gender;
	private String reasonForRejection;
	private Character registrationStatus;
	private String message;
	private String userId;
	private String password;


	public String registerCustomer()
	{	
		this.message=null;
		Date dateOfRegistration=new Date();

		CustomerRegistrationTO customerTO =new CustomerRegistrationTO();
		customerTO.setFirstName(firstName);
		customerTO.setLastName(lastName);
		customerTO.setContactNumber(contactNumber);
		customerTO.setAddress(address);
		customerTO.setEmailId(emailID);
		customerTO.setOccupation(occupation);
		customerTO.setCompany(company);
		customerTO.setDateOfBirth(dateOfBirth);
		customerTO.setGender(gender);
		customerTO.setDateOfRegistration(dateOfRegistration);


		try{	
			this.message=null;
			RaagaWrapper raagaWrapper=new RaagaWrapper();
			this.customerRegistrationId=raagaWrapper.registerCustomer(customerTO);
			this.message="Your registration have been completed.Your registration Id is "+this.customerRegistrationId;
			return "success";
		}
		catch(DuplicateEmailIdException e)
		{
			ErrorLogger.logError(this.getClass().getName(),
					"registerCustomer", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";


		}
		catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),
					"registerCustomer", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}

	}
	public String viewRegistrationStatus() {
		try {
			this.message=null;

			Character c = new RaagaWrapper().viewRegistrationStatus(this.customerRegistrationId);
			this.registrationStatus = c;
			this.userId=null;
			this.password=null;
			return "success";

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "viewRegistrationStatus",
					e.getMessage());

			this.setMessage("Customer Registration Id is invalid");
			return "failure";
		}
	}

	public String getLoginDetails() {
		try {
			this.message=null;
			Integer traineeId = new RaagaWrapper()
			.getTraineeId(this.customerRegistrationId);
			LoginTO loginTO = new RaagaWrapper().getCredentials(traineeId);
			this.userId = loginTO.getUserId();
			this.password = loginTO.getPassword();
			return "success";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getLoginDetails",
					e.getMessage());

			return "failure";
		}

	}


	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getCustomerRegistrationId() {
		return customerRegistrationId;
	}
	public void setCustomerRegistrationId(Integer customerRegistrationId) {
		this.customerRegistrationId = customerRegistrationId;
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
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public String getReasonForRejection() {
		return reasonForRejection;
	}
	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}
	public Character getRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(Character registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}











}
