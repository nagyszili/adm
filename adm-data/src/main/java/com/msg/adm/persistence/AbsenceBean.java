package com.msg.adm.persistence;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.msg.adm.model.AbsenceEntity;

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

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
