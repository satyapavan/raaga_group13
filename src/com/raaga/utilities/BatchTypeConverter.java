package com.raaga.utilities;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class BatchTypeConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		Character arg=arg2.toString().charAt(0);


		return arg;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		Character batchType=arg2.toString().charAt(0);
		if(batchType=='D')
			return "WeekDay";
		else
			if(batchType=='E')
				return "WeekEnd";
			else


				return null;
	}

}
