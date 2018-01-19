package com.raaga.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.CertificationEntity;
import com.raaga.entity.CourseDetailsEntity;
import com.raaga.entity.TraineeEntity;
import com.raaga.to.CertificationTO;
import com.raaga.utilities.ErrorLogger;

public class CertificationService {
	@SuppressWarnings("unchecked")
	public List<CertificationTO> getCertificationReport(Date startDate,
			Date endDate) throws Exception {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			List<CertificationTO> list = new ArrayList<CertificationTO>();
			Query query = em
			.createQuery("select a from CertificationEntity a where a.testDate>?1 and a.testDate<=?2");
			query.setParameter(1,startDate);
			query.setParameter(2,endDate);
			List<CertificationEntity> listentity = query.getResultList();
			
			
			for (CertificationEntity entity : listentity) {
				CertificationTO certificationTo = new CertificationTO();
				certificationTo.setCertificationId(entity.getCertificationId());
				certificationTo.setTraineeId(entity.getTraineeId());
				certificationTo.setTestDate(entity.getTestDate());
				
				TraineeEntity entity1 = em.find(TraineeEntity.class, entity
						.getTraineeId());
				certificationTo.setTraineeName(entity1.getTraineeName());
				certificationTo.setDateOfJoining(entity1.getDateOfJoining());

				CourseDetailsEntity entity2 = em.find(
						CourseDetailsEntity.class, entity.getCourseId());
				certificationTo.setCourseName(entity2.getCourseName());
				certificationTo.setCourseLevel(entity2.getCourseLevel());
				certificationTo.setCourseType(entity2.getCourseType());

				list.add(certificationTo);	

			
			}

			em.getTransaction().commit();
			return list;

		} catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),
					"getCertificationReport", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}

	}

	
}
