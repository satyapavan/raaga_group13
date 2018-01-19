package com.raaga.manager;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


import com.raaga.exceptions.NoApplicationsSelectedException;
import com.raaga.exceptions.NoBatchesAvailableException;
import com.raaga.exceptions.NoCourseApplicationPendingException;
import com.raaga.exceptions.NoCoursesAvailableException;
import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.exceptions.NotAValidBatchException;
import com.raaga.service.CourseApplicationService;


import com.raaga.to.BatchTO;
import com.raaga.to.CourseApplicationTO;
import com.raaga.to.CourseDetailsTO;
import com.raaga.utilities.ErrorLogger;

public class CourseApplicationManager {
	public List<CourseApplicationTO> viewApplicationStatus(String traineeName) throws NoDetailsAvailableException,Exception{
		CourseApplicationService c=new CourseApplicationService();
		try{
			List<CourseApplicationTO> list=c.viewApplicationStatus(traineeName);
			return list;
			
		}catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "viewApplicationStatus", e.getMessage());
			throw e;
		}

	}
	public Integer applyForCourse(CourseApplicationTO courseApplicationTO) throws Exception{
		
		CourseApplicationService courseApplicationService = new CourseApplicationService();
		Integer applicationId=0;
		try{
		applicationId=courseApplicationService.applyForCourse(courseApplicationTO);
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "applyForCourse", e.getMessage());
			throw e;
		}
		
		return applicationId;
	}
	public Set<Integer> getAppliedCourses() throws NoCourseApplicationPendingException,Exception {
		try {
			Set<Integer> set = new TreeSet<Integer>();

			set = new CourseApplicationService().getAppliedCourses();
			if (set.isEmpty()) {
				throw new NoCoursesAvailableException();
			}
			return set;
		}catch (NoCourseApplicationPendingException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getAppliedCourses", e.getMessage());
			throw e;
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getAppliedCourses", e.getMessage());
			throw e;
		}
	}

	public CourseDetailsTO getAppliedCourseDetails(Integer courseId)
			throws NoCoursesAvailableException,Exception {
		try {
			CourseDetailsTO courseDetailsTO = new CourseApplicationService()
					.getAppliedCourseDetails(courseId);
			if (courseDetailsTO == null) {
				throw new NoCoursesAvailableException();
			}
			return courseDetailsTO;
		} 
		catch(NoCoursesAvailableException e)
		{
			ErrorLogger.logError(this.getClass().getName(),
					"getAppliedCourseDetails", e.getMessage());
			throw e;	
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getAppliedCourseDetails", e.getMessage());
			throw e;
		}
	}

	public List<CourseApplicationTO> getTraineeApplications(Integer courseId)
			throws Exception {
		try {
			List<CourseApplicationTO> list = new CourseApplicationService()
					.getTraineeApplications(courseId);
			if(list.isEmpty()){
				throw new NoCourseApplicationPendingException();
			}
			
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getTraineeApplications", e.getMessage());
			throw e;
		}
	}

	public List<BatchTO> getBatchesForSelectedCourse(Integer courseId)
			throws NoBatchesAvailableException,Exception {
		try {
			List<BatchTO> list = new CourseApplicationService()
					.getBatchesForSelectedCourse(courseId);
			if (list.isEmpty()) {
				throw new NoBatchesAvailableException();
			}
			return list;
		} 
		catch(NoBatchesAvailableException e)
		{
			ErrorLogger.logError(this.getClass().getName(),
					"getBatchesForSelectedCourse", e.getMessage());
			throw e;	
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getBatchesForSelectedCourse", e.getMessage());
			throw e;
		}
	}

	public void approveOrRejectApplication(Character status, Integer batchId,
			List<CourseApplicationTO> selectedApplications) throws NoApplicationsSelectedException,NotAValidBatchException,Exception {
		try {
			if (selectedApplications.isEmpty()) {
				throw new NoApplicationsSelectedException();
			}
			if (status.equals('A')) {
				if (new CourseApplicationService().checkValidBatchId(batchId,selectedApplications.size())) 
				{	
					new CourseApplicationService().approveOrRejectApplication(
							status, batchId, selectedApplications);
				}
			}
				else if (status.equals('R')) {
					new CourseApplicationService().approveOrRejectApplication(
							status, batchId, selectedApplications);
				}
				else{
					throw new NotAValidBatchException();
				}
			
		} 
		catch(NoApplicationsSelectedException e)
		{
			ErrorLogger.logError(this.getClass().getName(),
					"approveOrRejectApplication", e.getMessage());
			throw e;
		}
		
		catch(NotAValidBatchException e)
		{ErrorLogger.logError(this.getClass().getName(),
				"approveOrRejectApplication", e.getMessage());
			throw e;
		}
		
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"approveOrRejectApplication", e.getMessage());
			throw e;
		}
	}

}
