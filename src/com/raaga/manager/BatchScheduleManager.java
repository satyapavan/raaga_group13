package com.raaga.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.raaga.exceptions.InstructorNotFreeException;

import com.raaga.exceptions.InvalidBatchStartDateException;
import com.raaga.exceptions.NoDetailsAvailableException;
import com.raaga.exceptions.NoVenuesAvailableException;
import com.raaga.service.BatchScheduleService;
import com.raaga.service.InstructorService;
import com.raaga.to.BatchTO;
import com.raaga.utilities.ErrorLogger;

public class BatchScheduleManager {

	
	public Map<Integer, String> getFreeVenues() throws NoVenuesAvailableException,Exception {
		try {	
			Map<Integer, String> map = new BatchScheduleService()
					.getFreeVenues();
			if (map.isEmpty()) {
				throw new NoVenuesAvailableException();
			}
			return map;

		} 
		catch(NoVenuesAvailableException e1)
		{
			ErrorLogger.logError(this.getClass().getName(), "getFreeVenues", e1
					.getMessage());
			throw e1;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getFreeVenues", e
					.getMessage());
			throw e;
		}
	}

	public Integer scheduleBatch(BatchTO to) throws InvalidBatchStartDateException,InstructorNotFreeException,Exception {
		try {
			Calendar date = Calendar.getInstance();
			date.setTime(to.getStartDate());
			int day = date.get(Calendar.DAY_OF_WEEK);

			if (to.getBatchType().equals('D')) {
				if (day == 1 || day == 7) {
					throw new InvalidBatchStartDateException();
				}
			}
			if (to.getBatchType().equals('E')) {
				if (day >= 2 && day <= 6) {
					throw new InvalidBatchStartDateException();
				}
			}


			if (new InstructorService().checkInstructor(to.getInstructorId(),
					to.getStartDate())) {
				Integer batchId = new BatchScheduleService().scheduleBatch(to);
				return batchId;

			} else {
				throw new InstructorNotFreeException();
			}

		} 
		catch (InvalidBatchStartDateException e1) {
			ErrorLogger.logError(this.getClass().getName(), "scheduleBatch", e1
					.getMessage());
			throw e1;
		}
		catch(InstructorNotFreeException e1)
		{
			ErrorLogger.logError(this.getClass().getName(), "scheduleBatch", e1
					.getMessage());
			throw e1;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "scheduleBatch", e
					.getMessage());
			throw e;
		}
	}

	
	
	public Set<Integer> getBatchYears() throws NoDetailsAvailableException,Exception {
		
		Set<Integer> set =new TreeSet<Integer>();
		BatchScheduleService batchScheduleService=new BatchScheduleService();

		try {
			set=batchScheduleService.getBatchYears();
			if(set.isEmpty()){
				throw new NoDetailsAvailableException();
			}
		} 
		catch (NoDetailsAvailableException e1) {
			ErrorLogger.logError(this.getClass().getName(), "getBatchYears", e1
					.getMessage());
			throw e1;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getBatchYears", e
					.getMessage());
			throw e;
		}

		return set;
	}

	public List<BatchTO> getBatchReport(Integer month, Integer year) throws NoDetailsAvailableException,Exception {

		List<BatchTO> list=new ArrayList<BatchTO>();

		try {
			list=new BatchScheduleService().getBatchReport(month,year);
			
			if(list.isEmpty()){
				
				throw new NoDetailsAvailableException();
			}
		} 
		catch (NoDetailsAvailableException e) {
			ErrorLogger.logError(this.getClass().getName(), "getBatchReport", e
					.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getBatchReport", e
					.getMessage());
			throw e;
		}
		return list;

	}

	public void confirmBatchClosure(BatchTO batchTO) throws Exception
	  {
		  try
		  {
		  new BatchScheduleService().confirmBatchClosure(batchTO);
		  }
		  catch (Exception e) {
			
			  ErrorLogger.logError(this.getClass().getName(),
	                  "confirmBatchClosure", e .getMessage());

			  throw e;
		}
	  }
	  public List<BatchTO> getBatchesForClosure() throws NoDetailsAvailableException,Exception
	  {
		  List<BatchTO> list=new ArrayList<BatchTO>();
		  List<BatchTO> l=new ArrayList<BatchTO>();
		  try
		  {
		  list=new BatchScheduleService().getAllBatches();
		  if(list.isEmpty())
		  {
			  throw new NoDetailsAvailableException();
		  }
		  else
		  {
			  for(BatchTO bto:list)
			  {
				  if(bto.getBatchClosureStatus()=='C')
				  {
					  l.add(bto);
				  }
			  }
		  }
		  }
		  catch(NoDetailsAvailableException e)
		  {
			  ErrorLogger.logError(this.getClass().getName(),
	                  "getBatchesForClosure", e .getMessage());

			  throw e;  
		  }
		  
		  catch (Exception e) {
			
			  ErrorLogger.logError(this.getClass().getName(),
	                  "getBatchesForClosure", e .getMessage());

			  throw e;
		}
		  return l;
		  
		 
	  }
	  



}
