package com.raaga.utilities;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class BatchStartDateValidator implements Validator {
	

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		

		if (!(arg1 instanceof UIInput)) {
			return;
		}
		Date date=(Date) arg2;
		Date today=new Date();
		if(date.before(today)){
			FacesMessage message = new FacesMessage(
					"Start date should be after today",
					"Start date should be after today");
			throw new ValidatorException(message);
		}
	}
}
