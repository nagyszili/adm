package com.msg.adm.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.msg.adm.model.JobEntity;

@Stateless
public class JobBean {

	@PersistenceContext(unitName = "edu.msg.Administration.postgres")
	private EntityManager entityManager;

	public void create(JobEntity jobEntity) {
		entityManager.persist(jobEntity);

	}

	public JobEntity findByJobId(Long id) {
		return entityManager.find(JobEntity.class, id);
	}
		
	public int update(JobEntity jobEntity) {
		TypedQuery<JobEntity> query = entityManager.createNamedQuery(JobEntity.JOB_UPDATE,JobEntity.class);
		
		query.setParameter("name", jobEntity.getName());
		query.setParameter("description", jobEntity.getDescription());
		query.setParameter("id", jobEntity.getId());
		
		int exeutedQueries = query.executeUpdate();
		
		return exeutedQueries;
	}
}
