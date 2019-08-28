package com.msg.adm.business;

import java.util.List;
import java.util.stream.Collectors;

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
		newAbsenceEntity.setId(absence.getId());
		newAbsenceEntity.setUserId(absence.getUserId());
		newAbsenceEntity.setStartDate(absence.getStartDate());
		newAbsenceEntity.setStartHour(absence.getStartHour());
		newAbsenceEntity.setEndHour(absence.getEndHour());
		newAbsenceEntity.setReason(absence.getReason());
		newAbsenceEntity.setReplacementId(absence.getReplacementId());
		newAbsenceEntity.setStatus(0);
		newAbsenceEntity.setCreatedDate(absence.getCreatedDate());
	
		
		absenceBean.create(newAbsenceEntity);
	}
	
	public List<Absence> getAbsenceByUserId(Long userId) {
		List<AbsenceEntity> absenceEntities = absenceBean.getAbsenceByUserId(userId);
		
		return absenceEntities.stream().map(ae -> Absence.getAbsence(ae)).collect(Collectors.toList());
	}
	
	public List<Absence> getAbsenceBySupervisorId(Long userId) {
		List<AbsenceEntity> absenceEntities = absenceBean.getAbsenceBySupervisorId(userId);
		
		return absenceEntities.stream().map(ae -> Absence.getAbsence(ae)).collect(Collectors.toList());
	}
}
