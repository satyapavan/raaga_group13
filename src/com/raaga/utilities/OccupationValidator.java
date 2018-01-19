package com.raaga.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class OccupationValidator implements Validator{

	public void validate(FacesContext context, UIComponent component, Object occupation)
			throws ValidatorException {
		
		if(!(component instanceof UIInput)){
			return;
		}
		if(occupation==null){
			return;
		}
		String ocuptn=(String)occupation;
		FacesMessage message=new FacesMessage();
		message.setSummary("occupation length is more than 20");
		message.setDetail("occupation length is more than 20!!");
		ocuptn=ocuptn.trim();
		if(ocuptn.length()>20){
			throw new ValidatorException(message);
		}
		for(int i=0;i<ocuptn.length();i++){
			if(!((ocuptn.charAt(i)>='a'&&ocuptn.charAt(i)<='z')|| (ocuptn.charAt(i)>='A'&&ocuptn.charAt(i)<='Z')||(ocuptn.charAt(i)==32))){
		     message.setDetail("Occupation should have only alphabets");
		     throw new ValidatorException(message);
			}
		
	}
	

}
}
