package com.raaga.testcases;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.raaga.exceptions.InvalidEndDateException;
import com.raaga.exceptions.NoCertificationsException;
import com.raaga.manager.CertificationManager;

public class TestCase3 
{
	@Test(expected=InvalidEndDateException.class)
	public void getCertificationReportInvalidEndDate() throws NoCertificationsException, InvalidEndDateException, Exception
	{

		String string1="19-MAR-2012";
		SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
		Date startDate=format.parse(string1);
		String string2="19-MAR-2012";
		Date endDate=format.parse(string2);
		new CertificationManager().getCertificationReport(startDate, endDate);
		Assert.assertTrue(endDate.before(startDate));
	}
	
	@Test (expected=NoCertificationsException.class)
	public void testCertificationReportValidity()throws NoCertificationsException,InvalidEndDateException,Exception
	{
		String string1="11-Sep-2013";
		SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
		Date startDate=format.parse(string1);
		String string2="13-Sep-2013";
		Date endDate=format.parse(string2);
		
		new CertificationManager().getCertificationReport(startDate, endDate);
	}
	
	@Test(expected=InvalidEndDateException.class)
	public void testGetCertificationReport() throws InvalidEndDateException, NoCertificationsException, Exception {


		String date1="11-AUG-2012";
		SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
		Date startDate=format.parse(date1);
		String date2="09-AUG-2012";
		Date endDate=format.parse(date2);
		new CertificationManager().getCertificationReport(startDate, endDate);
		
	}
	
}
