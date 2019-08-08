package com.msg.adm.gui.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.msg.adm.business.UsersService;
import com.msg.adm.business.data.User;
import com.msg.adm.business.data.UserDescription;
import com.msg.adm.common.exception.AdministrationEntityNotFoundException;
import com.msg.adm.common.exception.AdministrationValidationException;
import com.msg.adm.model.UserEntity;
import com.msg.adm.model.enums.EnumRoles;
import com.msg.adm.persistence.UserBean;

@ManagedBean
@ViewScoped
public class CreateUserView {

	private String username;
	private String password;
	private String password2;
	private String role;
	private String name;
	private Integer age;

	private User supervisor;
	private List<User> users;

	@Inject
	private UsersService usersService;
	
	@PostConstruct
    private void init() {
		this.setAllUser();
		
	}
	
	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public User getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setAllUser() {
		this.setUsers(usersService.getAllUsers());
	}
	
    public User getSelectedUser(Long id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (User user : users){
            if (id.equals(user.getId())){
                return user;
            }
        }
        return null;
    }

	/**
	 * Create a new User.
	 * 
	 */
	public void createUser(ActionEvent event) throws IOException {

		RequestContext requestContext = RequestContext.getCurrentInstance();
		FacesMessage message = null;

		boolean created = false;

		if (password.equals(password2) && password.length() > 4) {
			User user = new User();
			UserDescription userdescription = new UserDescription();
			userdescription.setName(name);
			userdescription.setAge(age);
			user.setUserdescription(userdescription);
			user.setUsername(username);
			String hashPassword = usersService.passwordHash(password, username.getBytes());
			user.setPassword(hashPassword);
			user.setRole(EnumRoles.valueOf(role));
			user.setSupervisorId(supervisor.getId());

			try {
				usersService.addUser(user);
				created = true;
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("../../content/administration/administration.xhtml");

				addMessage("Well done User created!");

			} catch (AdministrationValidationException e) {
				addMessage(e.getMessage());
			}

		} else {
			addMessage("Wrong password");
		}

		RequestContext.getCurrentInstance().update("administrationFormId:usersTableId");
		requestContext.addCallbackParam("created", created);

	}

	public void back() {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("../../content/administration/administration.xhtml");

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
