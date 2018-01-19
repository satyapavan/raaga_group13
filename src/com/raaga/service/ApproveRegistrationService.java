package com.raaga.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.raaga.entity.CustomerRegistrationEntity;
import com.raaga.entity.TraineeEntity;
import com.raaga.to.TraineeTO;
import com.raaga.utilities.ErrorLogger;

public class ApproveRegistrationService {
	public Integer approveRegistration(TraineeTO traineeTO) throws Exception{
		EntityManager em = null;
		try {
			
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			TraineeEntity entity=new TraineeEntity();
			entity.setRegistrationId(traineeTO.getRegistrationId());
			entity.setTraineeName(traineeTO.getTraineeName());
			entity.setDateOfJoining(traineeTO.getDateOfJoining());
			
			em.merge(entity);
			em.getTransaction().commit();
			em.getTransaction().begin();
			CustomerRegistrationEntity centit=em.find(CustomerRegistrationEntity.class,traineeTO.getRegistrationId());
			centit.setRegistrationStatus('A');
			em.merge(centit);
			em.getTransaction().commit();
			return entity.getTraineeId();
		} catch (Exception e) {
			
			ErrorLogger.logError(this.getClass().getName(),
					"approveRegistration", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
		
	}
	public void rejectCustomerApplication(Integer customerRegistrationId,String reasonForRejection) throws Exception{
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			CustomerRegistrationEntity entity=em.find(CustomerRegistrationEntity.class,customerRegistrationId);
			entity.setReasonForRejection(reasonForRejection);
			entity.setRegistrationStatus('R');
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			
			ErrorLogger.logError(this.getClass().getName(),
					"rejectCustomerApplication", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
		
	}

}
