package com.raaga.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.raaga.exceptions.InstructorNotFreeException;
import com.raaga.exceptions.InvalidBatchStartDateException;
import com.raaga.exceptions.InvalidCourseLevelException;

import com.raaga.exceptions.NoCoursesAvailableException;
import com.raaga.exceptions.NoInstructorsForSkillException;
import com.raaga.exceptions.NoVenuesAvailableException;
import com.raaga.to.BatchTO;
import com.raaga.to.InstructorTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class BatchScheduleMB {
	private List<SelectItem> courseNames;
	private List<SelectItem> courseLevels;
	private String selectedCourseLevel;
	private String selectedCourseName;
	private Integer instructorId;
	private List<SelectItem> instructors;
	private Integer classRoomId;
	private List<SelectItem> classRooms;
	private Date startDate;
	private Character batchType;
	private String message;

	public List<SelectItem> getCourseNames() {
		return courseNames;
	}

	public void setCourseNames(List<SelectItem> courseNames) {
		this.courseNames = courseNames;
	}

	public List<SelectItem> getCourseLevels() {
		return courseLevels;
	}

	public void setCourseLevels(List<SelectItem> courseLevels) {
		this.courseLevels = courseLevels;
	}

	public String getSelectedCourseLevel() {
		return selectedCourseLevel;
	}

	public void setSelectedCourseLevel(String selectedCourseLevel) {
		this.selectedCourseLevel = selectedCourseLevel;
	}

	public String getSelectedCourseName() {
		return selectedCourseName;
	}

	public void setSelectedCourseName(String selectedCourseName) {
		this.selectedCourseName = selectedCourseName;
	}

	public Integer getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}

	public List<SelectItem> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<SelectItem> instructors) {
		this.instructors = instructors;
	}

	public Integer getClassRoomId() {
		return classRoomId;
	}

	public void setClassRoomId(Integer classRoomId) {
		this.classRoomId = classRoomId;
	}

	public List<SelectItem> getClassRooms() {
		return classRooms;
	}

	public void setClassRooms(List<SelectItem> classRooms) {
		this.classRooms = classRooms;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Character getBatchType() {
		return batchType;
	}

	public void setBatchType(Character batchType) {
		this.batchType = batchType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BatchScheduleMB() {
		try {
			this.message=null;
			this.courseNames = new ArrayList<SelectItem>();
			Set<String> set = new RaagaWrapper().getAllCourseNames();
			for (String iti : set) {
				this.courseNames.add(new SelectItem(iti, iti));
			}

		} catch (NoCoursesAvailableException e) {
			ErrorLogger.logError(this.getClass().getName(), "BatchScheduleMB",
					e.getMessage());
			this.message=e.getMessage();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "BatchScheduleMB",
					e.getMessage());
			this.message=e.getMessage();
		}
	}

	public void getCourseName(ValueChangeEvent event) {
		this.message=null;
		this.selectedCourseName = event.getNewValue().toString();
		
		try {
			this.courseLevels = new ArrayList<SelectItem>();


			Set<String> levelSet = new RaagaWrapper().getSelectedCourseLevels(this.selectedCourseName);
			for (String iter : levelSet) {
				this.courseLevels.add(new SelectItem(iter, iter));
			}
			
			this.classRooms = new ArrayList<SelectItem>();
			Map<Integer, String> map = new RaagaWrapper().getFreeVenues();
			Set<Entry<Integer, String>> roomSet = map.entrySet();
			for (Entry<Integer, String> iter : roomSet) {
				this.classRooms.add(new SelectItem(iter.getKey(), iter
						.getValue()));
			}
			
			this.instructors = new ArrayList<SelectItem>();
			List<InstructorTO> list = new RaagaWrapper().getInstructorDetails(this.selectedCourseName);
			
			for (InstructorTO iter : list) {
				this.instructors.add(new SelectItem(iter.getInstructorId(),
						iter.getInstructorName()));
			}
			
		} catch (NoInstructorsForSkillException e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseName", e
					.getMessage());
			this.setMessage(e.getMessage());
		} catch (NoVenuesAvailableException e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseName", e
					.getMessage());
			this.setMessage(e.getMessage());
		} catch (InvalidCourseLevelException e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseName", e
					.getMessage());
			this.setMessage(e.getMessage());
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseName", e
					.getMessage());
			this.setMessage(e.getMessage());
		}
	}

	public String scheduleBatch() {
		try {
			this.message=null;
			BatchTO batchTO = new BatchTO();
			batchTO.setCourseName(this.selectedCourseName);
			batchTO.setCourseLevel(this.selectedCourseLevel);
			batchTO.setStartDate(this.startDate);
			batchTO.setBatchType(this.batchType);
			batchTO.setInstructorId(this.instructorId);
			batchTO.setVenueId(this.classRoomId);
			Integer batchId = new RaagaWrapper().scheduleBatch(batchTO);
			this.setMessage("Batch scheduled successfully with id : " + batchId);
			return "success";
		} catch (InstructorNotFreeException e) {
			ErrorLogger.logError(this.getClass().getName(), "scheduleBatch", e
					.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		} catch (InvalidBatchStartDateException e) {
			ErrorLogger.logError(this.getClass().getName(), "scheduleBatch", e
					.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "scheduleBatch", e
					.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
	}
}
