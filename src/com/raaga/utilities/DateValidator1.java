package com.raaga.utilities;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DateValidator1 implements Validator{

	public void validate(FacesContext facesContext, UIComponent component, Object date)throws ValidatorException {
		
		if(!(component instanceof UIInput)){
			return;
		}
        Date enterDate=(Date)date;
		Date today=new Date();
		if(enterDate.after(today)){
			FacesMessage message=new FacesMessage();
			message.setDetail("Date cannot be greater than today's date");
			throw new ValidatorException(message);
		}
	}

}