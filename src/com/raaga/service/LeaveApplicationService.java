package com.raaga.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.InstructorCalendarEntity;
import com.raaga.to.InstructorCalendarTO;
import com.raaga.utilities.ErrorLogger;

public class LeaveApplicationService {


	public void approveLeave(Integer calendarId) throws Exception
	{
		EntityManager em=null;
		try

		{	
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
			em=emf.createEntityManager();
			em.getTransaction().begin();

			Query query=em.createQuery("update InstructorCalendarEntity k set k.approvalStatus='A' where k.calendarId=?1");
			query.setParameter(1, calendarId);
			query.executeUpdate();
			em.getTransaction().commit();


		}
		catch(Exception e)
		{	
			ErrorLogger.logError(this.getClass().getName(), "approveLeave",e.getMessage());
			throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}	
	}

	public void rejectLeave(Integer calendarId) throws Exception
	{
		EntityManager em=null;
		try

		{	
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
			em=emf.createEntityManager();
			em.getTransaction().begin();

			Query query=em.createQuery("update InstructorCalendarEntity k set k.approvalStatus='R' where k.calendarId=?1");	
			query.setParameter(1, calendarId);
			query.executeUpdate();
			em.getTransaction().commit();



		}
		catch(Exception e)
		{ErrorLogger.logError(this.getClass().getName(), "rejectLeave",e.getMessage());
		throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}	

	}
	@SuppressWarnings("unchecked")
	public List<InstructorCalendarTO> fetchLeaves() throws Exception
	{
		EntityManager em=null;


		try
		{	

			List<InstructorCalendarTO> list1=new ArrayList<InstructorCalendarTO>();

			EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
			em=emf.createEntityManager();
			Query query=em.createQuery("select k from InstructorCalendarEntity k where k.blockType='L' and k.approvalStatus='P'");

			List<InstructorCalendarEntity> result=query.getResultList();


			for(InstructorCalendarEntity instructor:result)
			{
				InstructorCalendarTO instructorCalendarTO=new InstructorCalendarTO();
				instructorCalendarTO.setCalendarId(instructor.getCalendarId());
				instructorCalendarTO.setInstructorId(instructor.getInstructorId());
				instructorCalendarTO.setBlockedFrom(instructor.getBlockedFrom());
				instructorCalendarTO.setBlockedTo(instructor.getBlockedTo());
				instructorCalendarTO.setReason(instructor.getReason());
				instructorCalendarTO.setApprovalStatus(instructor.getApprovalStatus());
				list1.add(instructorCalendarTO);
			}



			return list1;
		}

		catch(Exception e)
		{ErrorLogger.logError(this.getClass().getName(), "fetchLeaves",e.getMessage());
		throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}	

	}
}
