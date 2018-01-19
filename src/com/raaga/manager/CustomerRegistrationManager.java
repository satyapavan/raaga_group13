package com.raaga.manager;

import java.util.ArrayList;
import java.util.List;

import com.raaga.exceptions.NoPendingCustomerRegistrationException;
import com.raaga.exceptions.DuplicateEmailIdException;
import com.raaga.exceptions.InvalidCustomerIdException;
import com.raaga.service.CustomerRegistrationService;
import com.raaga.to.CustomerRegistrationTO;
import com.raaga.utilities.ErrorLogger;

public class CustomerRegistrationManager {

	
	public CustomerRegistrationTO getCustomerDetails(Integer customerRegistrationId) throws InvalidCustomerIdException,Exception{
		try {
			CustomerRegistrationTO custTO=new CustomerRegistrationService().getCustomerDetail(customerRegistrationId);
			if(custTO==null){
				throw new InvalidCustomerIdException();
			}
			else{
				return custTO;
			}
		} 
		catch (InvalidCustomerIdException e) {
			ErrorLogger.logError(this.getClass().getName(), "getCustomerDetails", e
					.getMessage());
			throw e;
		}

		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getCustomerDetails", e
					.getMessage());
			throw e;
		}


	}
	public List<CustomerRegistrationTO> getPendingCustomers() throws NoPendingCustomerRegistrationException,Exception{
		try {
			List<CustomerRegistrationTO> list=new ArrayList<CustomerRegistrationTO>();
			list=new CustomerRegistrationService().getPendingCustomers();
			if(list==null){
				throw new NoPendingCustomerRegistrationException();
			}
			else{
				return list;
			}

		} catch (NoPendingCustomerRegistrationException e) {
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
	
	public Integer registerCustomer(CustomerRegistrationTO custoRegistrationTO) throws DuplicateEmailIdException,Exception
	{
		
		CustomerRegistrationService service =new CustomerRegistrationService();
		try{
			
			Boolean check=service.checkEmailId(custoRegistrationTO.getEmailId());
			
		if(check==true)	
		{
		Integer customerId=service.registerCustomer(custoRegistrationTO);
		
		return customerId;
		}
		else
		{
			throw new DuplicateEmailIdException();
		}
		}
		catch(DuplicateEmailIdException e)
		
		{	ErrorLogger.logError(this.getClass().getName(), "registerCustomer",
				e.getMessage());
			throw e;
		}
		
		catch(Exception e)
		{	ErrorLogger.logError(this.getClass().getName(), "registerCustomer",
				e.getMessage());
			throw e;
		}
	}
	

	
	
}
