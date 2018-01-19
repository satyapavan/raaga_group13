package com.raaga.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.to.CustomerRegistrationTO;
import com.raaga.to.TraineeTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class TraineeMB {

	private Integer traineeId;
	private String traineeName;
	private Date dateOfJoining;
	private Integer registrationId;
	private String message;
	private String firstName;
	private String lastName;
	private Long contactNumber;
	private String address;
	private String emailId;
	private String occupation;
	private String company;
	private Date dateOfBirth;
	private Character gender;
	private Date dateOfRegistration;
	private Character role;
	private String loggedInUser;
	private Integer customerRegistrationId;
	private Character registrationStatus;
	private List<SelectItem> traineeList;
	public Integer getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}
	public String getTraineeName() {
		return traineeName;
	}
	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Integer getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(Integer registrationId) {
		this.registrationId = registrationId;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public Character getRole() {
		return role;
	}
	public void setRole(Character role) {
		this.role = role;
	}
	public String getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	public Integer getCustomerRegistrationId() {
		return customerRegistrationId;
	}
	public void setCustomerRegistrationId(Integer customerRegistrationId) {
		this.customerRegistrationId = customerRegistrationId;
	}
	public Character getRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(Character registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	public List<SelectItem> getTraineeList() {
		return traineeList;
	}
	public void setTraineeList(List<SelectItem> traineeList) {
		this.traineeList = traineeList;
	}

	public TraineeMB() {
		List<TraineeTO> list=new ArrayList<TraineeTO>();
		traineeList=new ArrayList<SelectItem>();
		FacesContext ctx = FacesContext.getCurrentInstance(); 
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(false);

		String userName=(String) session.getAttribute("userId");
		Character role=(Character)session.getAttribute("role");

		
		try{
			Integer traineeId=new RaagaWrapper().getTraineeId(userName);
			list=new RaagaWrapper().getAllTrainee();
			for(TraineeTO t:list){
				traineeList.add(new SelectItem(t.getTraineeId(),t.getTraineeName()));
			}
			if(role=='T'){
			
			TraineeTO traineeTO=new TraineeTO();
			traineeTO=new RaagaWrapper().getTraineeDetails(traineeId);
			CustomerRegistrationTO customerRegistrationTO=new CustomerRegistrationTO();
			customerRegistrationTO=new RaagaWrapper().getCustomerDetail(traineeTO.getRegistrationId());
			if(traineeId!=null){
				this.traineeName=traineeTO.getTraineeName();
				this.gender=customerRegistrationTO.getGender();
				this.dateOfJoining=traineeTO.getDateOfJoining();
				this.emailId=customerRegistrationTO.getEmailId();
				this.dateOfBirth=customerRegistrationTO.getDateOfBirth();
				this.address=customerRegistrationTO.getAddress();
				this.contactNumber=customerRegistrationTO.getContactNumber();
				
			}
			}
		}


		catch (NoDetailsAvailableException e) {
			ErrorLogger.logError(this.getClass().getName(), "TraineeMB", e.getMessage());
			this.message=e.getMessage();
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "TraineeMB", e.getMessage());
			this.message=e.getMessage();
		}
	}
	public void getTraineeDetail(ValueChangeEvent vce)
	{
		
		
		CustomerRegistrationTO cust=new CustomerRegistrationTO();
		this.traineeId=(Integer)vce.getNewValue();
		
		TraineeTO t =new TraineeTO();
		try {
			t=new RaagaWrapper().getTraineeDetails(this.traineeId);
			
			cust=new RaagaWrapper().getCustomerDetail(t.getRegistrationId());
			
			this.firstName=cust.getFirstName();
			this.lastName=cust.getLastName();
			this.dateOfBirth=cust.getDateOfBirth();
			this.dateOfJoining=t.getDateOfJoining();
			this.address=cust.getAddress();
			this.company=cust.getCompany();
			this.contactNumber=cust.getContactNumber();
			this.emailId=cust.getEmailId();
			this.customerRegistrationId=t.getRegistrationId();
			this.gender=cust.getGender();
			this.occupation=cust.getOccupation();
			this.registrationStatus=cust.getRegistrationStatus();
			this.traineeName=t.getTraineeName();
			this.dateOfRegistration=cust.getDateOfRegistration();
		} catch (Exception e) {
			e.printStackTrace();
			ErrorLogger.logError(this.getClass().getName(),
                    "getTraineeDetail", e .getMessage());

		}

	}

	
	public String editTrainee()
	{
		CustomerRegistrationTO cust=new CustomerRegistrationTO();
		try
		{	
			cust.setAddress(address);
			cust.setCompany(company);
			cust.setContactNumber(contactNumber);
			
			cust.setCustomerRegistrationId(customerRegistrationId);
			
			cust.setDateOfBirth(dateOfBirth);
			cust.setDateOfRegistration(dateOfRegistration);
			cust.setEmailId(emailId);
			cust.setFirstName(firstName);
			cust.setGender(gender);
			cust.setLastName(lastName);
			cust.setOccupation(occupation);
			cust.setRegistrationStatus(registrationStatus);
			
			new RaagaWrapper().saveCustomer(cust);
			this.setMessage("Data saved successfully");
			return "success";
		}
		catch (Exception e) {
			
			ErrorLogger.logError(this.getClass().getName(),
                    "editTrainee", e .getMessage());

			this.setMessage(e.getMessage());
			return "failure";
		}

	}
}
