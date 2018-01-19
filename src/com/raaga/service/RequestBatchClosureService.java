package com.raaga.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.BatchEntity;
import com.raaga.exceptions.InvalidBatchClosureDateException;
import com.raaga.exceptions.InvalidClosureRequestException;
import com.raaga.utilities.ErrorLogger;

public class RequestBatchClosureService {
	
	@SuppressWarnings("unchecked")
	public List<Integer> viewAllocatedBatches(String s) throws Exception{
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("select a.instructorId from InstructorEntity a where a.instructorName=?1");
			query.setParameter(1,s);
			Integer name= (Integer) query.getSingleResult();
			Query query1=em.createQuery("select a.batchId from InstructorAllocationEntity a where a.instructorId=?1 and a.acceptanceStatus=?2");
			query1.setParameter(1,name);
			query1.setParameter(2,'A');
			List<Integer> list=new ArrayList<Integer>();
			list=query1.getResultList();
			em.getTransaction().commit();
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"viewAllocatedBatches", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
		
	}
	public Boolean requestClosure(Integer batchId,Date batchClosureDate) throws InvalidClosureRequestException,InvalidBatchClosureDateException,Exception{
		EntityManager em = null;
		
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			BatchEntity entity=em.find(BatchEntity.class,batchId);
			if(entity.getBatchClosureStatus()=='O'){
				throw new InvalidClosureRequestException();
			}
			if(batchClosureDate.after(entity.getBatchEndDate())||batchClosureDate.equals(entity.getBatchEndDate())){
			entity.setBatchClosureDate(batchClosureDate);
			entity.setBatchClosureStatus('C');
			em.merge(entity);
			}
			else
			{
				throw new InvalidBatchClosureDateException();
			}
			em.getTransaction().commit();
			return true;
			
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "requestClosure", e
					.getMessage());
			throw e;
			
		} finally {
			if (em != null)
				em.close();
		}
		
	}
	

}
