package com.msg.adm.gui.beans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.msg.adm.business.AbsenceService;
import com.msg.adm.business.data.Absence;

@ManagedBean
@ViewScoped
public class AbsenceView {

	private Date startDate;
	private Date startHour;
	private Date endHour;
	private String reason;
	private Long replacementId;
	
	@Inject
	private AbsenceService absenceService;


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
	
	public void addAbsence() {
		Absence absence = new Absence();
		absence.setStartDate(startDate);
		absence.setStartHour(startHour);
		absence.setEndHour(endHour);
		absence.setReason(reason);
		absence.setReplacementId(replacementId);
		absence.setCreatedDate(new Date());
		
		absenceService.createAbsence(absence);
		

	}

}
