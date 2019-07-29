package com.msg.adm.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.msg.adm.model.JobEntity;

@Stateless
public class JobBean {

	@PersistenceContext(unitName = "edu.msg.Administration.postgres")
	private EntityManager entityManager;

	public void create(JobEntity jobEntity) {
		entityManager.persist(jobEntity);

	}

}
