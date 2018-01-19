package com.raaga.testcases;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.raaga.exceptions.DuplicateEmailIdException;
import com.raaga.manager.CustomerRegistrationManager;
import com.raaga.to.CustomerRegistrationTO;
public class TestCase2 {
	
	@SuppressWarnings("deprecation")
	@Test(expected=DuplicateEmailIdException.class)
	public void testEmailValidity() throws DuplicateEmailIdException,Exception{
		Date d=new Date("28-JAN-1990");
		Date date=new Date("19-FEB-2013");
		CustomerRegistrationTO customerRegistrationTO=new CustomerRegistrationTO();
		customerRegistrationTO.setFirstName("Karthik");
		customerRegistrationTO.setLastName("Bhat");
		customerRegistrationTO.setContactNumber(794078347l);
		customerRegistrationTO.setAddress("Infosys,Mysore");
		customerRegistrationTO.setEmailId("rabi_bhaskar@gmail.com");
		customerRegistrationTO.setOccupation("Employee");
		customerRegistrationTO.setCompany("Infosys");
		customerRegistrationTO.setDateOfBirth(d);
		customerRegistrationTO.setGender('M');
		customerRegistrationTO.setDateOfRegistration(date);
		customerRegistrationTO.setReasonForRejection(null);
		customerRegistrationTO.setRegistrationStatus('P');
		CustomerRegistrationManager m=new CustomerRegistrationManager(); 
		m.registerCustomer(customerRegistrationTO);
	}

	
	@SuppressWarnings("deprecation")
	@Test()
	public void testCustomerDetails() throws Exception{
		Date d=new Date("28-JAN-1990");
		Date date=new Date("19-FEB-2013");
		CustomerRegistrationTO customerRegistrationTO=new CustomerRegistrationTO();
		customerRegistrationTO.setFirstName("tapash");
		customerRegistrationTO.setLastName("maharana");
		customerRegistrationTO.setContactNumber(794078347l);
		customerRegistrationTO.setAddress("Infosys,Mysore");
		customerRegistrationTO.setEmailId(null);
		customerRegistrationTO.setOccupation("Employee");
		customerRegistrationTO.setCompany("Infosys");
		customerRegistrationTO.setDateOfBirth(d);
		customerRegistrationTO.setGender('F');
		customerRegistrationTO.setDateOfRegistration(date);
		customerRegistrationTO.setReasonForRejection(null);
		customerRegistrationTO.setRegistrationStatus('A');
		CustomerRegistrationManager m=new CustomerRegistrationManager(); 
		Integer customerId=m.registerCustomer(customerRegistrationTO);
		Assert.assertTrue(customerId!=null);

	}
}
