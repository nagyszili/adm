package com.msg.adm.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.msg.adm.business.data.Absence;
import com.msg.adm.model.AbsenceEntity;
import com.msg.adm.persistence.AbsenceBean;

@Stateless
public class AbsenceService {


	@Inject
	private AbsenceBean absenceBean;
	
	public void createAbsence(Absence absence) {
		AbsenceEntity newAbsenceEntity = new AbsenceEntity();
		newAbsenceEntity.setUserId(absence.getUserId());
		newAbsenceEntity.setStartDate(absence.getStartDate());
		newAbsenceEntity.setStartHour(absence.getStartHour());
		newAbsenceEntity.setEndHour(absence.getEndHour());
		newAbsenceEntity.setReason(absence.getReason());
		newAbsenceEntity.setReplacementId(absence.getReplacementId());
		newAbsenceEntity.setStatus(0);
	
		
		absenceBean.create(newAbsenceEntity);
	}
}
