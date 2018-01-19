package com.raaga.utilities;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class StatusConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str=null;
		if (arg2.equals('A')) {
			str = "Approved";
		} else if (arg2.equals('P')) {
			str = "Pending";
		} else if (arg2.equals('R')) {
			str = "Rejected";
		}
		return str;
	}

}
