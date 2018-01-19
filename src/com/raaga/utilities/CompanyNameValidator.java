package com.raaga.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class CompanyNameValidator implements Validator{

	public void validate(FacesContext context, UIComponent component, Object company)
			throws ValidatorException {
		if(context==null||component==null){
			return;
		}
		if(!(component instanceof UIInput)){
			return;
		}
		if(company==null){
			return;
		}
		String str=company.toString();
		FacesMessage message=new FacesMessage();
		message.setSummary("Invalid Company Name");
		message.setDetail("Only text allowed!!");
		
		
		for(int i=0;i<str.length();i++){
			if(!((str.charAt(i)>='A' && str.charAt(i)<='Z')|| (str.charAt(i)>='a' && str.charAt(i)<='z'))){
		     message.setDetail("Company name cannot have digits or special characters!!!");
		     throw new ValidatorException(message);
			}
		}
		
	}
	
	

}
