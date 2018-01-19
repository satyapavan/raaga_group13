package com.raaga.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.entity.BatchEntity;
import com.raaga.entity.BatchTraineeDetailsEntity;
import com.raaga.entity.CertificationEntity;
import com.raaga.entity.ClassRoomEntity;
import com.raaga.entity.CourseDetailsEntity;
import com.raaga.entity.InstructorEntity;
import com.raaga.exceptions.InvalidClosureDateException;
import com.raaga.exceptions.InvalidRecord;
import com.raaga.to.BatchTO;
import com.raaga.utilities.ErrorLogger;

public class BatchScheduleService {

	@SuppressWarnings("unchecked")
	public Set<Integer> getBatchYears() throws Exception {
		
		EntityManager em=null;
		List<Date> list=new ArrayList<Date>();
		Set<Integer> set=new TreeSet<Integer>();
		try {
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
			em=emf.createEntityManager();
			
			Query query=em.createQuery("select k.startDate from BatchEntity k");
			list=query.getResultList();
			
			for(Date date:list){
				SimpleDateFormat s=new SimpleDateFormat("dd-MMM-yyyy");
				String str=s.format(date);
				String[] string=str.split("-");
				set.add(Integer.parseInt(string[2]));
			}

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getBatchYears", e
					.getMessage());
			throw e;
		}
		return set;
	}

	@SuppressWarnings("unchecked")
	public List<BatchTO> getBatchReport(Integer month, Integer year) throws Exception {
		

		EntityManager em=null;
		List<BatchTO> list=new ArrayList<BatchTO>();
		List<BatchEntity> list1=new ArrayList<BatchEntity>();
		List<BatchEntity> list2=new ArrayList<BatchEntity>();
		

		try {
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
			em=emf.createEntityManager();

			Query query=em.createQuery("select k from BatchEntity k");
			list1=query.getResultList();
			for(BatchEntity batchEntity:list1)
			{
				Date d=batchEntity.getStartDate();
				
				
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
				String s=simpleDateFormat.format(d);
				
				String[] string=s.split("-");
				if(month == null)
				{
					if(Integer.parseInt(string[2])==year)
					{
						list2.add(batchEntity);
					}
				}
				else 
				{
					if(Integer.parseInt(string[1])==month && Integer.parseInt(string[2])==year)
					{
						list2.add(batchEntity);
					}
				}
				
				
			}
			
			for(BatchEntity batchEntity:list2)
			{
				BatchTO batchTO=new BatchTO();
				batchTO.setBatchId(batchEntity.getBatchId());
				batchTO.setBatchStrength(batchEntity.getBatchStrength());
				batchTO.setStartDate(batchEntity.getStartDate());
				batchTO.setBatchEndDate(batchEntity.getBatchEndDate());
				batchTO.setBatchClosureDate(batchEntity.getBatchClosureDate());
				batchTO.setBatchClosureStatus(batchEntity.getBatchClosureStatus());
				batchTO.setBatchType(batchEntity.getBatchType());

				
				CourseDetailsEntity cde=new CourseDetailsEntity();
				cde = em.find(CourseDetailsEntity.class, batchEntity.getCourseId());
				batchTO.setCourseName(cde.getCourseName());
				batchTO.setCourseLevel(cde.getCourseLevel());

				
				InstructorEntity instructorEntity=new InstructorEntity();
				instructorEntity=em.find(InstructorEntity.class, batchEntity.getInstructorId());
				batchTO.setInstructorId(instructorEntity.getInstructorId());
				batchTO.setInstructorName(instructorEntity.getInstructorName());

				
				ClassRoomEntity classRoomEntity=new ClassRoomEntity();
				classRoomEntity=em.find(ClassRoomEntity.class,batchEntity.getVenueId());
				batchTO.setClassRoomName(classRoomEntity.getClassRoomName());

				list.add(batchTO);
			}
			
		} catch (Exception e) {
			
			ErrorLogger.logError(this.getClass().getName(), "getBatchReport", e
					.getMessage());
			throw e;
		}
		return list;
	}
	@SuppressWarnings({ "unchecked", "static-access" })
	public Integer scheduleBatch(BatchTO to) throws Exception {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			BatchEntity entity = new BatchEntity();
			Query query = em
			.createQuery("select a from CourseDetailsEntity a where a.courseName=?1 and a.courseLevel=?2");
			query.setParameter(1, to.getCourseName());
			query.setParameter(2, to.getCourseLevel());
			List<CourseDetailsEntity> cEntity = query.getResultList();
			entity.setCourseId(cEntity.get(0).getCourseId());
			int duration = cEntity.get(0).getDuration();
			
			
			Calendar calend=Calendar.getInstance(); 
			calend.setTime(to.getStartDate());
		
			if (to.getBatchType() == 'D') { 
				for (int i = 1; i < duration;) {
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
			if(to.getBatchType()=='E'){
				for (int i = 1; i < duration;) {
					if(calend.get(Calendar.DAY_OF_WEEK)==7||calend.get(Calendar.DAY_OF_WEEK)==1){
						calend.add(calend.DATE,1);
						i++;
					}
					else if(Calendar.DAY_OF_WEEK==2){
						calend.add(calend.DATE,5);
						
					}
					else if(Calendar.DAY_OF_WEEK==3){
						calend.add(calend.DATE,4);
						
					}
					else if(Calendar.DAY_OF_WEEK==4){
						calend.add(calend.DATE,3);
						
					}
					else if(Calendar.DAY_OF_WEEK==5){
						calend.add(calend.DATE,2);
						
					}
					else if(Calendar.DAY_OF_WEEK==6){
						calend.add(calend.DATE,1);
						
					}
				}
				
			}

			
			
			ClassRoomEntity clas=em.find(ClassRoomEntity.class,to.getVenueId());
			clas.setAvailability('N');
			em.merge(clas);
			entity.setVenueId(to.getVenueId());
			entity.setBatchType(to.getBatchType());
			entity.setInstructorId(to.getInstructorId());
			entity.setBatchClosureStatus('O');
			entity.setBatchClosureDate(calend.getTime());
			entity.setBatchEndDate(calend.getTime());

			entity.setBatchStrength(0);
			entity.setStartDate(to.getStartDate());
			em.persist(entity);
			em.getTransaction().commit();
			return entity.getBatchId();

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "scheduleBatch", e
					.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public Map<Integer, String> getFreeVenues() throws Exception {
		EntityManager em = null;
		try {
			
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em
			.createQuery("select a from ClassRoomEntity a where a.availability=?1");
			query.setParameter(1, 'Y');
			List<ClassRoomEntity> list = query.getResultList();
			Map<Integer, String> map = new TreeMap<Integer, String>();
			for (ClassRoomEntity iter : list) {
				map.put(iter.getClassRoomId(), iter.getClassRoomName());
			}
			return map;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getFreeVenues", e
					.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void confirmBatchClosure(BatchTO batchTO) throws Exception
	 {
		 EntityManager em=null;
		 @SuppressWarnings("unused")
		List<BatchTO> list=new ArrayList<BatchTO>();
		 @SuppressWarnings("unused")
		List<BatchEntity> l=new ArrayList<BatchEntity>();
		 try
		 {
			 EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
			 em=emf.createEntityManager();
			 EntityTransaction et=em.getTransaction();
			 et.begin();
			 Date today=new Date();
			
			 Query q=em.createQuery("select p from BatchEntity p where p.batchClosureStatus=?1 and p.batchClosureDate=?2 and p.batchId=?3");
				q.setParameter(1,'C');
				q.setParameter(2,today);
				q.setParameter(3,batchTO.getBatchId());
				
				List<BatchEntity> lb=q.getResultList();
				if(lb.size()!=0)
				{
					BatchEntity be=lb.get(0);
					
					 Query q1=em.createQuery("select b from BatchTraineeDetailsEntity b where b.batchId=?1");
					 q1.setParameter(1, be.getBatchId());
					 List<BatchTraineeDetailsEntity> ls=q1.getResultList();
					 
					 Calendar c=Calendar.getInstance();
						c.add(Calendar.DAY_OF_MONTH, 2);
						Date d=c.getTime();
						CertificationEntity ce=new CertificationEntity();
						ce.setCourseId(be.getCourseId());
						ce.setTestDate(d);
						Query q2=em.createQuery("select c from CertificationEntity c where c.courseId=?1 and c.testDate=?2");
						q2.setParameter(1,ce.getCourseId());
						q2.setParameter(2,ce.getTestDate());
						List<CertificationEntity> li=q2.getResultList();
						if(li.size()!=0)
						{
							throw new InvalidRecord();
						}
						if(li.size()==0)
						{
							
							for (BatchTraineeDetailsEntity be2:ls) {
								
								Integer traineeId=be2.getTraineeId();
								CertificationEntity ce1=new CertificationEntity();
								ce1.setCourseId(be.getCourseId());
								ce1.setTestDate(d);
								ce1.setTraineeId(traineeId);
								
								em.persist(ce1);	
						}
						}
					 et.commit();
			 }
				else
				{
					throw new InvalidClosureDateException();
				}
			 
		 
		 }
		 catch (Exception e) {
				
			 ErrorLogger.logError(this.getClass().getName(),
		                "confirmBatchClosure", e .getMessage());
				 throw e;
			}
		 finally{
				if(em!=null)
				em.close();
			}
		 
		 
		 
		 
		 
	 }
	 @SuppressWarnings("unchecked")
	public List<BatchTO> getAllBatches() throws Exception
	 {
		 EntityManager em=null;
		 List<BatchTO> list=new ArrayList<BatchTO>();
		 List<BatchEntity> l=new ArrayList<BatchEntity>();
		 try
		 {
			 EntityManagerFactory emf=Persistence.createEntityManagerFactory("proj");
			 em=emf.createEntityManager();
			 EntityTransaction et=em.getTransaction();
			 et.begin();
			 Query q=em.createQuery("select b from BatchEntity b");
			 
			 
			 l=q.getResultList();
			 for(BatchEntity be:l)
			 {
				 BatchTO b=new BatchTO();
				 b.setBatchId(be.getBatchId());
				 b.setBatchClosureDate(be.getBatchClosureDate());
				 b.setBatchClosureStatus(be.getBatchClosureStatus());
				 b.setBatchEndDate(be.getBatchEndDate());
				 b.setCourseId(be.getCourseId());
				 b.setStartDate(be.getStartDate());
				 b.setBatchType(be.getBatchType());
				 b.setVenueId(be.getVenueId());
				 b.setBatchStrength(be.getBatchStrength());
				 b.setInstructorId(be.getInstructorId());
				 list.add(b);
			 }
			
			 
		 }
		 catch (Exception e) {
			
			 ErrorLogger.logError(this.getClass().getName(),
		                "getAllBatches", e .getMessage());
			 throw e;
		}
		 finally{
				if(em!=null)
				em.close();
			}
		 return list;
	 }
	 
	 
	}



