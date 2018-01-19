package com.raaga.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.TraineeEntity;
import com.raaga.to.TraineeTO;
import com.raaga.utilities.ErrorLogger;

public class TraineeService {

@SuppressWarnings("unchecked")
	
	public List<TraineeTO> getAllTrainee() throws Exception {
		List<TraineeEntity> l=new ArrayList<TraineeEntity>();
		List<TraineeTO> list=new ArrayList<TraineeTO>();
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query q=em.createQuery("select t from TraineeEntity t");
			l=q.getResultList();
			for(TraineeEntity te:l){
				TraineeTO to=new TraineeTO();
				to.setDateOfJoining(te.getDateOfJoining());
				to.setRegistrationId(te.getRegistrationId());
				to.setTraineeId(te.getTraineeId());
				to.setTraineeName(te.getTraineeName());
				list.add(to);
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getAllTrainee", e
					.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
		return list;
	}
 
	
	public void saveTrainee(TraineeTO traineeTO) throws Exception {

		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			TraineeEntity te=new TraineeEntity();
			te.setDateOfJoining(traineeTO.getDateOfJoining());
			te.setRegistrationId(traineeTO.getRegistrationId());
			te.setTraineeId(traineeTO.getTraineeId());
			te.setTraineeName(traineeTO.getTraineeName());
			em.merge(te);
			em.getTransaction().commit();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "saveTrainee", e
					.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}

	}
	public TraineeTO getTraineeDetails(Integer traineeId) throws Exception {

		EntityManager em = null;
		TraineeTO traineeTO=new TraineeTO();
		TraineeEntity traineeEntity=new TraineeEntity();
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			traineeEntity=em.find(TraineeEntity.class, traineeId);

			traineeTO.setDateOfJoining(traineeEntity.getDateOfJoining());
			traineeTO.setRegistrationId(traineeEntity.getRegistrationId());
			traineeTO.setTraineeId(traineeEntity.getTraineeId());
			traineeTO.setTraineeName(traineeEntity.getTraineeName());

			return traineeTO;

		} catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(), "getTraineeDetails", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}
	public Integer getTraineeId(Integer customerRegistrationId) throws Exception {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("select a from TraineeEntity a where a.registrationId=?1");
			query.setParameter(1,customerRegistrationId);
			TraineeEntity entity=(TraineeEntity) query.getSingleResult();
			return entity.getTraineeId();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getTraineeId", e.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
	}
	}


