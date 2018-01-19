package com.raaga.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class MobileNumberValidator implements Validator{

	public void validate(FacesContext context, UIComponent component, Object number)
	throws ValidatorException {
		
		if(!(component instanceof UIInput)){
			return;
		}
		if(number==null){
			return;
		}
		Long num = (Long) number;
		
		String contactNo=num+"";
		FacesMessage message=new FacesMessage();
		message.setSummary("Invalid PhoneNumber length");
		message.setDetail("Invalid number of characters");
		contactNo=contactNo.trim();

		if(contactNo.length()!=10){
			throw new ValidatorException(message);
		}
		for(int i=0;i<contactNo.length();i++){
			if(!(contactNo.charAt(i)>='0'&&contactNo.charAt(i)<='9')){
				message.setDetail("Number cannot have specialcharacters!!!");
				throw new ValidatorException(message);
			}
		}

	}


}
