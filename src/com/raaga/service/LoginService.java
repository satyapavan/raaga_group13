package com.raaga.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.LoginEntity;
import com.raaga.exceptions.InvalidUsernameException;
import com.raaga.exceptions.WrongPasswordException;
import com.raaga.to.LoginTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.exceptions.InvalidCredentialsException;

public class LoginService {

	@SuppressWarnings("unchecked")
	public Character validateLogin(LoginTO loginTO) throws Exception
	{
		EntityManager em=null;
		int flag1=0;
		int flag2=0;
		int flag3=0;
		String userId=loginTO.getUserId();
		String password=loginTO.getPassword();
		
		try
		{	
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
		em=emf.createEntityManager();
		Query query=em.createQuery("select k from LoginEntity k ");
		
		
		List<LoginEntity> result=query.getResultList();
		
		
		for(LoginEntity login:result)
		{
			if(userId.equalsIgnoreCase(login.getUserId()))
			{
			flag1++;	
			}
			if(password.equalsIgnoreCase(login.getPassword()))
			{
			flag2++;	
			}
			
			if(userId.equalsIgnoreCase(login.getUserId()) && password.equalsIgnoreCase(login.getPassword()))
			{
			flag3++;	
			}
				
		}
		if(flag1==0)
		{
			throw new InvalidUsernameException();
		}
		else if(flag2==0)
		{
			throw new WrongPasswordException();
		}
		else if(flag3==0)
		{
			throw new InvalidCredentialsException();
		}
		LoginEntity login=em.find(LoginEntity.class,loginTO.getUserId());
		return login.getRole();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "validateLogin", e.getMessage());
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
	public String addNewLogin(LoginTO loginTO) throws Exception
	{	EntityManager em=null;
	try
	{	
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
		em=emf.createEntityManager();
		em.getTransaction().begin();
		LoginEntity loginEntity=new LoginEntity();
		loginEntity.setUserId(loginTO.getUserId());
		loginEntity.setPassword(loginTO.getPassword());
		loginEntity.setInstructorId(loginTO.getInstructorId());
		loginEntity.setTraineeId(loginTO.getTraineeId());
		loginEntity.setRole(loginTO.getRole());
		em.persist(loginEntity);
		em.getTransaction().commit();
		return loginEntity.getUserId();
	}
	catch(Exception e)
	
	{	
		ErrorLogger.logError(this.getClass().getName(), "addNewLogin", e.getMessage());
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
	public Integer getTraineeId(String userName) throws Exception {
		EntityManager em = null;

		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Query q=em.createQuery("select l.traineeId from LoginEntity l where l.userId=?1");
			q.setParameter(1,userName);
			Integer traineeId=(Integer) q.getSingleResult();
			return traineeId;

		} catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(), "getTraineeId", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}

	}
	
	public Integer getInstructorId(String userName) throws Exception {
		EntityManager em = null;

		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			Query q=em.createQuery("select l.instructorId from LoginEntity l where l.userId=?1");
			q.setParameter(1,userName);
			Integer instructorId=(Integer) q.getSingleResult();
			return instructorId;

		} catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(), "getInstructorId", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}
	public LoginTO getCredentials(Integer traineeId) throws Exception {
		EntityManager em = null;
		try {
			
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("select a from LoginEntity a where a.traineeId=?1");
			query.setParameter(1,traineeId);
			LoginEntity entity=(LoginEntity) query.getSingleResult();
			LoginTO to=new LoginTO();
			to.setUserId(entity.getUserId());
			to.setPassword(entity.getPassword());
			to.setInstructorId(entity.getInstructorId());
			to.setTraineeId(entity.getInstructorId());
			to.setRole(entity.getRole());
			
			return to;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCredentials", e
					.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}