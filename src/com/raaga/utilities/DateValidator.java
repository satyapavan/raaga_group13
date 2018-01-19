package com.raaga.utilities;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class DateValidator implements Validator{

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
	
		
			if(!(arg1 instanceof UIInput)){
			return;
			}
			FacesMessage message =new FacesMessage();
			message.setSummary("Invalid Date");
			message.setDetail("Date cannot be less than current date");
					Date d1=(Date)arg2;
		Date today=new Date();
		
			
				if(d1.before(today)){
					
				throw new ValidatorException(message);
			
				}
	}
	}
  

