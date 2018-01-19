package com.raaga.manager;

import java.util.ArrayList;
import java.util.List;

import com.raaga.exceptions.NoDetailsAvailableException;

import com.raaga.service.TraineeService;
import com.raaga.to.TraineeTO;
import com.raaga.utilities.ErrorLogger;

public class TraineeManager {

	public List<TraineeTO> getAllTrainee() throws NoDetailsAvailableException,Exception {

		TraineeService traineeService=new TraineeService();
		List<TraineeTO>list1=new ArrayList<TraineeTO>();
		try{
			list1=traineeService.getAllTrainee();
			

			if(list1.isEmpty()){
				throw new NoDetailsAvailableException();
			}else{
				return list1;
			}
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

	public TraineeTO getTraineeDetails(Integer traineeId) throws Exception {
		TraineeService traineeService=new TraineeService();
		TraineeTO traineeTO=new TraineeTO();
		try{
			traineeTO=traineeService.getTraineeDetails(traineeId);
			return traineeTO;
		}catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getTraineeDetails", e.getMessage());
			throw e;
		}
	}


	public void saveTrainee(TraineeTO traineeTO) throws Exception{
		try{
		new TraineeService().saveTrainee(traineeTO);
		}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(), "saveTrainee", e.getMessage());
		throw e;
	}
	}
}
