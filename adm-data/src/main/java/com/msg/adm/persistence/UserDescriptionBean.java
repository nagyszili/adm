package com.msg.adm.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.msg.adm.model.UserDescriptionEntity;
import com.msg.adm.model.UserEntity;

/**
 * EJB for UserDescriptionEntity persistence management.
 *
 */
@Stateless
public class UserDescriptionBean {

	@PersistenceContext(unitName = "edu.msg.Administration.postgres")
	private EntityManager entityManager;

	/**
	 * Creates a new UserDescriptionEntity.
	 * 
	 * @param UserDescriptionEntity
	 */
	public void create(UserDescriptionEntity userDescriptionEntity) {
		entityManager.persist(userDescriptionEntity);
	}

	/**
	 * Update UserDescriptionEntity
	 * 
	 * @param userDescriptionEntity
	 * @return the number of UserDescriptionEntitys updated
	 */
	public int updateUserDescription(UserDescriptionEntity userDescriptionEntity) {
		TypedQuery<UserDescriptionEntity> query = entityManager
				.createNamedQuery(UserDescriptionEntity.USER_DESCRIPTION_UPDATE, UserDescriptionEntity.class);

		query.setParameter("name", userDescriptionEntity.getName());
		query.setParameter("age", userDescriptionEntity.getAge());
		query.setParameter("id", userDescriptionEntity.getId());

		int executedQuery = query.executeUpdate();

		return executedQuery;

	}

}
