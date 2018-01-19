package com.raaga.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.BatchEntity;
import com.raaga.entity.InstructorAllocationEntity;
import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.utilities.ErrorLogger;

public class RejectAllocationService {



	@SuppressWarnings("unchecked")
	public Boolean rejectAllocation(Integer batchId) throws Exception
	{
		Date d=new Date();
		EntityManager em =null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
		em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		InstructorAllocationEntity iae=new InstructorAllocationEntity();

		try {
			et.begin();
			BatchEntity b=em.find(BatchEntity.class,batchId);


			if(b==null)
			{
				throw new NoDetailsAvailableException();
			}
			else
			{

				if(b.getStartDate().after(d))
				{


					Query q1=em.createQuery("select f.instructorAllocationId from InstructorAllocationEntity f where f.batchId=?1");
					q1.setParameter(1,batchId);
					List<Integer> result=q1.getResultList();
					for(int i=0;i<result.size();i++)
					{
						Integer iae1=(Integer)result.get(i);

						iae=em.find(InstructorAllocationEntity.class,iae1);

						iae.setAcceptanceStatus('R');

					}
					em.merge(iae);
					et.commit();
					return true;
				}
				else 
					return false;

			}
		}


		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "rejectAllocation", e.getMessage());
			throw e;
		}
		finally {
			if (em != null) {
				em.close();
			}
		}



	}

	@SuppressWarnings("unchecked")
	public List<Integer> viewAllocatedBatches(String loggedInUser) throws Exception
	{



		List<Integer> batchList =new ArrayList<Integer>();
		EntityManager em =null;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();

			Query q=em.createQuery("select i.batchId from InstructorAllocationEntity i where i.instructorId =(select j.instructorId from LoginEntity j where j.userId=?1)");

			q.setParameter(1,loggedInUser);

			List<Integer> batch =new ArrayList<Integer>();
			batch=q.getResultList();



			for(int i=0;i<batch.size();i++)
			{
				Integer batchId=batch.get(i);

				batchList.add(batchId);

			}


		}

		catch(Exception e)
		{	
			ErrorLogger.logError(this.getClass().getName(), "viewAllocatedBatches", e.getMessage());
			throw e;
		}
		finally {
			if (em != null) {
				em.close();
			}
		}

		return batchList;


	}


}
