package com.msg.adm.gui.beans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class AbsenceView {

	private Date startDate;
	private Date startHour;
	private Date endHour;
	private String reason;
	private Integer replacement;

	public AbsenceView(Date startDate, Date startHour, Date endHour, String reason, Integer replacement) {
		super();
		this.startDate = startDate;
		this.startHour = startHour;
		this.endHour = endHour;
		this.reason = reason;
		this.replacement = replacement;
	}

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

	public Integer getReplacement() {
		return replacement;
	}

	public void setReplacement(Integer replacement) {
		this.replacement = replacement;
	}

	public void addAbsence() {

	}

}
