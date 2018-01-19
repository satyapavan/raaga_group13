package com.raaga.utilities;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.component.UIInput;



	public class DateOfBirthValidator implements Validator{
		
		
		@SuppressWarnings("deprecation")
		public void validate(FacesContext facesContext, UIComponent component,Object object) throws ValidatorException{
			
			if(!(component instanceof UIInput)){
				return;
			}
			Date dateOfBirth=(Date) object;
			Date today=new Date();
			 int temp=today.getYear()-5;

	         if( dateOfBirth.getYear()>temp){
			FacesMessage message=new FacesMessage();
			message.setSummary("Invalid entry");
			message.setDetail("Date should be less than 3 years from current date");
			throw new ValidatorException(message);
			}
		}
	}

