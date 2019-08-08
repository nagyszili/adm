package com.msg.adm.gui.beans;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;


import com.msg.adm.business.UsersService;
import com.msg.adm.business.data.User;
import com.msg.adm.common.exception.AdministrationEntityNotFoundException;
import com.msg.adm.common.exception.AdministrationValidationException;

/**
 * View class for the UserLogin Dialog.
 *
 */
@ManagedBean
public class UserLoginView {

	private String username;
	private String password;

	@Inject
	private UsersService usersService;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Login event.
	 *
	 * 
	 */
	public void login(ActionEvent event) throws IOException {

		RequestContext requestContext = RequestContext.getCurrentInstance();
		FacesMessage message = null;

		boolean loggedIn = false;

		String hashPassword = usersService.passwordHash(password, username.getBytes());
		User user = null;
		
		try {
			user = usersService.getUserByUsername(username);
		} catch (AdministrationEntityNotFoundException e) {
			System.out.println("User with username: " + username + " does not exist!");
		}
		
//		ExternalContext context = null;
		
		if (username != null && username.equals(user.getUsername()) && password != null && hashPassword.equals(user.getPassword())) {
			loggedIn = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
			
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", username);
			session.setAttribute("userid", user.getId());
//			return "admin";
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("content/administration/administration.xhtml");
			
//			context = FacesContext.getCurrentInstance().getExternalContext();
//			context.redirect("content/administration/administration.xhtml");
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		requestContext.addCallbackParam("loggedIn", loggedIn);

	}

	

}
