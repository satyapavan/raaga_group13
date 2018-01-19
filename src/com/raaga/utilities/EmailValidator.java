package com.raaga.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator{
	
	public void validate(FacesContext facesContext, UIComponent uComponent, Object object)throws ValidatorException {
		
		 if(!(uComponent instanceof UIInput)){
			return;
		}
		else{
			FacesMessage message = new FacesMessage();
			message.setSummary("Invalid emailid");
			String email=object.toString();
			Integer siz=email.length();
			int countDafterA=0;
			int a=0;
			
			
			
			//check if the email containing @ and .
			if(email.contains("@") && email.contains("."))
			{
				
				//check if @ and . are first or last letter in emailId and if @ is immediately followed by '.'
				if(email.indexOf('@')==0 || email.indexOf('.')==0 || email.indexOf('@')==(siz-1)|| email.indexOf('.')==(siz-1) ||  email.indexOf('.')==(email.indexOf('@')+1) )
				{
				message.setDetail("Email Id must be of xyz@abc.com ");
				throw new ValidatorException(message);
				}
				//if the previous condition is not satisfied 
				else if(true)
				{
					for(int i=0;i<email.length();i++)
					{
						char temp=email.charAt(i);
						if(temp=='@')
						{
							a++;
						}
						if(email.charAt(i)>=33 &&email.charAt(i)<46 && email.charAt(i)==47)
						{
							message.setDetail("Email id must not contain any special characters other than '@' and '.'");
							throw new ValidatorException(message);
						}
					}
					if(!(a==1)){
						message.setDetail("Email Id should contain one occurance of '@' and maximum of two '.' after '@' ");
						throw new ValidatorException(message);
					}
					else if(true)
							
					{
								for(int k=email.lastIndexOf('@')+1;k<email.length();k++)
								{
									if(email.charAt(k)=='.')
									{
										countDafterA++;
									}
									if(!(Character.isLetter(email.charAt(k))||email.charAt(k)=='.'))
									{
										message.setDetail("Email id cannot contain any special characters or digits after @ except '.'");
										throw new ValidatorException(message);
									}
									
									
									
								}
								if(countDafterA>2)
								{
									message.setDetail("Email Id should contain maximum of two '.' after '@' ");
									throw new ValidatorException(message);
								}
								
					}
				}
						
			}
			else
			{
				message.setDetail("Email Id should contain atleast one @ and . ");
				throw new ValidatorException(message);
			}
					
			
		}
				
			
			
		
			
}
	}



