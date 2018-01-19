package com.raaga.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.raaga.exceptions.InvalidEndDateException;
import com.raaga.exceptions.NoCertificationsException;
import com.raaga.service.CertificationService;
import com.raaga.to.CertificationTO;
import com.raaga.utilities.ErrorLogger;

public class CertificationManager {

	public List<CertificationTO> getCertificationReport(Date startDate,Date endDate) throws InvalidEndDateException,NoCertificationsException,Exception{
		try {
			if(!(startDate.before(endDate))){
				throw new InvalidEndDateException();
			}
			List<CertificationTO> list=new ArrayList<CertificationTO>();
			list=new CertificationService().getCertificationReport(startDate, endDate);
			if(list.size()==0){
				throw new NoCertificationsException();
			}
				return list;
			
		}
		catch (InvalidEndDateException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getCertificationReport", e.getMessage());
			throw e;
			
		}
		catch (NoCertificationsException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getCertificationReport", e.getMessage());
			
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getCertificationReport", e.getMessage());
			throw e;
		}
		
		
	}
}
