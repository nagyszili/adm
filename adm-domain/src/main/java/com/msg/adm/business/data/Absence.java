package com.msg.adm.business.data;

import java.util.Date;

import com.msg.adm.model.AbsenceEntity;

public class Absence extends AbstractPojo {

	private Long userId;
	private Date createdDate;
	private Date startDate;
	private Date startHour;
	private Date endHour;
	private String reason;
	private Long replacementId;
	private int status;

	public Absence() {
		this.createdDate = new Date();
	}

	public Absence(Long id) {
		this.setId(id);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public Long getReplacementId() {
		return replacementId;
	}

	public void setReplacementId(Long replacementId) {
		this.replacementId = replacementId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static Absence getAbsence(AbsenceEntity absenceEntity) {

		Absence absence = new Absence();
		
		absence.setId(absenceEntity.getId());
		absence.setUserId(absenceEntity.getUserId());
		absence.setCreatedDate(absenceEntity.getCreatedDate());
		absence.setStartDate(absenceEntity.getStartDate());
		absence.setStartHour(absenceEntity.getStartHour());
		absence.setEndHour(absenceEntity.getEndHour());
		absence.setReplacementId(absenceEntity.getReplacementId());
		absence.setReason(absenceEntity.getReason());
		absence.setStatus(absenceEntity.getStatus());

		return absence;
	}

}
