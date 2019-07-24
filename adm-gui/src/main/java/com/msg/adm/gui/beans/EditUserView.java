package com.msg.adm.gui.beans;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.msg.adm.business.UsersService;
import com.msg.adm.business.data.User;
import com.msg.adm.business.data.UserDescription;
import com.msg.adm.common.exception.AdministrationEntityNotFoundException;

@ManagedBean
@SessionScoped
public class EditUserView {

	private String password1;
	private String password2;
	private User user;

	@Inject
	private UsersService usersService;

	@PostConstruct
	private void init() {

		Map<String, String> userMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long userId = Long.parseLong(userMap.get("id"));
		System.out.println(userId);
		try {
			setUser(usersService.getUserById(userId));
		} catch (AdministrationEntityNotFoundException e) {

			addMessage("User wit id: " + userId + " does not exist!");
		}
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void editUser() {

		if (password1.equals(password2) && password1.length() > 4) {

			String hashPassword = usersService.passwordHash(password1, user.getUsername().getBytes());
			user.setPassword(hashPassword);

			usersService.editUser(user);
		}
	}

	/**
	 * Creates a new {@link FacesMessage} and adds it to the {@link FacesContext}.
	 * 
	 * @param summary
	 */
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
