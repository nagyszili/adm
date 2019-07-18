package com.msg.adm.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.msg.adm.model.UserDescriptionEntity;
import com.msg.adm.model.UserEntity;

/**
 * EJB for UserEntity persistence management.
 *
 */
@Stateless
public class UserBean {

	@PersistenceContext(unitName = "edu.msg.Administration.postgres")
	private EntityManager entityManager;

	/**
	 * Creates a new UserEntity.
	 * 
	 * @param userEntity
	 */
	public void create(UserEntity userEntity) {
		try {
			entityManager.persist(userEntity);
		} catch (Exception e) {

		}
	}

	/**
	 * Deletes the given UserEntity.
	 * 
	 * @param userEntity
	 */
	public void delete(UserEntity userEntity) {
		entityManager.remove(userEntity);
	}

	/**
	 * Finds the UserEntity by Id.
	 * 
	 * @param id
	 * 
	 * @return {@link UserEntity}
	 */
	public UserEntity findById(Long id) {
		return entityManager.find(UserEntity.class, id);
	}

	/**
	 * Finds the UserEntity by username.
	 * 
	 * @param username
	 * 
	 * @return {@link UserEntity} or null if not found.
	 */
	public UserEntity findByUsername(String username) {
		TypedQuery<UserEntity> query = entityManager.createNamedQuery(UserEntity.USER_FIND_BY_USERNAME,
				UserEntity.class);
		query.setParameter("username", username);

		List<UserEntity> users = query.getResultList();

		UserEntity userEntity = null;
		if (!users.isEmpty()) {
			userEntity = users.get(0);
		}

		return userEntity;
	}

	/**
	 * Return all UserEntities from the Database.
	 * 
	 * @return List of {@link UserEntity}
	 */
	public List<UserEntity> getAllUsers() {

		TypedQuery<UserEntity> query = entityManager.createNamedQuery("user.getAllUsers", UserEntity.class);

		return query.getResultList();
	}

	/**
	 * Update UserEntity
	 * 
	 * @param userEntity
	 * @return the number of UserEntitys updated
	 */
	public int updateUser(UserEntity userEntity) {
		TypedQuery<UserEntity> query = entityManager.createNamedQuery(UserEntity.USER_UPDATE, UserEntity.class);

		query.setParameter("role", userEntity.getRole());
		query.setParameter("password", userEntity.getPassword());
		query.setParameter("id", userEntity.getId());

		int executedQuery = query.executeUpdate();

		return executedQuery;

	}

}
