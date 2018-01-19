package com.raaga.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.CourseApplicationEntity;
import com.raaga.entity.CourseDetailsEntity;
import com.raaga.entity.SkillSetEntity;
import com.raaga.to.CourseApplicationTO;
import com.raaga.to.CourseDetailsTO;
import com.raaga.to.SkillSetTO;
import com.raaga.utilities.ErrorLogger;


public class CourseDetailsService {
	

	@SuppressWarnings("unchecked")
	public List<CourseDetailsTO> getCourseDetails(String courseName) throws Exception
	{
		List<CourseDetailsTO> l=new ArrayList<CourseDetailsTO>();



		EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		try{


			Query q=em.createQuery("select i from CourseDetailsEntity i where i.courseName=?1");
			q.setParameter(1,courseName);

			List<CourseDetailsEntity> list=q.getResultList();
			if(list.isEmpty())
			{
				return null;

			}

			else
			{

				for(int i=0;i<list.size();i++)
				{
					CourseDetailsTO c=new CourseDetailsTO();
					c.setCertification(list.get(i).getCertification());
					c.setCourseId(list.get(i).getCourseId());
					c.setCourseLevel(list.get(i).getCourseLevel());
					c.setCourseName(list.get(i).getCourseName());
					c.setCourseType(list.get(i).getCourseType());
					c.setDuration(list.get(i).getDuration());
					c.setFee(list.get(i).getFee());
					l.add(c);

				}
			}
		}

		catch(Exception e)
		{

			throw e;

		}
		return l;
	}

	
	
	@SuppressWarnings("unchecked")
	public Set<String> getAllCourseNames() throws Exception
	{

		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query q=em.createQuery("select c.courseName from CourseDetailsEntity c");
			List<String> l=new ArrayList<String>();
			l=q.getResultList();
			Set<String> s=new HashSet<String>();
			for(String s1:l){
			s.add(s1);
			}
			em.getTransaction().commit();
			return s;
		} catch (Exception e) {
			
		
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}

