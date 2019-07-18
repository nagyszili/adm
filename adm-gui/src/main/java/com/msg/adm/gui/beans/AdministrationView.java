package com.msg.adm.gui.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.msg.adm.business.IUserService;
import com.msg.adm.business.UsersService;
import com.msg.adm.business.data.User;
import com.msg.adm.business.data.UserDescription;
import com.msg.adm.common.exception.AdministrationValidationException;
import com.msg.adm.model.enums.EnumRoles;

/**
 * View class for the Administration Dialog.
 *
 */
@ManagedBean(name = "administrationView")
@SessionScoped
public class AdministrationView {

	private Long userId;

	@Inject
	private UsersService usersService;

	private List<User> usersList = new ArrayList<User>();

	@PostConstruct
	public void init() {
		usersList = usersService.getAllUsers();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the usersList
	 */
	public List<User> getUsersList() {
		return usersList;
	}

	/**
	 * @param usersList the usersList to set
	 */
	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	/**
	 * Action for create new User.
	 * 
	 */
	public void addUserAction() {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("../../content/user/createUser.xhtml");
		} catch (IOException e) {
			addMessage(e.getMessage());
		}
	}

	/**
	 * Action for delete User.
	 * 
	 */
	public void deleteUserAction() {
		User user = new User();
		user.setId(0L);

	}

	public void editUserAction(Long userid) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			this.setUserId(userid);
			context.getExternalContext().redirect("../../content/user/editUser.xhtml?id=" + userid.toString());

		} catch (IOException e) {
			addMessage(e.getMessage());
		}
	}

	public void absenceAction() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("../../content/administration/createAbsence.xhtml");

		} catch (IOException e) {
			addMessage(e.getMessage());
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
