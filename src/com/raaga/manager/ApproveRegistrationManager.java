package com.raaga.manager;

import com.raaga.service.ApproveRegistrationService;
import com.raaga.to.TraineeTO;
import com.raaga.utilities.ErrorLogger;

public class ApproveRegistrationManager {
	public Integer approveRegistration(TraineeTO traineeTO) throws Exception{
		try {

			Integer traineeId=new ApproveRegistrationService().approveRegistration(traineeTO);
			return traineeId;
		}
		catch (Exception e) 
		{
			ErrorLogger.logError(this.getClass().getName(),
					"approveRegistration", e.getMessage());
			throw e;

		}

	}
	public void rejectCustomerApplication(Integer customerRegistrationId,String reasonForRejection) throws Exception{

		try {
			new ApproveRegistrationService().rejectCustomerApplication(customerRegistrationId, reasonForRejection);

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"rejectCustomerApplication", e.getMessage());
			throw e;
		}
	}


}
