package com.raaga.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.to.InstructorTO;
import com.raaga.to.TraineeTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class EditDetailsMB {
	private int choice;
	private List<SelectItem> instructorList;
	private String message;
	private List<SelectItem> traineeList;
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public List<SelectItem> getInstructorList() {
		return instructorList;
	}
	public void setInstructorList(List<SelectItem> instructorList) {
		this.instructorList = instructorList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<SelectItem> getTraineeList() {
		return traineeList;
	}
	public void setTraineeList(List<SelectItem> traineeList) {
		this.traineeList = traineeList;
	}
	public EditDetailsMB()
	{
		this.choice=0;
		this.message=null;
		
		List<InstructorTO> inst=new ArrayList<InstructorTO>();
		traineeList=new ArrayList<SelectItem>();
		instructorList=new ArrayList<SelectItem>();
		try
		{	
			
			
			List<TraineeTO>trainee=new RaagaWrapper().getAllTrainee();
           
			for(TraineeTO t:trainee)
			{
				this.traineeList.add(new SelectItem(t.getTraineeId(),t.getTraineeName()));
			}
			
			inst=new RaagaWrapper().getAllInstructor();
			for(InstructorTO t:inst)
			{
				this.instructorList.add(new SelectItem(t.getInstructorId(),t.getInstructorName()));
			}
		

		}
		catch(NoDetailsAvailableException e){
			ErrorLogger.logError(this.getClass().getName(), "getAllTrainee", e.getMessage());
			this.message=e.getMessage();
		}
		catch(Exception e)
		{	
			
			ErrorLogger.logError(this.getClass().getName(),
                    "EditDetailsMB", e.getMessage());

		}

	}
	public void getList(ValueChangeEvent event)
	{
		
		Integer i=(Integer)event.getNewValue();
		this.choice=i;
	}

}
