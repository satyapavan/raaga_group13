package com.raaga.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.*;

import com.raaga.exceptions.NoPendingCourseApplicationException;
import com.raaga.to.CourseApplicationTO;
import com.raaga.to.CourseDetailsTO;
import com.raaga.to.InstructorTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class ApplyForCourseMB {
	private String courseName;
	private String courseType;
	private Character certification;
	private Integer duration;
	private Double fee;
	private Integer applicationId;
	private Integer traineeId;
	private Integer courseId;
	private Double feePaid;
	private String loggedInUser;
	private String message;
	private List<SelectItem> courseAvailable;
	private Integer instructorId;
	private String instructorName;
	private Long contactNumber;
	private Date dateOfJoining;
	private String address;
	private List<SelectItem> instructorAvailable;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public Character getCertification() {
		return certification;
	}

	public void setCertification(Character certification) {
		this.certification = certification;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Double getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(Double feePaid) {
		this.feePaid = feePaid;
	}

	public String getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SelectItem> getCourseAvailable() {
		return courseAvailable;
	}

	public void setCourseAvailable(List<SelectItem> courseAvailable) {
		this.courseAvailable = courseAvailable;
	}

	public Integer getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<SelectItem> getInstructorAvailable() {
		return instructorAvailable;
	}

	public void setInstructorAvailable(List<SelectItem> instructorAvailable) {
		this.instructorAvailable = instructorAvailable;
	}

	public ApplyForCourseMB() {
		this.message = null;
		List<CourseDetailsTO> list = new ArrayList<CourseDetailsTO>();
		this.courseAvailable = new ArrayList<SelectItem>();

		try {
			list = new RaagaWrapper().getAllCourse();
			for (CourseDetailsTO courseDetailsTO : list) {
				courseAvailable.add(new SelectItem(courseDetailsTO.getCourseId(), courseDetailsTO.getCourseName() + "-"+ courseDetailsTO.getCourseLevel()));
			}

		}


		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "ApplyForCourseMB", e.getMessage());
			this.message=e.getMessage();
		}

	}

	public void getCourseDetail(ValueChangeEvent event) {
		this.message = null;
		this.instructorAvailable = new ArrayList<SelectItem>();
		this.instructorId = null;
		CourseDetailsTO courseDetailsTO = new CourseDetailsTO();

		List<InstructorTO> list = new ArrayList<InstructorTO>();
		Integer v = (Integer) event.getNewValue();

		try {

			courseDetailsTO = new RaagaWrapper().getCourseDetail(v);
			this.courseName = courseDetailsTO.getCourseName();
			this.fee = courseDetailsTO.getFee();
			this.certification = courseDetailsTO.getCertification();
			this.duration = courseDetailsTO.getDuration();

			list = new RaagaWrapper().getInstructorDetails(courseDetailsTO
					.getCourseName());
			for (int i = 0; i < list.size(); i++) {
				instructorAvailable.add(new SelectItem(list.get(i)
						.getInstructorId()));
			}
		}

		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseDetail",
					e.getMessage());
			this.setMessage(e.getMessage());
		}
	}

	public String applyForCourse() 
	{
		try {
			this.message = null;
			if (!(this.fee.equals(this.feePaid))) {
				this.message = "Fee paid should be equal to the Course fee";
				return "failure";
			}
			CourseApplicationTO courseApplicationTO = new CourseApplicationTO();
			FacesContext c = FacesContext.getCurrentInstance(); 
			ExternalContext e = c.getExternalContext();
			HttpSession session = (HttpSession)e.getSession(true);

			this.loggedInUser=(String) session.getAttribute("userId");


			String[] split = this.loggedInUser.split("_");
			this.traineeId = Integer.parseInt(split[1]);

			InstructorTO instructorTO = new InstructorTO();
			instructorTO = (new RaagaWrapper()
			.getInstructorDetail(this.instructorId));
			this.instructorName = instructorTO.getInstructorName();

			this.dateOfJoining = instructorTO.getDateOfJoining();

			courseApplicationTO.setTraineeId(this.traineeId);
			courseApplicationTO.setCourseId(this.getCourseId());

			courseApplicationTO.setApplicationStatus('P');
			courseApplicationTO.setDateOfApplication(new Date());
			courseApplicationTO.setFeePaid(this.feePaid);
			applicationId = new RaagaWrapper()
			.applyForCourse(courseApplicationTO);
			if (applicationId.equals(0)) {
				this.setMessage("You have already registered for one course");
				return "failure";
			}

			this.setMessage("Your application has been submitted with applicationId :"
					+ this.applicationId);
			return "success";
		} catch (NoPendingCourseApplicationException e) {
			ErrorLogger.logError(this.getClass().getName(), "applyForCourse", e
					.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "applyForCourse", e.getMessage());

			this.setMessage(e.getMessage());
			return "failure";
		}

	}
}
