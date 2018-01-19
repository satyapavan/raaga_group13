package com.raaga.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.BatchEntity;
import com.raaga.entity.InstructorEntity;
import com.raaga.entity.InstructorSkillSetEntity;
import com.raaga.to.InstructorSkillSetTO;
import com.raaga.to.InstructorTO;
import com.raaga.utilities.ErrorLogger;

public class InstructorService {





	@SuppressWarnings("unchecked")
	public List<InstructorTO> getInstructorDetails(String skillName)
	throws Exception {
		EntityManager em = null;
		try {

			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			Query query1 = em
			.createQuery("select a.skillId from SkillSetEntity a where a.skillName = ?1");
			query1.setParameter(1, skillName);
			Integer skillId = (Integer) query1.getSingleResult();
			Query query2 = em
			.createQuery("select b from InstructorEntity b where b.instructorId in (select c.instructorId from InstructorSkillSetEntity c where c.skillId=?1)");
			query2.setParameter(1, skillId);
			List<InstructorEntity> list = query2.getResultList();
			List<InstructorTO> list1 = new ArrayList<InstructorTO>();

			for (InstructorEntity iter : list) {
				InstructorTO to = new InstructorTO();
				to.setInstructorId(iter.getInstructorId());
				to.setInstructorName(iter.getInstructorName());
				to.setAddress(iter.getAddress());
				to.setContactNumber(iter.getContactNumber());
				to.setDateOfJoining(iter.getDateOfJoining());
				list1.add(to);
			}

			return list1;
		} catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(),
					"getInstructorDetails", e.getMessage());

			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Boolean checkInstructor(Integer instructorId, Date startDate)
	throws Exception {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			Query query1 = em
			.createQuery("select a from BatchEntity a where a.instructorId = ?1");
			query1.setParameter(1, instructorId);
			List<BatchEntity> list1 = query1.getResultList();
			if (list1.isEmpty()) {
				return true;
			}
			Query query = em
			.createQuery("select a from BatchEntity a where a.instructorId = ?1 and a.batchClosureDate< ?2");
			query.setParameter(1, instructorId);
			query.setParameter(2, startDate);
			List<BatchEntity> list = query.getResultList();
			if (list.isEmpty()) {
				return false;
			}
			return true;

		} catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(),
					"checkInstructor", e.getMessage());

			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}


	public InstructorTO getInstructorDetail(Integer instructorId) throws Exception
	{
		EntityManager em = null;



		try {
			InstructorTO instructorTO=new InstructorTO();
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			InstructorEntity instructorEntity=em.find(InstructorEntity.class, instructorId);

			instructorTO.setInstructorId(instructorEntity.getInstructorId());
			instructorTO.setInstructorName(instructorEntity.getInstructorName());
			instructorTO.setDateOfJoining(instructorEntity.getDateOfJoining());
			instructorTO.setAddress(instructorEntity.getAddress());
			instructorTO.setContactNumber(instructorEntity.getContactNumber());


			return instructorTO;

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getInstructorDetail", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}


	}

	@SuppressWarnings("unchecked")
	public List<InstructorTO> getAllInstructor() throws Exception
	{
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query q=em.createQuery("select i from InstructorEntity i");
			List<InstructorEntity> l=new ArrayList<InstructorEntity>();
			l=q.getResultList();
			List<InstructorTO> lto=new ArrayList<InstructorTO>();

			for(InstructorEntity ie:l)
			{
				InstructorTO ito=new InstructorTO();
				ito.setAddress(ie.getAddress());
				ito.setContactNumber(ie.getContactNumber());
				ito.setDateOfJoining(ie.getDateOfJoining());
				ito.setInstructorId(ie.getInstructorId());
				ito.setInstructorName(ie.getInstructorName());
				lto.add(ito);
			}


			em.getTransaction().commit();
			return lto;
		} catch (Exception e) {

			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}

	public void saveInstructor(InstructorTO instructorTO) throws Exception {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			InstructorEntity ie=new InstructorEntity();
			ie.setAddress(instructorTO.getAddress());
			ie.setContactNumber(instructorTO.getContactNumber());
			ie.setDateOfJoining(instructorTO.getDateOfJoining());
			ie.setInstructorId(instructorTO.getInstructorId());
			ie.setInstructorName(instructorTO.getInstructorName());
			em.merge(ie);
			em.getTransaction().commit();
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "saveInstructor",e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}		
	}

	@SuppressWarnings("unchecked")
	public List<InstructorSkillSetTO>  getSkillSet(Integer instructorId) throws Exception
	{
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			@SuppressWarnings("unused")
			InstructorSkillSetEntity ie=new InstructorSkillSetEntity();
			Query q2=em.createQuery("select sk from InstructorSkillSetEntity  sk where sk.instructorId=?2");
			q2.setParameter(2, instructorId);
			List<InstructorSkillSetEntity> lsk=new ArrayList<InstructorSkillSetEntity>();
			lsk=q2.getResultList();


			List<InstructorSkillSetTO> lsto=new ArrayList<InstructorSkillSetTO>();

			for(InstructorSkillSetEntity ie2:lsk)
			{
				InstructorSkillSetTO ito=new InstructorSkillSetTO();
				ito.setInstructorId(instructorId);
				ito.setSkillId(ie2.getSkillId());
				lsto.add(ito);
			}




			em.getTransaction().commit();
			return lsto;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getSkillSet",e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}
}
