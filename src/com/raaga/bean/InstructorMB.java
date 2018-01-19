package com.raaga.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.to.CourseDetailsTO;
import com.raaga.to.InstructorSkillSetTO;
import com.raaga.to.InstructorTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class InstructorMB {
	private Integer instructorId;
	private String instructorName;
	private Long contactNumber;
	private Date dateOfJoining;
	private String address;
	private Integer courseId;
	private String message;
	private List<SelectItem> instructorList;
	private List<Integer> courseTaken;
	private List<CourseDetailsTO> courseDetails;
	private String loggedInUser;
	private Character role;

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
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<SelectItem> getInstructorList() {
		return instructorList;
	}
	public void setInstructorList(List<SelectItem> instructorList) {
		this.instructorList = instructorList;
	}
	public List<Integer> getCourseTaken() {
		return courseTaken;
	}
	public void setCourseTaken(List<Integer> courseTaken) {
		this.courseTaken = courseTaken;
	}
	public List<CourseDetailsTO> getCourseDetails() {
		return courseDetails;
	}
	public void setCourseDetails(List<CourseDetailsTO> courseDetails) {
		this.courseDetails = courseDetails;
	}
	public String getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	public Character getRole() {
		return role;
	}
	public void setRole(Character role) {
		this.role = role;
	}
	public InstructorMB(){
		this.message=null;
		List<InstructorTO> ilist=new ArrayList<InstructorTO>();
		instructorList=new ArrayList<SelectItem>();
		FacesContext ct = FacesContext.getCurrentInstance(); 
		ExternalContext et = ct.getExternalContext();
		HttpSession session = (HttpSession)et.getSession(true);

		String userName=(String) session.getAttribute("userId");
		
		try {
			ilist=new RaagaWrapper().getAllInstructor();
			for (InstructorTO instructorTO : ilist) {
				instructorList.add(new SelectItem(instructorTO.getInstructorId(),instructorTO.getInstructorName()));				

			}

			Character c= (Character) session.getAttribute("role");
			if(c=='I'){

				InstructorTO instructorTO=new InstructorTO();
				RaagaWrapper wrap=new RaagaWrapper();
				Integer InstructorId=wrap.getInstructorId(userName);
				if(InstructorId!=null){
					instructorTO=wrap.getInstructorDetail(InstructorId);
					this.instructorName=instructorTO.getInstructorName();
					this.dateOfJoining=instructorTO.getDateOfJoining();
					this.address=instructorTO.getAddress();
					this.contactNumber=instructorTO.getContactNumber();
					this.courseDetails=wrap.getCourseDetailsByInstructor(InstructorId);
				}
			}
		}
		catch (NoDetailsAvailableException e) {

			ErrorLogger.logError(this.getClass().getName(), "InstructorMB", e.getMessage());
			this.message=e.getMessage();
		}
		catch (Exception e) {

			ErrorLogger.logError(this.getClass().getName(), "InstructorMB", e.getMessage());
			this.message=e.getMessage();
		}
	}
	public String editInstructor(){
		this.message=null;
		InstructorTO instructorTO=new InstructorTO();
		instructorTO.setAddress(this.address);
		instructorTO.setContactNumber(this.contactNumber);
		instructorTO.setDateOfJoining(this.dateOfJoining);
		instructorTO.setInstructorId(this.instructorId);
		instructorTO.setInstructorName(this.instructorName);
		try {
			new RaagaWrapper().saveInstructor(instructorTO);
			this.message="Details saved successfully";
			return "success";
		}catch(Exception e){

			ErrorLogger.logError(this.getClass().getName(), "editInstructor",e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}

	}

	public void getInstructorDetail(ValueChangeEvent vce){

		try{
			this.message=null;
			this.instructorId=Integer.parseInt(vce.getNewValue().toString());

			RaagaWrapper wrap=new RaagaWrapper();
			InstructorTO ito=new InstructorTO();
			ito=wrap.getInstructorDetail(this.instructorId);
			this.instructorName=ito.getInstructorName();
			this.dateOfJoining=ito.getDateOfJoining();
			this.address=ito.getAddress();
			this.contactNumber=ito.getContactNumber();
			this.courseDetails=new RaagaWrapper().getCourseDetailsByInstructor(instructorId);

			List<InstructorSkillSetTO> ls=new ArrayList<InstructorSkillSetTO>();
			ls=wrap.getSkillSet(instructorId);
			List<String> sName=new ArrayList<String>();
			for(InstructorSkillSetTO isto:ls)
			{

				sName.add(wrap.getSkillSetName(isto.getSkillId()));
			}
			List<Integer> cid=new ArrayList<Integer>();
			for(String s2:sName)
			{

				cid.add(wrap.getCourseId(s2).getCourseId())	;
			}
			courseDetails=new ArrayList<CourseDetailsTO>();
			for(Integer CourseId:cid)
			{

				courseDetails.add(wrap.getCourseDetail(CourseId));

			}


		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getInstructorDetail",e.getMessage());

			this.setMessage(e.getMessage());
		}
	}

}
