package com.raaga.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.raaga.to.InstructorTO;
import com.raaga.to.TraineeTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class ViewDetailsMB {
	private int choice;
	private List<SelectItem> instructorList;
	private String message;
	private List<SelectItem> traineeList;


	public ViewDetailsMB(){
		
		List<TraineeTO>list1=new ArrayList<TraineeTO>();
		List<InstructorTO>list2=new ArrayList<InstructorTO>();
		try{
			this.message=null;
			RaagaWrapper raagaWrapper =new RaagaWrapper ();
			list1=raagaWrapper.getAllTrainee();
			for(int i=0;i<list1.size();i++){
				TraineeTO traineeTO =list1.get(i);
				this.traineeList.add(new SelectItem(traineeTO.getTraineeId(),traineeTO.getTraineeName()));
			}
			list2=raagaWrapper.getAllInstructor();
			for(int i=0;i<list2.size();i++){
				InstructorTO instructorTO =list2.get(i);
				this.traineeList.add(new SelectItem(instructorTO.getInstructorId(),instructorTO.getInstructorName()));
			}

		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),
					"ViewDetailsMB", e.getMessage());
			this.message=e.getMessage();
		}

	}

	public void getList(ValueChangeEvent vce) 
	{

		try{

			this.message=null;
			this.choice=Integer.parseInt(vce.getNewValue().toString());
			if(choice==1)
			{
				this.traineeList=new ArrayList<SelectItem>();
				List<TraineeTO> list=new ArrayList<TraineeTO>();
				list=new RaagaWrapper().getAllTrainee();

				for(int i=0;i<list.size();i++)
				{
					TraineeTO traineeTO=new TraineeTO();
					traineeTO=list.get(i);
					this.traineeList.add(new SelectItem(traineeTO.getTraineeId(),traineeTO.getTraineeName()));
				}
			}

			if(choice==2)
			{
				this.instructorList=new ArrayList<SelectItem>();
				List<InstructorTO> list=new ArrayList<InstructorTO>();
				list=new RaagaWrapper().getAllInstructor();

				for(int i=0;i<list.size();i++)
				{
					InstructorTO into=new InstructorTO();
					into=list.get(i);
					this.instructorList.add(new SelectItem(into.getInstructorId(),into.getInstructorName()));
				}

			}
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(),
					"getList", e.getMessage());
			this.setMessage(e.getMessage());

		}
	}

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

}
