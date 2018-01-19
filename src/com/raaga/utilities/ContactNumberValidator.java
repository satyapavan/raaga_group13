package com.raaga.utilities;


	import javax.faces.application.FacesMessage;
	import javax.faces.component.UIComponent;
	import javax.faces.component.UIInput;
	import javax.faces.context.FacesContext;
	import javax.faces.validator.Validator;
	import javax.faces.validator.ValidatorException;

	public class ContactNumberValidator implements Validator
	{
		public void validate(FacesContext facesContext, UIComponent component, Object object)throws ValidatorException{
			
			if(!(component instanceof UIInput)){
				return;
			}
			FacesMessage message =new FacesMessage();
			message.setSummary("Invalid Contact Number");
			message.setDetail("Contact Number should contain digits only");
			String phone=object.toString();
			if(phone!=null)
			
			{
				
				for(int i=0;i<phone.length();i++)
				{
					if(!(Character.isDigit(phone.charAt(i))))
					{
						message.setDetail("Contact Number can only be in digits");
						
						throw new ValidatorException(message);
					}
				
					else if(phone.length()!=10)
					{
					message.setDetail("Contact Number must be 10 digits long");
				
					throw new ValidatorException(message);
					}
				
				}
			}
			else
			{
				message.setDetail("Contact Number is mandatory");
				throw new ValidatorException(message);
			}

		}
	
	}

