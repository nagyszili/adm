package com.msg.adm.gui.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.msg.adm.business.AbsenceService;
import com.msg.adm.business.UsersService;
import com.msg.adm.business.data.Absence;
import com.msg.adm.business.data.User;

@ManagedBean
@ViewScoped
public class SupervisorAbsenceView {

	private Long userId;
	private List<Absence> absenceList = new ArrayList<Absence>();

	@Inject
	private AbsenceService absenceService;

	@Inject
	private UsersService usersService;

	@PostConstruct
	public void init() {

		User user = null;

		FacesContext context = FacesContext.getCurrentInstance();
		String uname = (String) context.getExternalContext().getApplicationMap().get("username");

		try {

			user = usersService.getUserByUsername(uname);
			this.setUserId(user.getId());

		} catch (Exception e) {

			e.printStackTrace();
		}

		absenceList = absenceService.getAbsenceBySupervisorId(userId);

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Absence> getAbsenceList() {
		return absenceList;
	}

	public void setAbsenceList(List<Absence> absenceList) {
		this.absenceList = absenceList;
	}

}
