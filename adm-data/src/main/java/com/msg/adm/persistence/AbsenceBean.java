package com.msg.adm.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.msg.adm.model.AbsenceEntity;
import com.msg.adm.model.UserEntity;

@Stateless
public class AbsenceBean {

	@PersistenceContext(unitName = "edu.msg.Administration.postgres")
	private EntityManager entityManager;

	public void create(AbsenceEntity absenceEntity) {
		try {

			entityManager.persist(absenceEntity);
		} catch (Exception e) {

			System.out.println(e.toString());
			addMessage(e.toString());

		}

	}
	
	public List<AbsenceEntity> getAbsenceByUserId(Long userId) {

		TypedQuery<AbsenceEntity> query = entityManager.createNamedQuery(AbsenceEntity.GETABSENCEBYUSERID, AbsenceEntity.class);
		query.setParameter("userid", userId);

		return query.getResultList();
		
	}
	
	public List<AbsenceEntity> getAbsenceBySupervisorId(Long userId) {

		TypedQuery<AbsenceEntity> query = entityManager.createNamedQuery(AbsenceEntity.GETABSENCEBYSUPERVISORID, AbsenceEntity.class);
		query.setParameter("userid", userId);

		return query.getResultList();
		
	}
	
	

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
