package com.raaga.bean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.raaga.exceptions.InvalidUsernameException;
import com.raaga.exceptions.WrongPasswordException;
import com.raaga.to.LoginTO;
import com.raaga.utilities.ErrorLogger;
import com.raaga.wrapper.RaagaWrapper;

public class LoginMB  {
	private String userId;
	private String password;
	private Character role;
	private String message;



	public String validateLogin()
	{	
		LoginTO loginTO=new LoginTO();
		loginTO.setUserId(userId);
		loginTO.setPassword(password);
		try
		{	

			this.message=null;

			this.role=new RaagaWrapper().validateLogin(loginTO);

			HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);   
			session.setAttribute("userId",this.userId); 
			session.setAttribute("role",this.role);
			return "success";
		}
		catch (InvalidUsernameException e) {
			ErrorLogger.logError(this.getClass().getName(), "validateLogin", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
		catch (WrongPasswordException e) {
			ErrorLogger.logError(this.getClass().getName(), "validateLogin", e.getMessage());
			this.setMessage(e.getMessage());
			return "failure";
		}
		catch(Exception e)
		{	ErrorLogger.logError(this.getClass().getName(), "validateLogin", e.getMessage());
		this.setMessage(e.getMessage());
		return "failure";
		}
	}

	public String logout() 

	{	FacesContext ctx = FacesContext.getCurrentInstance();  
	ExternalContext etx = ctx.getExternalContext();  
	HttpSession session = (HttpSession)etx.getSession(false);    
	session.invalidate();  
	return "logout";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Character getRole() {
		return role;
	}
	public void setRole(Character role) {
		this.role = role;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
