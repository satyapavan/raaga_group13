package com.raaga.bean;



import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.raaga.to.CourseApplicationTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class ViewApplicationStatusMB {
	private List<CourseApplicationTO> listOfCourses;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<CourseApplicationTO> getListOfCourses() {
		return listOfCourses;
	}

	public void setListOfCourses(List<CourseApplicationTO> listOfCourses) {
		this.listOfCourses = listOfCourses;
	}

	public  ViewApplicationStatusMB(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession)externalContext.getSession(true);
		String user=(String) session.getAttribute("userId");
		RaagaWrapper w=new RaagaWrapper();
		this.message=null;
		this.listOfCourses=new ArrayList<CourseApplicationTO>();
		try {

			this.listOfCourses=w.viewApplicationStatus(user);
			if(this.listOfCourses.isEmpty()){
				this.message="Sorry!!! No Registration available with us";
			}
		} 
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
                    "ViewApplicationStatusMB", e.getMessage());

			this.message=e.getMessage();
			
		}
	}


}
