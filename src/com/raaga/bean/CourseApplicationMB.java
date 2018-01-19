package com.raaga.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.raaga.exceptions.ClassCapacityExceededException;
import com.raaga.exceptions.NoApplicationsSelectedException;
import com.raaga.exceptions.NoBatchesAvailableException;
import com.raaga.exceptions.NoCourseApplicationPendingException;
import com.raaga.exceptions.NoCoursesAvailableException;
import com.raaga.exceptions.NotAValidBatchException;
import com.raaga.to.BatchTO;
import com.raaga.to.CourseApplicationTO;
import com.raaga.to.CourseDetailsTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class CourseApplicationMB {
	private Integer courseId;
	private List<SelectItem> courseIds;
	private CourseDetailsTO courseDetailsTO;
	private List<BatchTO> batchTOs;
	private List<CourseApplicationTO> applicationTOs;
	private Character status;
	private Integer batchId;
	private List<SelectItem> availableBatches;
	private String message;

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public List<SelectItem> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<SelectItem> courseIds) {
		this.courseIds = courseIds;
	}

	public CourseDetailsTO getCourseDetailsTO() {
		return courseDetailsTO;
	}

	public void setCourseDetailsTO(CourseDetailsTO courseDetailsTO) {
		this.courseDetailsTO = courseDetailsTO;
	}

	public List<BatchTO> getBatchTOs() {
		return batchTOs;
	}

	public void setBatchTOs(List<BatchTO> batchTOs) {
		this.batchTOs = batchTOs;
	}

	public List<CourseApplicationTO> getApplicationTOs() {
		return applicationTOs;
	}

	public void setApplicationTOs(List<CourseApplicationTO> applicationTOs) {
		this.applicationTOs = applicationTOs;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public List<SelectItem> getAvailableBatches() {
		return availableBatches;
	}

	public void setAvailableBatches(List<SelectItem> availableBatches) {
		this.availableBatches = availableBatches;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CourseApplicationMB() {
		try {
			this.message=null;
			this.courseIds = new ArrayList<SelectItem>();
			Set<Integer> set = new TreeSet<Integer>();
			set = new RaagaWrapper().getAppliedCourses();
			for (Integer iti : set) {
				this.courseIds.add(new SelectItem(iti));
			}
		} catch (NoCoursesAvailableException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"CourseApplicationMB", e.getMessage());
			this.message=e.getMessage();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"CourseApplicationMB", e.getMessage());
			this.message=e.getMessage();

		}
	}

	public void getSelectedCourse(ValueChangeEvent event) {
		try {
			this.message=null;
			this.availableBatches = new ArrayList<SelectItem>();
			this.courseId = Integer.parseInt(event.getNewValue().toString());
			this.courseDetailsTO = new RaagaWrapper()
			.getAppliedCourseDetails(this.courseId);
			this.applicationTOs = new RaagaWrapper().getTraineeApplications(this.courseId);
			this.batchTOs = new RaagaWrapper()
			.getBatchesForSelectedCourse(this.courseId);


			for (BatchTO iter : batchTOs) {
				this.availableBatches.add(new SelectItem(iter.getBatchId()));
			}

		} catch (NoCourseApplicationPendingException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getSelectedCourse", e.getMessage());
			this.setMessage(e.getMessage());
		} catch (NoCoursesAvailableException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getSelectedCourse", e.getMessage());
			this.setMessage(e.getMessage());
		} catch (NoBatchesAvailableException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getSelectedCourse", e.getMessage());
			this.setMessage(e.getMessage());
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getSelectedCourse", e.getMessage());
			this.setMessage(e.getMessage());
		}
	}

	public void getSelectedStatus(ValueChangeEvent event) {
		
			this.status = event.getNewValue().toString().charAt(0);

		
	}

	public String approveOrRejectApplication() {
		try {
			this.message=null;
			List<CourseApplicationTO> list = new ArrayList<CourseApplicationTO>();
			for (CourseApplicationTO iti : this.applicationTOs) {
				if (iti.isChecked()) {
					list.add(iti);
				}
			}
			new RaagaWrapper().approveOrRejectApplication(this.status,
					this.batchId, list);

			this.setMessage("Applications Updated Successfully");
			return "success";
		} catch (ClassCapacityExceededException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"approveOrRejectApplication", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		} catch (NotAValidBatchException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"approveOrRejectApplication", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		} catch (NoApplicationsSelectedException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"approveOrRejectApplication", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"approveOrRejectApplication", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}

	}
}
