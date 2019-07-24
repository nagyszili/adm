package com.msg.adm.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.msg.adm.model.AbsenceEntity;


@Stateless
public class AbsenceBean {

	@PersistenceContext(unitName = "edu.msg.Administration.postgres")
	private EntityManager entityManager;
	
	/**
	 * Creates a new AbsenceEntity.
	 * 
	 * @param userEntity
	 */
	public void create(AbsenceEntity absenceEntity) {
		try {
			entityManager.persist(absenceEntity);
		} catch (Exception e) {
			
			System.out.println(e.toString());
			addMessage(e.toString());
			
		}
		
//		entityManager.persist(absenceEntity);
	}
	
	/**
	 * Deletes the given AbsenceEntity.
	 * 
	 * @param userEntity
	 */
	public void delete(AbsenceEntity absenceEntity) {
		entityManager.remove(absenceEntity);
	}
	

//	public List<AbsenceEntity> getAllUsers() {
//
//		TypedQuery<AbsenceEntity> query = entityManager.createNamedQuery("user.getAllUsers", AbsenceEntity.class);
//
//		return query.getResultList();
//	}

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
