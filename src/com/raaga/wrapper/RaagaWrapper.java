package com.raaga.wrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.raaga.exceptions.DateDifferenceException;
import com.raaga.exceptions.DuplicateEmailIdException;
import com.raaga.exceptions.InstructorNotFreeException;
import com.raaga.exceptions.InvalidBatchClosureDateException;
import com.raaga.exceptions.InvalidBatchStartDateException;
import com.raaga.exceptions.InvalidClosureRequestException;
import com.raaga.exceptions.InvalidCourseLevelException;
import com.raaga.exceptions.InvalidCustomerIdException;
import com.raaga.exceptions.InvalidEndDateException;
import com.raaga.exceptions.InvalidInstructorIdException;
import com.raaga.exceptions.InvalidUsernameException;
import com.raaga.exceptions.LeaveExceededException;
import com.raaga.exceptions.LeaveInAdvanceException;
import com.raaga.exceptions.NoApplicationsSelectedException;
import com.raaga.exceptions.NoBatchesAvailableException;
import com.raaga.exceptions.NoCertificationsException;
import com.raaga.exceptions.NoCourseApplicationPendingException;
import com.raaga.exceptions.NoCourseForEditingException;
import com.raaga.exceptions.NoCourseFoundException;
import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.exceptions.NoInstructorsForSkillException;
import com.raaga.exceptions.NoInstructorsToApproveOrRejectException;
import com.raaga.exceptions.NoPendingCustomerRegistrationException;
import com.raaga.exceptions.NoVenuesAvailableException;
import com.raaga.exceptions.NotAValidBatchException;
import com.raaga.exceptions.WrongPasswordException;
import com.raaga.manager.ApplyLeaveManager;
import com.raaga.manager.ApproveRegistrationManager;
import com.raaga.manager.BatchDetailsManager;
import com.raaga.manager.BatchScheduleManager;
import com.raaga.manager.CertificationManager;
import com.raaga.manager.CourseApplicationManager;
import com.raaga.manager.CourseDetailsManager;
import com.raaga.manager.CustomerRegistrationManager;
import com.raaga.manager.InstructorManager;
import com.raaga.manager.LoginManager;
import com.raaga.manager.TraineeManager;
import com.raaga.service.CourseDetailsService;
import com.raaga.service.CustomerRegistrationService;
import com.raaga.service.LeaveApplicationService;
import com.raaga.service.LoginService;
import com.raaga.service.RequestBatchClosureService;
import com.raaga.service.TraineeService;
import com.raaga.to.BatchTO;
import com.raaga.to.CertificationTO;
import com.raaga.to.ClassRoomTO;
import com.raaga.to.CourseApplicationTO;
import com.raaga.to.CourseDetailsTO;
import com.raaga.to.CustomerRegistrationTO;
import com.raaga.to.InstructorCalendarTO;
import com.raaga.to.InstructorSkillSetTO;
import com.raaga.to.InstructorTO;
import com.raaga.to.LoginTO;
import com.raaga.to.TraineeTO;
import com.raaga.utilities.ErrorLogger;


public class RaagaWrapper {


	public void confirmBatchClosure(BatchTO bto) throws Exception
	{
		try {
			new BatchScheduleManager().confirmBatchClosure(bto);
		} catch (Exception e) {
			
			ErrorLogger.logError(this.getClass().getName(),
	                "confirmBatchClosure", e .getMessage());
			throw e;
		}
	}
	public List<BatchTO> getBatchesForClosure() throws Exception {
		
		List<BatchTO> list=new ArrayList<BatchTO>();
		try {
			list=new BatchScheduleManager().getBatchesForClosure();
		} catch (Exception e) {
			
			ErrorLogger.logError(this.getClass().getName(),
	                "getBatchesForClosure", e .getMessage());
			throw e;
		}
		return list;
		
		
	}
	
	
	public Integer applyForCourse(CourseApplicationTO courseApplicationTO) throws Exception
	{
		Integer applicationId;
		CourseApplicationManager courseApplicationManager = new CourseApplicationManager();
		try{
		applicationId=courseApplicationManager.applyForCourse(courseApplicationTO);
		
		
		
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),
	                "applyForCourse", e.getMessage());
			throw e;
		}
		return applicationId;
	}

	
