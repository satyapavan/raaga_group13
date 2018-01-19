package com.raaga.utilities;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class StartDateValidator implements Validator{

	@SuppressWarnings("deprecation")
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		 
		 if(!(arg1 instanceof UIInput)){
			 return;
		 }
		Date d=(Date)arg2;
		if(d.getDay()==0||d.getDay()==6){
			FacesMessage f=new FacesMessage();
			f.setDetail("Date cannot be a saturday or sunday");
			f.setSummary("Invalid date");
			throw new ValidatorException(f);
		}
	}

}
