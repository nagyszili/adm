package com.msg.adm.gui.beans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.msg.adm.business.AbsenceService;
import com.msg.adm.business.UsersService;
import com.msg.adm.business.data.Absence;
import com.msg.adm.business.data.User;
import com.msg.adm.common.exception.AdministrationEntityNotFoundException;

@ManagedBean
@ViewScoped
public class AbsenceView {

	private Date startDate;
	private Date startHour;
	private Date endHour;
	private String reason;
	private Long replacementId;
	private Long userId;

	@Inject
	private AbsenceService absenceService;
	
	@Inject
	private UsersService usersService;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartHour() {
		return startHour;
	}

	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}

	public Date getEndHour() {
		return endHour;
	}

	public void setEndHour(Date endHour) {
		this.endHour = endHour;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getReplacementId() {
		return replacementId;
	}

	public void setReplacementId(Long replacementId) {
		this.replacementId = replacementId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void addAbsence() {
		
		User user = null;
		Absence absence = new Absence();
		String username = null;
		Long userid = null;
		try {
			HttpSession session = SessionUtils.getSession();
			username = ((SessionUtils) session).getUserName();
			userid = ((SessionUtils) session).getUserId();
//			user = usersService.getUserByUsername(username);
			user = usersService.getUserById(userid);
//			this.setUserId(user.getId());

		} catch (Exception e) {

			e.printStackTrace();
		}
		absence.setUserId(userid);
		absence.setStartDate(startDate);
		absence.setStartHour(startHour);
		absence.setEndHour(endHour);
		absence.setReason(reason);
		absence.setReplacementId(replacementId);
		absence.setCreatedDate(new Date());

		absenceService.createAbsence(absence);

	}

}
