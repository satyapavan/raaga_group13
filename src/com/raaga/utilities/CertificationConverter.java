package com.raaga.utilities;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public class CertificationConverter implements Converter 
{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Character abc=(Character)arg2;
		String unitname=null;
		if(abc=='Y')
		{
			unitname= "Yes";
		}
			
		else if(abc=='N')
		{
			unitname= "No";
		}
			
		
		return unitname;
	}
		
	}


