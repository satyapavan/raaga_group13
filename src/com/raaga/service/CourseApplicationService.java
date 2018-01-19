package com.raaga.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.raaga.bean.BatchTraineeDetailsPK;
import com.raaga.entity.BatchEntity;
import com.raaga.entity.BatchTraineeEntity;
import com.raaga.entity.ClassRoomEntity;
import com.raaga.entity.CourseApplicationEntity;
import com.raaga.entity.CourseDetailsEntity;
import com.raaga.entity.InstructorEntity;
import com.raaga.entity.TraineeEntity;
import com.raaga.exceptions.AlreadyInCourseException;
import com.raaga.exceptions.ClassCapacityExceededException;
import com.raaga.to.BatchTO;
import com.raaga.to.CourseApplicationTO;
import com.raaga.to.CourseDetailsTO;
import com.raaga.utilities.ErrorLogger;

public class CourseApplicationService {
	@SuppressWarnings("unchecked")
	public Set<Integer> getAppliedCourses() throws Exception {
		EntityManager em = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em
			.createQuery("select a.courseId from CourseApplicationEntity a");
			list = query.getResultList();
			Set<Integer> set = new TreeSet<Integer>();
			for (Integer iter : list) {
				set.add(iter);
			}
			return set;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getAppliedCourses", e.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public CourseDetailsTO getAppliedCourseDetails(Integer courseId)
	throws Exception {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			CourseDetailsEntity entity = em.find(CourseDetailsEntity.class,
					courseId);
			CourseDetailsTO to = new CourseDetailsTO();
			to.setCourseId(entity.getCourseId());
			to.setCourseName(entity.getCourseName());
			to.setCourseType(entity.getCourseType());
			to.setCourseLevel(entity.getCourseLevel());
			to.setCertification(entity.getCertification());
			to.setDuration(entity.getDuration());
			to.setFee(entity.getFee());
			return to;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getAppliedCourseDetails", e.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<CourseApplicationTO> getTraineeApplications(Integer courseId)
	throws Exception {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em
			.createQuery("select a from CourseApplicationEntity a where a.courseId=?1 and a.applicationStatus=?2");
			query.setParameter(1, courseId);
			query.setParameter(2, 'P');
			List<CourseApplicationEntity> list = query.getResultList();
			List<CourseApplicationTO> toList = new ArrayList<CourseApplicationTO>();
			for (CourseApplicationEntity iter : list) {
				TraineeEntity entity = em.find(TraineeEntity.class, iter
						.getTraineeId());
				CourseApplicationTO to = new CourseApplicationTO();
				to.setApplicationId(iter.getApplicationId());
				to.setTraineeId(iter.getTraineeId());
				to.setTraineeName(entity.getTraineeName());
				to.setCourseId(iter.getCourseId());
				to.setFeePaid(iter.getFeePaid());
				to.setApplicationStatus(iter.getApplicationStatus());
				to.setDateOfApplication(iter.getDateOfApplication());
				// to.setChecked(iter.getChecked());
				toList.add(to);
			}
			return toList;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getTraineeApplications", e.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<BatchTO> getBatchesForSelectedCourse(Integer courseId)
	throws Exception {
		EntityManager em = null;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em
			.createQuery("select a from BatchEntity a where a.courseId=?1");
			query.setParameter(1, courseId);
			List<BatchEntity> list = query.getResultList();

			List<BatchTO> toList = new ArrayList<BatchTO>();
			for (BatchEntity iter : list) {
				BatchTO to = new BatchTO();

				ClassRoomEntity entity = em.find(ClassRoomEntity.class, iter
						.getVenueId());
				to.setClassRoomName(entity.getClassRoomName());

				CourseDetailsEntity entity1 = em.find(
						CourseDetailsEntity.class, iter.getCourseId());
				to.setCourseName(entity1.getCourseName());
				to.setCourseLevel(entity1.getCourseLevel());

				InstructorEntity entity2 = em.find(InstructorEntity.class, iter
						.getInstructorId());
				to.setInstructorName(entity2.getInstructorName());

				to.setBatchId(iter.getBatchId());
				to.setStartDate(iter.getStartDate());
				to.setBatchStrength(iter.getBatchStrength());
				to.setVenueId(iter.getVenueId());
				to.setCourseId(iter.getCourseId());
				to.setBatchType(iter.getBatchType());
				to.setInstructorId(iter.getInstructorId());
				to.setBatchEndDate(iter.getBatchEndDate());
				to.setBatchClosureDate(iter.getBatchClosureDate());
				to.setBatchClosureStatus(iter.getBatchClosureStatus());

				toList.add(to);
			}
			return toList;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getBatchesForSelectedCourse", e.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public boolean checkValidBatchId(Integer batchId, Integer noOfApplicants)
	throws Exception {
		EntityManager em = null;
		try {

			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			BatchEntity entity = em.find(BatchEntity.class, batchId);

			if (entity.getBatchClosureStatus().equals('C')
					|| entity.getBatchClosureStatus().equals('R')) {
				return false;
			}
			if (noOfApplicants > entity.getBatchStrength()) {
				return false;
			} else

				return true;
		} catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(),
					"checkValidBatchId", e.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void approveOrRejectApplication(Character status, Integer batchId,
			List<CourseApplicationTO> selectedApplications) throws Exception {
		EntityManager em = null;

		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			if (status.equals('A')) {
				Integer noOfApplications = selectedApplications.size();

				BatchEntity batch = em.find(BatchEntity.class, batchId);

				Integer strength = batch.getBatchStrength();

				ClassRoomEntity classRoom = em.find(ClassRoomEntity.class,
						batch.getVenueId());

				Integer classCapacity = classRoom.getCapacity();

				if (strength + noOfApplications > classCapacity) {
					throw new ClassCapacityExceededException();
				}

				Integer traineeId = null;

				for (CourseApplicationTO iter : selectedApplications) {

					BatchTraineeEntity b = new BatchTraineeEntity();
					BatchTraineeDetailsPK p = new BatchTraineeDetailsPK();

					CourseApplicationEntity entity = em.find(
							CourseApplicationEntity.class, iter
							.getApplicationId());

					entity.setApplicationStatus(status);
					batch.setBatchStrength(batch.getBatchStrength() + 1);
					em.merge(entity);
					em.merge(batch);
					traineeId = iter.getTraineeId();

					p.setBatchId(batchId);
					p.setTraineeId(traineeId);
					b.setBPK(p);
					em.persist(b);
				}

				em.getTransaction().commit();
			} else if (status.equals('R')) {
				for (CourseApplicationTO iter : selectedApplications) {
					CourseApplicationEntity entity = em.find(
							CourseApplicationEntity.class, iter
							.getApplicationId());
					entity.setApplicationStatus(status);
					em.merge(entity);

				}

				em.getTransaction().commit();
			}
		} catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(),
					"approveOrRejectApplication", e.getMessage());
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<CourseApplicationTO> viewApplicationStatus(String s)
	throws Exception {
		EntityManager em = null;
		List<CourseApplicationTO> list = new ArrayList<CourseApplicationTO>();
		List<CourseApplicationEntity> list2 = new ArrayList<CourseApplicationEntity>();
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query q = em
			.createQuery("select l.traineeId from LoginEntity l where l.userId=?1");
			q.setParameter(1, s);
			Integer traineeId = (Integer) q.getSingleResult();

			Query q2 = em
			.createQuery("select ca from CourseApplicationEntity ca where ca.traineeId=?1");
			q2.setParameter(1, traineeId);
			list2 = q2.getResultList();
			for (int i = 0; i < list2.size(); i++) {

				CourseApplicationEntity courseApplicationEntity = list2.get(i);
				CourseApplicationTO courseApplicationTO = new CourseApplicationTO();

				courseApplicationTO.setApplicationId(courseApplicationEntity
						.getApplicationId());
				courseApplicationTO
				.setApplicationStatus(courseApplicationEntity
						.getApplicationStatus());



				courseApplicationTO.setCourseId(courseApplicationEntity
						.getCourseId());
				courseApplicationTO
				.setDateOfApplication(courseApplicationEntity
						.getDateOfApplication());
				courseApplicationTO.setFeePaid(courseApplicationEntity
						.getFeePaid());

				courseApplicationTO.setTraineeId(courseApplicationEntity
						.getTraineeId());

				TraineeEntity traineeEntity = em.find(TraineeEntity.class,
						courseApplicationEntity.getTraineeId());

				courseApplicationTO.setTraineeName(traineeEntity
						.getTraineeName());

				list.add(courseApplicationTO);

			}
			return list;
		}

		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"viewApplicationStatus", e.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public Integer applyForCourse(CourseApplicationTO courseApplicationTO)
	throws  Exception {
		EntityManager em = null;
		int flag = 1;
		try {
			EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("proj");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			CourseApplicationEntity courseApplicationEntity = new CourseApplicationEntity();


			Query query = em
			.createQuery("select c from CourseApplicationEntity c where c.traineeId=?1");
			query.setParameter(1, courseApplicationTO.getTraineeId());
			List<CourseApplicationEntity> list = query.getResultList();
			if (!(list.isEmpty())) {
				Integer cid = courseApplicationTO.getCourseId();
				Query q1 = em
				.createQuery("select b from BatchEntity b where b.courseId=?1");
				q1.setParameter(1, cid);
				List<BatchEntity> list1 = q1.getResultList();
				if (list1.isEmpty()) {
					flag = 0;
				} else {
					Character status = list1.get(list1.size() - 1)
					.getBatchClosureStatus();

					if (status == 'R') {
						flag = 0;
						throw new AlreadyInCourseException();
					} else {
						flag = 1;
					}
				}

			}

			if (flag == 1) {
				courseApplicationEntity
				.setApplicationStatus(courseApplicationTO
						.getApplicationStatus());
				courseApplicationEntity.setCourseId(courseApplicationTO
						.getCourseId());
				courseApplicationEntity
				.setDateOfApplication(courseApplicationTO
						.getDateOfApplication());
				courseApplicationEntity.setFeePaid(courseApplicationTO
						.getFeePaid());
				courseApplicationEntity.setTraineeId(courseApplicationTO
						.getTraineeId());
				em.persist(courseApplicationEntity);
				em.getTransaction().commit();
			} else {
				throw new AlreadyInCourseException();
			}
			return courseApplicationEntity.getApplicationId();
		} catch (AlreadyInCourseException e) {
			ErrorLogger.logError(this.getClass().getName(), "applyForCourse", e
					.getMessage());
			throw e;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "applyForCourse", e
					.getMessage());
			throw e;
		} finally {
			if (em != null)
				em.close();
		}
	}
}