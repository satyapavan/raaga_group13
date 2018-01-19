
package com.raaga.testcases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.raaga.exceptions.InvalidBatchStartDateException;
import com.raaga.exceptions.NoApplicationsSelectedException;
import com.raaga.exceptions.NotAValidBatchException;
import com.raaga.to.BatchTO;
import com.raaga.to.CourseApplicationTO;
import com.raaga.wrapper.RaagaWrapper;

public class TestCase4 {
	@Test(expected = NoApplicationsSelectedException.class)
	public void testNoOfApplications() throws Exception {
		List<CourseApplicationTO> list = new ArrayList<CourseApplicationTO>();
		new RaagaWrapper().approveOrRejectApplication('A', 5014, list);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testInstructorId() throws Exception {
		Date date = new Date();
		date.setDate(23);
		date.setMonth(7);
		date.setYear(2012);
		BatchTO batchTO = new BatchTO();
		batchTO.setCourseName("keyboard");
		batchTO.setCourseLevel("Basic");
		batchTO.setStartDate(date);
		batchTO.setBatchType('D');
		batchTO.setInstructorId(10006);
		batchTO.setVenueId(9011);
		Assert.assertTrue(new RaagaWrapper().scheduleBatch(batchTO)!=null);

	}
	@SuppressWarnings("deprecation")
	@Test(expected = InvalidBatchStartDateException.class)
	public void testStartDate() throws Exception {
		Date date = new Date();
		date.setDate(12);
		date.setMonth(9);
		date.setYear(2010);

		BatchTO batchTO = new BatchTO();
		batchTO.setCourseName("Bass Guitar");
		batchTO.setCourseLevel("Level 1");
		batchTO.setStartDate(date);
		batchTO.setBatchType('E');
		batchTO.setInstructorId(10002);
		batchTO.setVenueId(9001);
		new RaagaWrapper().scheduleBatch(batchTO);

	}

	@SuppressWarnings("deprecation")
	@Test(expected = NotAValidBatchException.class)
	public void testValidBatch() throws Exception {
		Date date = new Date();
		date.setDate(01);
		date.setMonth(8);
		date.setYear(2011);
		List<CourseApplicationTO> list = new ArrayList<CourseApplicationTO>();
		CourseApplicationTO to = new CourseApplicationTO();
		to.setApplicationId(8001);
		to.setApplicationStatus('A');
		to.setChecked(true);
		to.setCourseId(5014);
		to.setDateOfApplication(date);
		to.setFeePaid(1500.0);
		to.setTraineeId(20020);
		to.setTraineeName("Deepak");
		list.add(to);
		new RaagaWrapper().approveOrRejectApplication('C', 2004, list);
	}

	
	
}