public Set<String> getAllCourseNames() throws Exception{
		
		CourseDetailsManager courseDetailsManager=new CourseDetailsManager();
		Set<String> set=new TreeSet<String>();
		try{
			 set=courseDetailsManager.getAllCourseNames();
		
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
	                "getAllCourseNames", e.getMessage());
			throw e;
		  }
		return set;
	}

	


	public List<CourseDetailsTO> getCourseDetails(String courseName) throws Exception
	{
		try
		{

			List<CourseDetailsTO> l=new ArrayList<CourseDetailsTO>();

			CourseDetailsService courseDetailsService=new CourseDetailsService();
			l=courseDetailsService.getCourseDetails(courseName);
			return l;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
	                "getCourseDetails", e.getMessage());
			throw e;
		}
	}
	
	public Set<String> getSelectedCourseLevels(String courseName)
	throws InvalidCourseLevelException,Exception {
		try {
			Set<String> set = new CourseDetailsManager().getSelectedCourseLevels(courseName);
			
			return set;
		}
		catch (InvalidCourseLevelException e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getSelectedCourseLevels", e.getMessage());
			throw e;	
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getSelectedCourseLevels", e.getMessage());
			throw e;
		}
	}
	public CourseDetailsTO getCoursesDetailsForEditing(String courseName,String courseLevel) throws NoCourseForEditingException,Exception{
		CourseDetailsManager courseDetailsManager=new CourseDetailsManager();
		CourseDetailsTO courseDetailsTO=new CourseDetailsTO();
		try{
			courseDetailsTO=courseDetailsManager.getCoursesDetailsForEditing(courseName,courseLevel);
		}
		catch(NoCourseForEditingException e){
			ErrorLogger.logError(this.getClass().getName(),
					"getCoursesDetailsForEditing", e.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getCoursesDetailsForEditing", e.getMessage());
			throw e;
		  }
		return courseDetailsTO;
	}

	public void editCourse(CourseDetailsTO detailsTO) throws Exception{
		CourseDetailsManager courseDetailsManager=new CourseDetailsManager();
		try{
			courseDetailsManager.editCourse(detailsTO);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"editCourse", e.getMessage());
			throw e;
		  }
	}
	public Integer addCourseDetails(CourseDetailsTO detailsTO) throws Exception
	{
		try {
			CourseDetailsManager cdm=new CourseDetailsManager();
			return cdm.addCourseDetails(detailsTO);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addCourseDetails", e.getMessage());
			throw e;
		}
	}
	public List<InstructorTO> getInstructorDetails(String skillName) throws  NoInstructorsForSkillException,Exception
	{
		try
		{
			List<InstructorTO> lto=new ArrayList<InstructorTO>();
			InstructorManager manager=new InstructorManager();

			lto=manager.getInstructorDetails(skillName);
			

			return lto;
		}
		catch (NoInstructorsForSkillException e) {
			ErrorLogger.logError(this.getClass().getName(), "getInstructorDetails", e.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getInstructorDetails", e.getMessage());
			throw e;
		}
	}
	public List<CustomerRegistrationTO> getPendingCustomers() throws NoPendingCustomerRegistrationException, Exception

	{	try
	{
		List<CustomerRegistrationTO> list=new CustomerRegistrationManager().getPendingCustomers();
		return list;
	}
	catch (NoPendingCustomerRegistrationException e) {
		ErrorLogger.logError(this.getClass().getName(), "getPendingCustomers",
				e.getMessage());
		throw e;
	}


	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(), "getPendingCustomers",
				e.getMessage());
		throw e;
	}

	}
	public List<CertificationTO> getCertificationReport(Date startDate,
			Date endDate) throws InvalidEndDateException,NoCertificationsException, Exception {
		try
		{
			List<CertificationTO> list=new CertificationManager().getCertificationReport(startDate,endDate);
			if(list==null){
				throw new NoCertificationsException();
			}
			else{
				return list;
			}
			
		}
		catch (InvalidEndDateException Ie) {
			ErrorLogger.logError(this.getClass().getName(),
					"getCertificationReport", Ie.getMessage());
			throw Ie;
			
		}
		catch (NoCertificationsException Ce) {
			ErrorLogger.logError(this.getClass().getName(),
					"getCertificationReport", Ce.getMessage());
			throw Ce;
		}
		catch(Exception e) {
			throw e;
		}
	}
	public Set<Integer> getBatchYears()throws Exception
	{
		BatchScheduleManager batchScheduleManager=new BatchScheduleManager();
		Set<Integer> set=new TreeSet<Integer>();
		try {
			set=batchScheduleManager.getBatchYears();
			return set;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getBatchYears", e.getMessage());
			throw e;
		}

	}

	public List<BatchTO> getBatchReport(Integer month,Integer year) throws NoDetailsAvailableException,Exception
	{
		List<BatchTO> list=new ArrayList<BatchTO>();

		try {
			list=new BatchScheduleManager().getBatchReport(month,year);
			return list;
		} 
		catch (NoDetailsAvailableException e1) {
			ErrorLogger.logError(this.getClass().getName(),
					"getBatchReport", e1.getMessage());
			throw e1;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getBatchReport", e.getMessage());
			throw e;
		}

	}
	public Set<Integer> getAppliedCourses() throws NoCourseApplicationPendingException,Exception {
		try {
			Set<Integer> set=new TreeSet<Integer>();
			set = new CourseApplicationManager()
			.getAppliedCourses();
			return set;
		} 
		catch (NoCourseApplicationPendingException e) {
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
	throws Exception {
		try {
			CourseDetailsTO courseDetailsTO = new CourseApplicationManager()
			.getAppliedCourseDetails(courseId);
			return courseDetailsTO;
		}  catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getAppliedCourseDetails", e.getMessage());
			throw e;
		}
	}

	public List<BatchTO> getBatchesForSelectedCourse(Integer courseId)
	throws Exception {
		try {
			List<BatchTO> list = new CourseApplicationManager()
			.getBatchesForSelectedCourse(courseId);
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getBatchesForSelectedCourse", e.getMessage());
			throw e;
		}
	}
	public List<CourseApplicationTO> getTraineeApplications(Integer courseId)
	throws Exception {
		try {
			List<CourseApplicationTO> list = new CourseApplicationManager()
			.getTraineeApplications(courseId);
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getTraineeApplications", e.getMessage());
			throw e;
		}
	}
	public void approveOrRejectApplication(Character status, Integer batchId,
			List<CourseApplicationTO> selectedApplications) throws NoApplicationsSelectedException,
			NotAValidBatchException,Exception {
		try {
			new CourseApplicationManager().approveOrRejectApplication(status,
					batchId, selectedApplications);
		} 

		catch(NoApplicationsSelectedException e)
		{
			ErrorLogger.logError(this.getClass().getName(),
					"approveOrRejectApplication", e.getMessage());
			throw e;
		}

		catch(NotAValidBatchException e)
		{	ErrorLogger.logError(this.getClass().getName(),
				"approveOrRejectApplication", e.getMessage());
			throw e;
		}
		catch (Exception e) {
			

			ErrorLogger.logError(this.getClass().getName(),
					"approveOrRejectApplication", e.getMessage());
			throw e;
		}
	}
	public Map<Integer, String> getFreeVenues() throws Exception {
		try {
			Map<Integer, String> map = new BatchScheduleManager()
			.getFreeVenues();
			return map;
		}
		catch(NoVenuesAvailableException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "getFreeVenues", e
					.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getFreeVenues", e
					.getMessage());
			throw e;
		}
	}
	public Integer scheduleBatch(BatchTO to) throws InvalidBatchStartDateException,InstructorNotFreeException, Exception {
		try {
			Integer batchId = new BatchScheduleManager().scheduleBatch(to);
			return batchId;
		} 
		catch (InvalidBatchStartDateException e) {
			ErrorLogger.logError(this.getClass().getName(), "scheduleBatch", e
					.getMessage());
			throw e;
		}
		catch (InstructorNotFreeException e) {
			ErrorLogger.logError(this.getClass().getName(), "scheduleBatch", e
					.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "scheduleBatch", e
					.getMessage());
			throw e;
		}
	}
	public List<TraineeTO> getAllTrainee() throws NoDetailsAvailableException,Exception
	{
		try
		{
			List<TraineeTO> list1=new TraineeManager().getAllTrainee();
			
			return list1;
		}
		catch(NoDetailsAvailableException e){
			ErrorLogger.logError(this.getClass().getName(), "getAllTrainee", e.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getAllTrainee", e.getMessage());
			throw e;
		}
	}
	public Integer getInstructorId(String userName) throws Exception {
		LoginManager loginManager=new LoginManager();
		try{
			Integer a=loginManager.getInstructorId(userName);
			return a;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getInstructorId", e.getMessage());
			throw e;
		}
	}
	public List<CourseDetailsTO> getCourseDetailsByInstructor (
			Integer instructorId) throws Exception {
		CourseDetailsService service=new CourseDetailsService();
		List<CourseDetailsTO> list=new ArrayList<CourseDetailsTO>();
		try{
			list=service.getCourseDetailsByInstructor(instructorId);
			return list;


		}catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getCourseDetailsByInstructor", e.getMessage());
			throw e;
		}

	}
	public List<InstructorTO> getAllInstructor() throws NoDetailsAvailableException,Exception {
		InstructorManager instructorManager=new InstructorManager();
		List<InstructorTO>list1=new ArrayList<InstructorTO>();
		try{
			list1=instructorManager. getAllInstructor();
			return list1;
		}
		catch(NoDetailsAvailableException e){
			ErrorLogger.logError(this.getClass().getName(), "getAllTrainee", e.getMessage());
			throw e;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getAllTrainee", e.getMessage());
			throw e;
		}
	}


	public Integer getTraineeId(Integer customerRegistrationId) throws Exception  {
		try {
			Integer traineeId = new TraineeService().getTraineeId(customerRegistrationId);
			return traineeId;
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getTraineeId", e
					.getMessage());
			throw e;
		}

	}
	public LoginTO getCredentials(Integer traineeId) throws Exception  { 
		try {
			LoginTO loginTO = new LoginService().getCredentials(traineeId);
			return loginTO;
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCredentials", e
					.getMessage());
			throw e;	
		}

	}
	public Integer registerCustomer(CustomerRegistrationTO customerTO) throws DuplicateEmailIdException,Exception
	{
		CustomerRegistrationManager manager=new CustomerRegistrationManager();

		try{
			Integer customerId=manager.registerCustomer(customerTO);

			return customerId;
		}
		catch(DuplicateEmailIdException e)
		{	ErrorLogger.logError(this.getClass().getName(), "registerCustomer", e
				.getMessage());
			throw e;
		}

		catch(Exception e)
		{	ErrorLogger.logError(this.getClass().getName(), "registerCustomer", e
				.getMessage());
			throw e;
		}



	}
	public void rejectCustomerApplication(Integer customerRegistrationId,
			String reasonForRejection) throws Exception {
		try
		{
			new ApproveRegistrationManager().rejectCustomerApplication(customerRegistrationId, reasonForRejection);
		}

		catch(Exception e)
		
		{ErrorLogger.logError(this.getClass().getName(), "rejectCustomerApplication", e.getMessage());
			throw e;
		}
	}
	public String addNewLogin(LoginTO loginTO) throws Exception {
		try
		{
			
			String userId=new LoginManager().addNewLogin(loginTO);
			return userId;
		}

		catch(Exception e)
		{
			throw e;
		}
	}
	public Integer approveRegistration(TraineeTO traineeTO) throws Exception {
		try
		{
			
			Integer traineeId=new ApproveRegistrationManager().approveRegistration(traineeTO);
			
			return traineeId;
		}

		catch(Exception e)
		{	ErrorLogger.logError(this.getClass().getName(), "approveRegistration", e.getMessage());
			throw e;
		}

	}
	public CustomerRegistrationTO getCustomerDetail(
			Integer customerRegistrationId) throws InvalidCustomerIdException, Exception

			{	try
			{
				CustomerRegistrationTO cust=new CustomerRegistrationManager().getCustomerDetails(customerRegistrationId);
				return cust;
			}
			catch (InvalidCustomerIdException e) {
				ErrorLogger.logError(this.getClass().getName(), "getCustomerDetail", e.getMessage());
				throw e;
			}

			catch (Exception e) {
				ErrorLogger.logError(this.getClass().getName(), "getCustomerDetail", e.getMessage());
				throw e;
			}

			}

	public Integer getTraineeId(String userName) throws Exception {
		LoginManager loginManager=new LoginManager();
		try{
			Integer a=loginManager.getTraineeId(userName);
			return a;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getTraineeId", e.getMessage());
			throw e;
		}
	}
	public List<CourseDetailsTO> getAllCourse() throws NoCourseFoundException,Exception {
		CourseDetailsManager courseDetailsManager = new CourseDetailsManager();
		List<CourseDetailsTO> list=new ArrayList<CourseDetailsTO>();
		try{
			list=courseDetailsManager.getAllCourse();
		}
		
		catch(NoCourseFoundException e){
			ErrorLogger.logError(this.getClass().getName(), "getAllCourse", e.getMessage());
			throw e;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getAllCourse", e.getMessage());
			throw e;
		}
		return list;
	}
	
	
	public CourseDetailsTO getCourseDetail(Integer courseId) throws Exception
	{
		try {

			CourseDetailsManager cdm=new CourseDetailsManager();
			return cdm.getCourseDetail(courseId);


		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseDetail", e.getMessage());
			throw e;
		}
	}
	
  public String getSkillName(Integer skill)throws Exception{
        
        CourseDetailsManager courseDetailsManager=new CourseDetailsManager();
        try {
              return courseDetailsManager.getSkillName(skill);
        } catch (Exception e) {
        	ErrorLogger.logError(this.getClass().getName(), "getSkillName", e.getMessage()); 
              
              throw e;
        }
  }
	
	public List<CourseApplicationTO> viewApplicationStatus(String s) throws NoDetailsAvailableException,Exception{
		CourseApplicationManager courseApplicationManager =new CourseApplicationManager ();
		List<CourseApplicationTO> list=new ArrayList<CourseApplicationTO>();
		try{
			list=courseApplicationManager.viewApplicationStatus(s);
			return list;

		}
		catch(NoDetailsAvailableException e){
			ErrorLogger.logError(this.getClass().getName(), "viewApplicationStatus", e.getMessage());
			throw e;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "viewApplicationStatus", e.getMessage());
			throw e;
		}
	}

	public List<BatchTO> viewBatchDetails(Character batchType) throws Exception {
		BatchDetailsManager batchDetailsManager = new BatchDetailsManager();
		List<BatchTO> list = new ArrayList<BatchTO>();
		try
		{
			list=batchDetailsManager.viewBatchDetails(batchType);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "viewBatchDetails", e.getMessage());
			throw e;
		}
		return list;
	}
	public Character viewRegistrationStatus(Integer custRegId) throws Exception  {

		try {
			Character c = new CustomerRegistrationService()
			.viewRegistrationStatus(custRegId);
			return c;
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "viewRegistrationStatus", e
					.getMessage());
			throw e;

		}

	}
	
	public List<Integer> viewAllocatedBatches(String s) throws Exception {
		try
		{
			List<Integer> list=new RequestBatchClosureService().viewAllocatedBatches(s);
			return list;
		}
		catch (Exception e) {

			throw e;
		}
	}

	
	public Integer applyLeave(InstructorCalendarTO instTO) throws DateDifferenceException,
	LeaveExceededException,LeaveInAdvanceException,Exception
	{
		try {
			ApplyLeaveManager am=new ApplyLeaveManager();
			return am.applyLeave(instTO);

		} catch(DateDifferenceException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			throw e;
		}
		catch(LeaveInAdvanceException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			throw e;
		}
		catch(LeaveExceededException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "applyLeave", e
					.getMessage());
			throw e;		}

	}
	public List<BatchTO> viewBatchIdDetails(Integer batchId) throws NoBatchesAvailableException,Exception {
		BatchDetailsManager batchDetailManager=new BatchDetailsManager();
		List<BatchTO> list=new ArrayList<BatchTO>();
		try{

			list=batchDetailManager.viewBatchIdDetails(batchId);

			return list;
		}
		catch(NoBatchesAvailableException e){
			ErrorLogger.logError(this.getClass().getName(), "viewBatchIdDetails", e.getMessage());
			throw e;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "viewBatchIdDetails", e.getMessage());
			throw e;
		}

	}
	public Integer fetchInstructor(String s) throws Exception
	{
		try {
			ApplyLeaveManager am=new ApplyLeaveManager();
			return am.fetchInstructor(s);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "fetchInstructor", e.getMessage());
			throw e;
		}
	}
	public List<Integer> getBatchIdDetails()  throws Exception{
		BatchDetailsManager BDManager = new BatchDetailsManager();
		List<Integer> list=new ArrayList<Integer>();
		try{
			list=BDManager.getBatchIdDetails();
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getBatchIdDetails", e.getMessage());
			throw e;
		}
		return list;
	}
	public List<ClassRoomTO> getClassRoomDetails()  throws NoVenuesAvailableException,Exception
	{
		try
		{
			List<ClassRoomTO> list1=new BatchDetailsManager().getClassRoomDetails();
			if(list1.size()==0){
				throw new NoVenuesAvailableException();
			}
			else{
				return list1;
			}
			
		}
		catch(NoVenuesAvailableException e){
			ErrorLogger.logError(this.getClass().getName(), "getClassRoomDetails", e.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getClassRoomDetails", e.getMessage());
			throw e;
		}

	}
	public void editBatch(Integer batchId, Integer classRoomId,
			Date batchStartDate) throws Exception {
		BatchDetailsManager batchDetailManager=new BatchDetailsManager();
		try{

			batchDetailManager.editBatch(batchId,classRoomId,
					batchStartDate);

		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "editBatch", e.getMessage());
			throw e;
		}

	}
	public List<InstructorCalendarTO> fetchLeaves() throws NoInstructorsToApproveOrRejectException,Exception
	{	List<InstructorCalendarTO>list=new ArrayList<InstructorCalendarTO>();

	try {
		list=new LoginManager().fetchLeaves();
		return list;
	} 
	catch(NoInstructorsToApproveOrRejectException e)
	{
		ErrorLogger.logError(this.getClass().getName(), "fetchLeaves", e.getMessage());
		throw e;
	}
	
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(), "fetchLeaves", e.getMessage());
		throw e;

	}
	}
	public void approveLeave(Integer calendarId) throws Exception
	{
		try {
			new LeaveApplicationService().approveLeave(calendarId);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "approveLeave", e.getMessage());
			throw e;
		}
	}
	public void rejectLeave(Integer calendarId) throws Exception
	{
		try {
			new LeaveApplicationService().rejectLeave(calendarId);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "rejectLeave", e.getMessage());
			throw e;
		}
	}
	
	public Boolean requestClosure(Integer batchId, Date batchClosureDate) throws InvalidClosureRequestException,InvalidBatchClosureDateException,Exception {
		try
		{
			Boolean bool=new RequestBatchClosureService().requestClosure(batchId, batchClosureDate);
			return bool;
		}
		catch(InvalidBatchClosureDateException e1)
		{	ErrorLogger.logError(this.getClass().getName(), "requestClosure", e1.getMessage());
			throw e1;
		}
		catch (InvalidClosureRequestException e) {
			ErrorLogger.logError(this.getClass().getName(), "requestClosure", e.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "requestClosure", e.getMessage());
			
			throw e;
		}
	}
	public InstructorTO getInstructorDetail(Integer instructorId)throws Exception
	{
		try {
			InstructorManager im=new InstructorManager();
			return im.getInstructorDetail(instructorId);
		}
		catch(InvalidInstructorIdException e1)
		{	ErrorLogger.logError(this.getClass().getName(), "getInstructorDetail", e1.getMessage());
			throw e1;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getInstructorDetail", e.getMessage());
			
			throw e;
		}	
	}
	public List<InstructorSkillSetTO> getSkillSet(Integer instructorId)throws Exception
	{
		InstructorManager Im=new InstructorManager();
		try {
			return Im.getSkillSet(instructorId);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getSkillSet", e.getMessage());
			throw e;
		}
	}
	public String getSkillSetName(Integer skill)throws Exception
	{
		CourseDetailsManager cdm=new CourseDetailsManager();
		try {
			return cdm.getSkillName(skill);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getSkillSet", e.getMessage());
			
			throw e;
		}
	}
	public CourseDetailsTO getCourseId(String name) throws Exception
	{
		CourseDetailsManager cdm=new CourseDetailsManager();
		try {
			return cdm.getCourseId(name);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCourseId", e.getMessage());
			throw e;
		}
	}
	public void saveInstructor(InstructorTO to) throws Exception
	{
		try
		{
			new InstructorManager().saveInstructor(to);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "saveInstructor", e.getMessage());
			throw e;
		}
	}
	public Character validateLogin(LoginTO loginTO) throws Exception
	{	Character role=null;
	try {
		role=new LoginManager().validateLogin(loginTO);
	}
	catch (InvalidUsernameException e) {
		ErrorLogger.logError(this.getClass().getName(), "validateLogin", e.getMessage());
		throw e;
	}
	catch (WrongPasswordException e) {
		ErrorLogger.logError(this.getClass().getName(), "validateLogin", e.getMessage());
		throw e;
	}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(), "validateLogin", e.getMessage());
		throw e;

	}
	return role;
	}
	public TraineeTO getTraineeDetails(Integer traineeId) throws Exception {
		TraineeManager traineeManager=new TraineeManager();
		TraineeTO traineeTO=new TraineeTO();
		try{
			traineeTO=traineeManager.getTraineeDetails(traineeId);
			return traineeTO;
		}catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getTraineeDetails", e.getMessage());
			throw e;
		}
	}
	public void saveCustomer(CustomerRegistrationTO customerRegistrationTO) throws Exception
	{
		try
		{
			new CustomerRegistrationService().saveCustomer(customerRegistrationTO);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "saveCustomer", e.getMessage());
			throw e;

		}
	}
	
}
