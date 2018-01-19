package com.raaga.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.CustomerRegistrationEntity;
import com.raaga.to.CustomerRegistrationTO;
import com.raaga.utilities.ErrorLogger;

public class CustomerRegistrationService {


	public Character viewRegistrationStatus(Integer custRegId) throws Exception {
		EntityManager em = null;
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			CustomerRegistrationEntity entity = em.find(CustomerRegistrationEntity.class, custRegId);
			
			
			
			return entity.getRegistrationStatus();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"viewRegistrationStatus", e.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	
	public CustomerRegistrationTO getCustomerDetail(Integer registrationId) throws Exception {
		EntityManager em = null;
		CustomerRegistrationTO customerRegistrationTO=new CustomerRegistrationTO();
		CustomerRegistrationEntity customerRegistrationEntity=new CustomerRegistrationEntity();
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			customerRegistrationEntity=em.find(CustomerRegistrationEntity.class, registrationId);
			customerRegistrationTO.setAddress(customerRegistrationEntity.getAddress());
			
			customerRegistrationTO.setCompany(customerRegistrationEntity.getCompany());
			customerRegistrationTO.setContactNumber(customerRegistrationEntity.getContactNumber());
			customerRegistrationTO.setCustomerRegistrationId(customerRegistrationEntity.getCustomerRegistrationId());
			customerRegistrationTO.setDateOfBirth(customerRegistrationEntity.getDateOfBirth());
			customerRegistrationTO.setDateOfRegistration(customerRegistrationEntity.getDateOfRegistration());
			customerRegistrationTO.setEmailId(customerRegistrationEntity.getEmailId());
			
			customerRegistrationTO.setFirstName(customerRegistrationEntity.getFirstName());
			customerRegistrationTO.setGender(customerRegistrationEntity.getGender());
			customerRegistrationTO.setLastName(customerRegistrationEntity.getLastName());
			customerRegistrationTO.setOccupation(customerRegistrationEntity.getOccupation());
			customerRegistrationTO.setReasonForRejection(customerRegistrationEntity.getReasonForRejection());
			customerRegistrationTO.setRegistrationStatus(customerRegistrationEntity.getRegistrationStatus());
			return customerRegistrationTO;
				
		} catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(), "getCustomerDetail", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
}
@SuppressWarnings("unchecked")	
public boolean checkEmailId(String emailId) throws Exception
{
	
	EntityManager em=null;
	try{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
		em=emf.createEntityManager();
		em.getTransaction().begin();

		
		Query q=em.createQuery("select c from CustomerRegistrationEntity c where c.emailId=?1");
		q.setParameter(1,emailId);
		List<CustomerRegistrationEntity> cde=q.getResultList();
        if(cde.isEmpty())
        {
           return true;
        }
        else
           return false;

	
	}
	
	catch(Exception e)
	{
		ErrorLogger.logError(this.getClass().getName(), "checkEmailId", e.getMessage());
		throw e;
	}
	finally
	{
		if(em!=null)
			em.close();
	}


}

	public Integer registerCustomer(CustomerRegistrationTO customerRegistrationTO) throws Exception
	{

		CustomerRegistrationEntity customer=new CustomerRegistrationEntity();
		
		EntityManager em=null;
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
			em=emf.createEntityManager();
			em.getTransaction().begin();

			
				customer.setFirstName(customerRegistrationTO.getFirstName());
				customer.setLastName(customerRegistrationTO.getLastName());
				customer.setContactNumber(customerRegistrationTO.getContactNumber());
				customer.setAddress(customerRegistrationTO.getAddress());
				customer.setEmailId(customerRegistrationTO.getEmailId());
				customer.setOccupation(customerRegistrationTO.getOccupation());
				customer.setCompany(customerRegistrationTO.getCompany());
				customer.setDateOfBirth(customerRegistrationTO.getDateOfBirth());
				customer.setDateOfRegistration(customerRegistrationTO.getDateOfRegistration());
				customer.setGender(customerRegistrationTO.getGender());
				
				
				
				em.persist(customer);
				em.getTransaction().commit();
			
			return customer.getCustomerRegistrationId();

		}
		
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "registerCustomer",e.getMessage());
			throw e;
		}
		finally
		{
			if(em!=null)
				em.close();
		}



	}
	public void saveCustomer(CustomerRegistrationTO registrationTO) throws Exception {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			Query q=em.createQuery("update CustomerRegistrationEntity c set c.contactNumber=?1,c.dateOfBirth=?2,c.company=?3,c.occupation=?4,c.address=?5 where c.customerRegistrationId=?6");
			q.setParameter(1, registrationTO.getContactNumber());
			q.setParameter(2, registrationTO.getDateOfBirth());
			q.setParameter(3, registrationTO.getCompany());
			q.setParameter(4, registrationTO.getOccupation());
			q.setParameter(5, registrationTO.getAddress());
			q.setParameter(6, registrationTO.getCustomerRegistrationId());
			q.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "saveCustomer",e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CustomerRegistrationTO> getPendingCustomers() throws Exception{
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("select a from CustomerRegistrationEntity a where a.registrationStatus=?1");
			query.setParameter(1,'P');
			List<CustomerRegistrationTO> list=new ArrayList<CustomerRegistrationTO>();
			
			List<CustomerRegistrationEntity> listent=query.getResultList();
			for(CustomerRegistrationEntity entity:listent)
			{
				CustomerRegistrationTO customerRegistrationTO=new CustomerRegistrationTO();
				customerRegistrationTO.setAddress(entity.getAddress());
				customerRegistrationTO.setCompany(entity.getCompany());
				customerRegistrationTO.setContactNumber(entity.getContactNumber());
				customerRegistrationTO.setCustomerRegistrationId(entity.getCustomerRegistrationId());
				customerRegistrationTO.setDateOfBirth(entity.getDateOfBirth());
				customerRegistrationTO.setDateOfRegistration(entity.getDateOfRegistration());
				customerRegistrationTO.setEmailId(entity.getEmailId());
				customerRegistrationTO.setFirstName(entity.getFirstName());
				customerRegistrationTO.setGender(entity.getGender());
				customerRegistrationTO.setLastName(entity.getLastName());
				customerRegistrationTO.setOccupation(entity.getOccupation());
				customerRegistrationTO.setReasonForRejection(entity.getReasonForRejection());
				customerRegistrationTO.setRegistrationStatus(entity.getRegistrationStatus());
				list.add(customerRegistrationTO);
			}
			em.getTransaction().commit();
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getPendingCustomers", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
		
	}
	
	
}
