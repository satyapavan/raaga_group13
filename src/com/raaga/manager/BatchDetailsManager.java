package com.raaga.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.raaga.exceptions.InvalidBatchStartDateException;
import com.raaga.exceptions.NoBatchesAvailableException;
import com.raaga.exceptions.NoVenuesAvailableException;
import com.raaga.service.BatchDetailsService;
import com.raaga.to.BatchTO;
import com.raaga.to.ClassRoomTO;
import com.raaga.utilities.ErrorLogger;

public class BatchDetailsManager {

	public void editBatch(Integer batchId, Integer classRoomId,
			Date batchStartDate) throws InvalidBatchStartDateException,Exception {
		BatchDetailsService batchDetailsService=new BatchDetailsService();
		try{

			batchDetailsService.editBatch(batchId, classRoomId,batchStartDate);

		}
		catch(InvalidBatchStartDateException e){
			ErrorLogger.logError(this.getClass().getName(), "editBatch", e.getMessage());
			throw e;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "editBatch", e.getMessage());
			throw e;
		}
	}

	public List<Integer> getBatchIdDetails() throws NoBatchesAvailableException,Exception {
		List<Integer> list=new ArrayList<Integer>();
		BatchDetailsService batchDetailsService=new BatchDetailsService();
		try{
			list=batchDetailsService.getBatchIdDetails();
			if(list.size()==0){
				throw new NoBatchesAvailableException();
			}
			else{
				return list;
			}
		}
		catch(NoBatchesAvailableException e){
			ErrorLogger.logError(this.getClass().getName(), "getBatchIdDetails", e.getMessage());
			throw e;
		}
		catch(Exception e){
			ErrorLogger.logError(this.getClass().getName(), "getBatchIdDetails", e.getMessage());
			throw e;
		}
	}

	public List<ClassRoomTO> getClassRoomDetails() throws NoVenuesAvailableException,Exception{

		List<ClassRoomTO> list=new ArrayList<ClassRoomTO>();

		BatchDetailsService batchDetailsService=new BatchDetailsService();
		try{
			list=batchDetailsService.getClassRoomDetails();
			if(list.size()==0){
				throw new NoVenuesAvailableException();
			}
			else{
				return list;
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
	public List<BatchTO> viewBatchIdDetails(Integer batchId) throws NoBatchesAvailableException,Exception {
		List<BatchTO> list=new ArrayList<BatchTO>();

		BatchDetailsService batchDetailsService=new BatchDetailsService();
		try{
			list=batchDetailsService.viewBatchIdDetails(batchId);
			if(list.isEmpty()){
				throw new NoBatchesAvailableException();
			}
			else{
				return list;
			}
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

	
	public List<BatchTO> viewBatchDetails(Character batchType) throws Exception{

		BatchDetailsService batchDetailsService = new BatchDetailsService();
		List<BatchTO> list = new ArrayList<BatchTO>();
		try
		{
			list=batchDetailsService.viewBatchDetails(batchType);
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "viewBatchDetails", e.getMessage());
			throw e;
		}
		return list;
	}

}

