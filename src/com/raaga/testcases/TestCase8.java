package com.raaga.testcases;



import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.raaga.exceptions.InvalidBatchStartDateException;
import com.raaga.exceptions.InvalidCustomerIdException;
import com.raaga.exceptions.NoBatchesAvailableException;
import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.manager.BatchDetailsManager;
import com.raaga.manager.CourseApplicationManager;
import com.raaga.manager.CustomerRegistrationManager;
import com.raaga.to.BatchTO;
import com.raaga.to.CourseApplicationTO;
import com.raaga.to.CustomerRegistrationTO;

public class TestCase8 {

	@Test
	public void testviewBatchIdDetails() throws NoBatchesAvailableException,Exception{
		List<BatchTO> list=new BatchDetailsManager().viewBatchIdDetails(2001);
		assertTrue(list.size()!=0);
	}
	
	@Test
	public void  getCustomerDetails() throws InvalidCustomerIdException,Exception{
		
		Integer customerRegistrationId=3001;
		CustomerRegistrationTO cust=new CustomerRegistrationManager().getCustomerDetails(customerRegistrationId);
		assertTrue(cust!=null);
		
		
	}

	@SuppressWarnings("deprecation")
	@Test
	public void editBatch() throws InvalidBatchStartDateException,Exception{
		Integer batchId=2002;
		Integer  classRoomId=9011;
		Date batchStartDate=new Date("2-AUG-2012");
		new BatchDetailsManager().editBatch(batchId,classRoomId,batchStartDate);
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void editBatchInvalidBatchStartDateException() throws InvalidBatchStartDateException,Exception{
		Integer batchId=2001;
		Integer  classRoomId=9010;
		Date batchStartDate=new Date("15-AUG-2012");
		new BatchDetailsManager().editBatch(batchId,classRoomId,batchStartDate);
	}
	@Test
	public void testCheckValidData() throws NoDetailsAvailableException,Exception 
	{
		
		List<CourseApplicationTO> listOfCourses=new CourseApplicationManager().viewApplicationStatus("Gouri_20005");
		assertTrue(listOfCourses.size()!=0);
	}

	

	
		
}