 @SuppressWarnings("unchecked")
public Boolean checkCourseForAdding(String courseName) throws Exception {


		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("select c from CourseDetailsEntity c where c.courseName=?1");
			query.setParameter(1,courseName);
			List<CourseDetailsEntity> l=query.getResultList();

			if(l.isEmpty()){
				return true;
			}
			else{
				return false;
			}

		} catch (Exception e) {

			 ErrorLogger.logError(this.getClass().getName(), "checkCourseForAdding",
						e.getMessage());

			throw e;
		} finally {
			if (em != null)
				em.close();
		}

	}
	
	public Integer addCourseDetails(CourseDetailsTO detailsTO) throws Exception {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			CourseDetailsEntity c=new CourseDetailsEntity();
			c.setCertification(detailsTO.getCertification());
			c.setCourseLevel(detailsTO.getCourseLevel());
			c.setCourseName(detailsTO.getCourseName());
			c.setCourseType(detailsTO.getCourseType());
			c.setDuration(detailsTO.getDuration());
			c.setFee(detailsTO.getFee());
			em.persist(c);
			em.getTransaction().commit();
			Integer id=c.getCourseId();
			return id;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addCourseDetails",
					e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	
		
		
	}
	@SuppressWarnings("unchecked")
	public CourseDetailsTO getCourseDetailsForEditing(String courseName,
			  String courseLevel) throws Exception {
				 EntityManager em = null;
				 CourseDetailsTO to=null;
				try {
					EntityManagerFactory emf = Persistence
							.createEntityManagerFactory("proj");
					em = emf.createEntityManager();
					em.getTransaction().begin();
					Query query=em.createQuery("select c from CourseDetailsEntity c where c.courseName=?1 and c.courseLevel=?2");
					query.setParameter(1, courseName);
					query.setParameter(2,courseLevel);
					List<CourseDetailsEntity> ls=query.getResultList();
					if(ls.size()==0)
					{
						return to;
					}
					else
					{
						to=new CourseDetailsTO();
						CourseDetailsEntity ce=ls.get(0);
						to.setCourseId(ce.getCourseId());
						to.setCertification(ce.getCertification());
						to.setCourseLevel(ce.getCourseLevel());
						to.setCourseName(ce.getCourseName());
						to.setCourseType(ce.getCourseType());
						to.setDuration(ce.getDuration());
						to.setFee(ce.getFee());
						return to;
					}
					

				
				} catch (Exception e) {
					 ErrorLogger.logError(this.getClass().getName(), "getCourseDetailsForEditing",
								e.getMessage());
					
					throw e;
				} finally {
					if (em != null)
						em.close();
				}
	}

	
	
	@SuppressWarnings("unchecked")
	public List<CourseDetailsTO> getAllCourse() throws Exception
	{
		EntityManager em=null;
		 try{
		 em=Persistence.createEntityManagerFactory("proj").createEntityManager();
		 em.getTransaction().begin();
		 Query q=em.createQuery("select k from CourseDetailsEntity k ");
		 List<CourseDetailsEntity> cDet=q.getResultList();
		 List<CourseDetailsTO> cCourseDetailTO=new ArrayList<CourseDetailsTO>();
		 for (CourseDetailsEntity courseDetailsEntity : cDet) {
		  CourseDetailsTO cDetails=new CourseDetailsTO();
		  cDetails.setCertification(courseDetailsEntity.getCertification());
		  cDetails.setCourseId(courseDetailsEntity.getCourseId());
		  cDetails.setCourseLevel(courseDetailsEntity.getCourseLevel());
		  cDetails.setCourseName(courseDetailsEntity.getCourseName());
		        cDetails.setCourseType(courseDetailsEntity.getCourseType());
		        cDetails.setDuration(courseDetailsEntity.getDuration());
		        cDetails.setFee(courseDetailsEntity.getFee());
		        cCourseDetailTO.add(cDetails);
		 }
		 
		 return cCourseDetailTO;
		 }
		 catch (Exception e) {
			 ErrorLogger.logError(this.getClass().getName(), "getAllCourse", e
						.getMessage());
				throw e;
			    
		 }
		 finally{
		  if(em!=null){
		   em.close();
		  }
		 }
		
		
	}

	
	public CourseDetailsTO getCourseDetail(Integer courseId) throws Exception
	{

		 EntityManager em=null;
		 try{
		 em=Persistence.createEntityManagerFactory("proj").createEntityManager();
		 em.getTransaction().begin();
		 CourseDetailsEntity courseDetailsEntity=em.find(CourseDetailsEntity.class, courseId);
		 CourseDetailsTO cDetails=new CourseDetailsTO();
		 cDetails.setCertification(courseDetailsEntity.getCertification());
		 cDetails.setCourseId(courseDetailsEntity.getCourseId());
		 cDetails.setCourseLevel(courseDetailsEntity.getCourseLevel());
		 cDetails.setCourseName(courseDetailsEntity.getCourseName());
		 cDetails.setCourseType(courseDetailsEntity.getCourseType());
		 cDetails.setDuration(courseDetailsEntity.getDuration());
		 cDetails.setFee(courseDetailsEntity.getFee());
		 return cDetails;
		 }
		 catch (Exception e) {
			 ErrorLogger.logError(this.getClass().getName(), "getCourseDetail", e
						.getMessage());
		  throw e;
		 }
		 finally{
		  if(em!=null){
		   em.close();
		  }
		 } 
		 
	}
	
	
	@SuppressWarnings("unchecked")
	public CourseDetailsTO getCourseId(String name) throws Exception
	{
		 EntityManager em=null;
		 try{
		 em=Persistence.createEntityManagerFactory("proj").createEntityManager();
		 em.getTransaction().begin();
		 CourseDetailsEntity courseDetailsEntity=new CourseDetailsEntity();
		 Query q=em.createQuery("select ce from CourseDetailsEntity ce where ce.courseName=?1");
		 q.setParameter(1, name);
		 List<CourseDetailsEntity> l=new  ArrayList<CourseDetailsEntity>();
		 l=q.getResultList();
		 courseDetailsEntity=l.get(0);
		 CourseDetailsTO cDetails=new CourseDetailsTO();
		 cDetails.setCertification(courseDetailsEntity.getCertification());
		 cDetails.setCourseId(courseDetailsEntity.getCourseId());
		 cDetails.setCourseLevel(courseDetailsEntity.getCourseLevel());
		 cDetails.setCourseName(courseDetailsEntity.getCourseName());
		 cDetails.setCourseType(courseDetailsEntity.getCourseType());
		 cDetails.setDuration(courseDetailsEntity.getDuration());
		 cDetails.setFee(courseDetailsEntity.getFee());
		 return cDetails;
		 }
		 catch (Exception e) {
			 ErrorLogger.logError(this.getClass().getName(), "getCourseId", e
						.getMessage());
		  throw e;
	}
	}	
	@SuppressWarnings("unchecked")
	public SkillSetTO getSkill(String courseName) throws Exception
	{
		EntityManager em=null;
		 try{
		 em=Persistence.createEntityManagerFactory("proj").createEntityManager();
		 em.getTransaction().begin();
		 Query q=em.createQuery("Select s from SkillSetEntity s,InstructorSkillSetEntity i,BatchEntity b,CourseDetailEntity cd where cd.courseName=?1");
		 q.setParameter(1, courseName);
		 List<SkillSetEntity> setEntity=q.getResultList();
		 SkillSetTO skillSetTO=new SkillSetTO();
		 for (SkillSetEntity skillSetEntity : setEntity) {
		  skillSetTO.setSkillId(skillSetEntity.getSkillId());
		  skillSetTO.setSkillName(skillSetEntity.getSkillName());
		  skillSetTO.setSkillDescription(skillSetEntity.getSkillDescription());
		 }
		 return skillSetTO;
		 }
		 catch (Exception e) {
			 ErrorLogger.logError(this.getClass().getName(), "getSkill", e
						.getMessage());
		  throw e;
		 }
		 finally{
		  if(em!=null){
		   em.close();
		  }
		 }  
	}
	
	
	public String getSkillName(Integer skill) throws Exception
	{
		EntityManager em=null;
		 try{
		 em=Persistence.createEntityManagerFactory("proj").createEntityManager();
		 em.getTransaction().begin();
		 SkillSetEntity skillSetEntity=em.find(SkillSetEntity.class, skill);
		 return skillSetEntity.getSkillName();
		 }
		 catch (Exception e) {
			 ErrorLogger.logError(this.getClass().getName(), "getSkillName", e
						.getMessage());
		  throw e;
		 }
		 finally{
		  if(em!=null){
		   em.close();
		  }
		 } 
	}
	

	@SuppressWarnings("unchecked")
	public List<CourseDetailsTO> getCourseDetailsByInstructor(
			Integer instructorId) throws Exception {
		EntityManager em = null;
		List<CourseDetailsTO> listreturn=new ArrayList<CourseDetailsTO>();

		List<Integer> list3=new ArrayList<Integer>();
		CourseDetailsTO courseDetailsTO=new CourseDetailsTO();
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			Query query=(Query) em.createQuery("select b.courseId from BatchEntity b where b.instructorId=?1");
			query.setParameter(1, instructorId);
			list3=query.getResultList();

			for(int i=0;i<list3.size();i++){
				CourseDetailsEntity courseDetailsEntity=em.find(CourseDetailsEntity.class, list3.get(i));
				courseDetailsTO.setCertification(courseDetailsEntity.getCertification());
				courseDetailsTO.setCourseId(courseDetailsEntity.getCourseId());
				courseDetailsTO.setCourseLevel(courseDetailsEntity.getCourseLevel());
				courseDetailsTO.setCourseName(courseDetailsEntity.getCourseName());
				courseDetailsTO.setCourseType(courseDetailsEntity.getCourseType());
				courseDetailsTO.setDuration(courseDetailsEntity.getDuration());
				courseDetailsTO.setFee(courseDetailsEntity.getFee());
				listreturn.add(courseDetailsTO);

			}
			return listreturn;

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseDetailsByInstructor", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}
	public void editCourse(CourseDetailsTO detailsTO) throws Exception {
		
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			CourseDetailsEntity courseDetailsEntity=new CourseDetailsEntity();
			courseDetailsEntity.setCertification(detailsTO.getCertification());
			courseDetailsEntity.setCourseId(detailsTO.getCourseId());
			courseDetailsEntity.setCourseLevel(detailsTO.getCourseLevel());
			courseDetailsEntity.setCourseName(detailsTO.getCourseName());
			courseDetailsEntity.setCourseType(detailsTO.getCourseType());
			courseDetailsEntity.setDuration(detailsTO.getDuration());
			courseDetailsEntity.setFee(detailsTO.getFee());
			em.merge(courseDetailsEntity);
			em.getTransaction().commit();
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"editCourse", e.getMessage());
			throw e;
		}
		finally {
			if (em != null)
				em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public Set<String> getSelectedCourseLevels(String courseName) throws Exception {
		
		EntityManager em = null;
		List<String> list=new ArrayList<String>();
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("select l.courseLevel from CourseDetailsEntity l where l.courseName=?1");
			query.setParameter(1,courseName);
			list=query.getResultList();
			Iterator<String> iti=list.iterator();
			Set<String> s=new TreeSet<String>();
			while(iti.hasNext()){
				s.add(iti.next());
			}
			
		return s;
	}
		catch (Exception e) {
			
			ErrorLogger.logError(this.getClass().getName(), "getSelectedCourseLevels", e.getMessage());
			throw e;
		}
		 finally {
			if (em != null)
				em.close();
		}

	}

	
		
	public Integer applyForCourse(CourseApplicationTO courseApplicationTO)
	throws Exception 
	{
		EntityManager em = null;

		try
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			CourseApplicationEntity CATo = new CourseApplicationEntity();
			CATo.setApplicationStatus(courseApplicationTO.getApplicationStatus());
			CATo.setCourseId(courseApplicationTO.getCourseId());
			CATo.setDateOfApplication(courseApplicationTO.getDateOfApplication());
			CATo.setFeePaid(courseApplicationTO.getFeePaid());
			CATo.setTraineeId(courseApplicationTO.getTraineeId());
			em.persist(CATo);
			em.getTransaction().commit();
			return CATo.getApplicationId();
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "applyForCourse", e.getMessage());
			throw e;
		}
		finally {
			if (em != null)
				em.close();
		}
	}
	public Boolean checkCourseForEditing(Integer courseId) throws Exception
	{
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("select c.courseName from CourseDetailsEntity c where c.courseId=?1");
			query.setParameter(1, courseId);
			if(query.getResultList().isEmpty())
			{
				return false;
			}
			
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"checkCourseForEditing", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public CourseDetailsTO getCoursesDetailsForEditing(String courseName,
			String courseLevel) throws Exception {
		
		EntityManager em = null;
		CourseDetailsTO courseDetailsTO=null;
		Integer cid=0;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			
			
			Query query=em.createQuery("select l from CourseDetailsEntity l where l.courseName=?1 and l.courseLevel=?2");
			query.setParameter(1,courseName);
			query.setParameter(2,courseLevel);
			
			List<CourseDetailsEntity> l1=query.getResultList();
			
			for (CourseDetailsEntity courseDetailsEntity : l1) {
				
			
			courseDetailsTO=new CourseDetailsTO();
			courseDetailsTO.setCourseId(courseDetailsEntity.getCourseId());
			courseDetailsTO.setCourseName(courseDetailsEntity.getCourseName());
			courseDetailsTO.setCourseLevel(courseDetailsEntity.getCourseLevel());
			courseDetailsTO.setCourseType(courseDetailsEntity.getCourseType());
			courseDetailsTO.setCertification(courseDetailsEntity.getCertification());
			courseDetailsTO.setDuration(courseDetailsEntity.getDuration());
			courseDetailsTO.setFee(courseDetailsEntity.getFee());
			
		
			cid=courseDetailsEntity.getCourseId();
			}
			
			Query q=em.createQuery("select c.batchClosureStatus from BatchEntity c where c.courseId=?1");
			q.setParameter(1,cid);
			
			List<Character> l=q.getResultList();
			
            for(Character c:l){
            	if(!(c=='C')){
            		return null;
    			}
    			
    			
    			
            }
          
            return courseDetailsTO;		
			
			
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCoursesDetailsForEditing", e.getMessage());
			throw e;
		}
		
		finally {
			if (em != null)
				em.close();
		}
	}






}
