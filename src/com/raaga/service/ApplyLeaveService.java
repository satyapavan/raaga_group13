package com.raaga.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.raaga.entity.InstructorCalendarEntity;
import com.raaga.entity.LoginEntity;
import com.raaga.exceptions.NoInstructorsForSkillException;
import com.raaga.to.InstructorCalendarTO;
import com.raaga.utilities.ErrorLogger;


public class ApplyLeaveService {

	
	public Integer fetchInstructor(String s) throws NoInstructorsForSkillException,Exception
	{
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			LoginEntity login=new LoginEntity();
			login=em.find(LoginEntity.class, s);
			if(login==null)
				throw new NoInstructorsForSkillException();
			
			em.getTransaction().commit();
			return login.getInstructorId();
		} 
		
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "fetchInstructor", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}
	
	public Integer applyLeave(InstructorCalendarTO instTO) throws Exception
	{
		
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			InstructorCalendarEntity ice=new InstructorCalendarEntity();
			ice.setBlockedFrom(instTO.getBlockedFrom());
			ice.setBlockedTo(instTO.getBlockedTo());
			ice.setInstructorId(instTO.getInstructorId());
			
			ice.setReason(instTO.getReason());
			ice.setBlockType('L');
			ice.setApprovalStatus('P');
			em.persist(ice);
			
			em.getTransaction().commit();
			return ice.getCalendarId();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}
	
}
