package com.raaga.manager;

import java.util.ArrayList;
import java.util.List;

import com.raaga.exceptions.InvalidInstructorIdException;
import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.exceptions.NoInstructorsForSkillException;
import com.raaga.service.InstructorService;
import com.raaga.to.InstructorSkillSetTO;
import com.raaga.to.InstructorTO;
import com.raaga.utilities.ErrorLogger;

public class InstructorManager {



	public List<InstructorTO> getInstructorDetails(String skillName) throws NoInstructorsForSkillException,Exception  {
		try {
			List<InstructorTO> list = new InstructorService()
			.getInstructorDetails(skillName);

			if (list.isEmpty()) {
				throw new NoInstructorsForSkillException();
			}
			return list;
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

	public InstructorTO getInstructorDetail(Integer instructorId) throws InvalidInstructorIdException,Exception
	{


		try {
			InstructorService is=new InstructorService();
			if(is.getInstructorDetail(instructorId)==null)
			{
				throw new InvalidInstructorIdException();
			}
			return is.getInstructorDetail(instructorId);
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

	public List<InstructorTO> getAllInstructor() throws NoDetailsAvailableException,Exception {
		InstructorService instructorService=new InstructorService();
		List<InstructorTO>list1=new ArrayList<InstructorTO>();
		try{
			list1=instructorService.getAllInstructor();

			if(list1.isEmpty()){
				throw new NoDetailsAvailableException();
			}else{
				return list1;
			}
		}
		catch(NoDetailsAvailableException e){
			ErrorLogger.logError(this.getClass().getName(), "getAllInstructor", e.getMessage());
			throw e;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getAllInstructor", e.getMessage());
			throw e;
		}
	}
	public void saveInstructor(InstructorTO to) throws Exception{
		try{
			new InstructorService().saveInstructor(to);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "saveCustomer", e.getMessage());
			throw e;
		}
	}
	public List<InstructorSkillSetTO> getSkillSet(Integer instructorId) throws Exception
	{InstructorService is=new InstructorService();
	try {
		return is.getSkillSet(instructorId);
	} catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(), "getSkillSet", e.getMessage());
		throw e;
	}

	}

}
