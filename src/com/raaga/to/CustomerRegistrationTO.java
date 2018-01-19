package com.raaga.to;

import java.util.Date;

public class CustomerRegistrationTO {

	private Integer customerRegistrationId; 
	
	
	
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
	private String reasonForRejection;
	private Character registrationStatus;
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
	
	
	
}
