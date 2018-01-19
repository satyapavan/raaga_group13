package com.raaga.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class NameValidator implements Validator{

	public void validate(FacesContext facesContext, UIComponent uComponent, Object object)throws ValidatorException {
		
		 if(!(uComponent instanceof UIInput)){
			return;
		}
		else{
			FacesMessage message = new FacesMessage();
			message.setSummary("Invalid name");
			String name=object.toString();
			
			int count=0;
			
			if(name.contains(" "))
			{
				for(int k=0;k<name.length();k++)
				{
					if(name.charAt(k)==' ')
					{
						count++;
					}
					if(count==name.length())
					{
						message.setDetail("Name containing only spaces is not allowed");

						throw new ValidatorException(message);
					}
				}
			
			}
			else
			if(!(name.length()>=3 && name.length()<=10))
			{
				message.setDetail("Name should be atleast 3 characters and atmost 10 characters long");
				throw new ValidatorException(message);
			}
			else
			{
				for(int i=0;i<name.length();i++)
			
				{
				 
				if(name.charAt(i)>=33 &&name.charAt(i)<= 47)
				{
					message.setDetail("Name can't have special characters");
					throw new ValidatorException(message);
				}


				else if(!(Character.isLetter(name.charAt(i)) || name.charAt(i)==' '))
				{
					message.setDetail("Name should  contain only alphabets and spaces");
					throw new ValidatorException(message);
				}
				
				}
			}
			   
			
			


		}
	}


}
