package com.raaga.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.BatchEntity;
import com.raaga.entity.ClassRoomEntity;
import com.raaga.entity.CourseDetailsEntity;
import com.raaga.entity.InstructorEntity;
import com.raaga.to.BatchTO;
import com.raaga.to.ClassRoomTO;
import com.raaga.utilities.ErrorLogger;

public class BatchDetailsService {


	@SuppressWarnings("unchecked")
	public List<BatchTO> viewBatchDetails(Character batchType) throws Exception{
		EntityManager em = null;
		List<BatchEntity> b=new ArrayList<BatchEntity>();
		List<BatchTO> list=new ArrayList<BatchTO>();
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			Query query=(Query) em.createQuery("select b from BatchEntity b where b.batchType=?1");
			query.setParameter(1, batchType);
			b=query.getResultList();

			for(int i=0;i<b.size();i++){
				BatchEntity batch=b.get(i);
				BatchTO bto=new BatchTO();
				bto.setBatchId(batch.getBatchId());
				bto.setStartDate(batch.getStartDate());
				bto.setBatchStrength(batch.getBatchStrength());
				bto.setVenueId(batch.getVenueId());
				bto.setBatchType(batchType);
				bto.setInstructorId(batch.getInstructorId());
				bto.setBatchEndDate(batch.getBatchEndDate());
				bto.setBatchClosureDate(batch.getBatchClosureDate());
				bto.setBatchClosureStatus(batch.getBatchClosureStatus());

				list.add(bto);
			}
			return list;

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "viewBatchDetails", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<BatchTO> viewBatchIdDetails(Integer batchId) throws Exception {

		EntityManager em = null;
		List<BatchEntity> b=new ArrayList<BatchEntity>();
		List<BatchTO> list=new ArrayList<BatchTO>();
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			Query query=(Query) em.createQuery("select b from BatchEntity b where b.batchId=?1");
			query.setParameter(1,batchId);
			b=query.getResultList();
			for(int i=0;i<b.size();i++){
				BatchEntity batchEntity=b.get(i);
				BatchTO batchTO=new BatchTO();
				batchTO.setBatchClosureDate(batchEntity.getBatchClosureDate());
				batchTO.setBatchClosureStatus(batchEntity.getBatchClosureStatus());
				batchTO.setBatchEndDate(batchEntity.getBatchEndDate());
				batchTO.setBatchId(batchEntity.getBatchId());
				batchTO.setBatchStrength(batchEntity.getBatchStrength());
				batchTO.setBatchType(batchEntity.getBatchType());

				batchTO.setVenueId(batchEntity.getVenueId());
				ClassRoomEntity classRoomEntity=em.find(ClassRoomEntity.class, batchEntity.getVenueId());
				batchTO.setClassRoomName(classRoomEntity.getClassRoomName());

				batchTO.setCourseId(batchEntity.getCourseId());
				CourseDetailsEntity courseDetailsEntity=em.find(CourseDetailsEntity.class, batchEntity.getCourseId());
				batchTO.setCourseLevel(courseDetailsEntity.getCourseLevel());
				batchTO.setCourseName(courseDetailsEntity.getCourseName());

				batchTO.setInstructorId(batchEntity.getInstructorId());
				InstructorEntity instructorEntity=em.find(InstructorEntity.class, batchEntity.getInstructorId());
				batchTO.setInstructorName(instructorEntity.getInstructorName());

				batchTO.setStartDate(batchEntity.getStartDate());

				list.add(batchTO);
			}
			return list;

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "viewBatchIdDetails", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getBatchIdDetails() throws Exception{

		EntityManager em = null;
		List<Integer> list=new ArrayList<Integer>();

		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query q=em.createQuery("select b.batchId from BatchEntity b where b.batchClosureStatus='C'");
			list=q.getResultList();
			
			return list;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getBatchIdDetails", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}

	
	@SuppressWarnings("unchecked")
	public List<ClassRoomTO> getClassRoomDetails() throws Exception{
		EntityManager em = null;
		List<ClassRoomTO> list=new ArrayList<ClassRoomTO>();

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query q=em.createQuery("select c from ClassRoomEntity c where c.availability='Y'");
			List<ClassRoomEntity> list2=q.getResultList();
			
			for(int i=0;i<list2.size();i++){
				ClassRoomEntity classRoomEntity=list2.get(i);
				ClassRoomTO classRoomTO=new ClassRoomTO();
				classRoomTO.setClassRoomId(classRoomEntity.getClassRoomId());
				classRoomTO.setClassRoomName(classRoomEntity.getClassRoomName());
				classRoomTO.setCapacity(classRoomEntity.getCapacity());
				classRoomTO.setAvailability(classRoomEntity.getAvailability());
				list.add(classRoomTO);
			}
			return list;
		}


		catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(), "getClassRoomDetails", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}

	@SuppressWarnings("static-access")
	public void editBatch(Integer batchId,Integer classRoomId,Date startDate) throws Exception{
		EntityManager em = null;

		try {
	
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
	
			Query q1 = em.createQuery("select b.venueId from BatchEntity b where b.batchId=?1");
			q1.setParameter(1, batchId);
			Integer venueId=(Integer)q1.getSingleResult();
		
			
			Query q2 = em.createQuery("select b.batchType from BatchEntity b where b.batchId=?1");
			q2.setParameter(1, batchId);
			Character s=(Character)q2.getSingleResult();
		


			Query q3 = em.createQuery("update BatchEntity b set b.startDate =?1,b.venueId =?2 where b.batchId=?3");
			q3.setParameter(1, startDate);
			q3.setParameter(2, classRoomId);
			q3.setParameter(3, batchId);
			q3.executeUpdate();
		
			Query q4 = em.createQuery("update ClassRoomEntity c set c.availability='N' where c.classRoomId=?1");
			q4.setParameter(1, classRoomId);
			q4.executeUpdate();
		
			Query q5= em.createQuery("update ClassRoomEntity c set c.availability='Y' where c.classRoomId=?1");
			q5.setParameter(1, venueId);
			q5.executeUpdate();
			
			Query q6= em.createQuery("select b.courseId from BatchEntity b where b.batchId=?1");
			q6.setParameter(1, batchId);
			Integer courseId=(Integer)q6.getSingleResult();
			
			Query q7= em.createQuery("select cd.duration from CourseDetailsEntity cd where cd.courseId=?1");
			q7.setParameter(1, courseId);
			Integer duration=(Integer)q7.getSingleResult();

		
			Calendar calend=Calendar.getInstance(); 

			calend.setTime(startDate);
			
			if ( s== 'D') { 
				for (int i = 1; i <duration;) {
					if(calend.get(Calendar.DAY_OF_WEEK)==7){
						calend.add(calend.DATE, 2);
					} 
					else if(calend.get(Calendar.DAY_OF_WEEK)==1){
						calend.add(calend.DATE, 1);
					} 
					else{
						calend.add(calend.DATE,1);
						i++;
					}
				}
			}
			if(s== 'E'){
				for (int i = 1; i <duration;) {
					if(calend.get(Calendar.DAY_OF_WEEK)==7||calend.get(Calendar.DAY_OF_WEEK)==1){
						calend.add(calend.DATE,1);
						i++;
						
					}
					else if(calend.get(Calendar.DAY_OF_WEEK)==2){
						calend.add(calend.DATE,5);

					}
					else if(calend.get(Calendar.DAY_OF_WEEK)==3){
						calend.add(calend.DATE,4);

					}
					else if(calend.get(Calendar.DAY_OF_WEEK)==4){
						calend.add(calend.DATE,3);

					}
					else if(calend.get(Calendar.DAY_OF_WEEK)==5){
						calend.add(calend.DATE,2);

					}
					else if(calend.get(Calendar.DAY_OF_WEEK)==6){
						calend.add(calend.DATE,1);

					}
				}

			}

	
			Date end = calend.getTime();
		
		
			Query q8=em.createQuery("update BatchEntity b set b.batchEndDate=?1 where b.batchId=?2");
			q8.setParameter(1,end);
			q8.setParameter(2,batchId);
			q8.executeUpdate();

			Query q9=em.createQuery("update BatchEntity b set b.batchClosureDate=?1 where b.batchId=?2");
			q9.setParameter(1,end);
			q9.setParameter(2,batchId);
			q9.executeUpdate();


			em.getTransaction().commit();
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "editBatch", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}

	}


}
