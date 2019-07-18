package com.msg.adm.business;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.msg.adm.business.data.User;
import com.msg.adm.business.data.UserDescription;
import com.msg.adm.common.exception.AdministrationEntityNotFoundException;
import com.msg.adm.common.exception.AdministrationValidationException;
import com.msg.adm.model.UserDescriptionEntity;
import com.msg.adm.model.UserEntity;
import com.msg.adm.persistence.UserBean;
import com.msg.adm.persistence.UserDescriptionBean;

/**
 * Service between Persistence and View.
 *
 */
@Stateless
public class UsersService {

	// @Inject
	// private transient Logger logger;

	@Inject
	private UserBean userBean;

	@Inject
	private UserDescriptionBean userDescriptionBean;

	/**
	 * Get all Users.
	 * 
	 * @return List of User Transfer Objects.
	 */
	public List<User> getAllUsers() {
		// logger.info("UserService --> getAllUsers()");
		List<UserEntity> userEntities = userBean.getAllUsers();

		return userEntities.stream().map(ue -> User.getUser(ue)).collect(Collectors.toList());
	}

	/**
	 * Get User by User Id.
	 * 
	 * @param user
	 * 
	 * @return User
	 * 
	 * @throws AdministrationEntityNotFoundException
	 */
	public User getUser(User user) throws AdministrationEntityNotFoundException {
		UserEntity userEntity = userBean.findById(user.getId());
		if (userEntity == null) {
			throw new AdministrationEntityNotFoundException("User wit id: " + user.getId() + " does not exist!");
		}

		return User.getUser(userEntity);
	}

	public User getUserById(Long id) throws AdministrationEntityNotFoundException {
		UserEntity userEntity = userBean.findById(id);
		if (userEntity == null) {
			throw new AdministrationEntityNotFoundException("User wit id: " + id + " does not exist!");
		}

		return User.getUser(userEntity);
	}

	public UserEntity getUserEntityById(Long id) throws AdministrationEntityNotFoundException {
		UserEntity userEntity = userBean.findById(id);
		if (userEntity == null) {
			throw new AdministrationEntityNotFoundException("UserEntity wit id: " + id + " does not exist!");
		}

		return userEntity;
	}

	/**
	 * Get User by username.
	 * 
	 * @param username
	 * 
	 * @throws AdministrationValidationException
	 */
	public User getUserByUsername(String username) throws AdministrationEntityNotFoundException {
		UserEntity userEntity = userBean.findByUsername(username);
		if (userEntity == null) {

			throw new AdministrationEntityNotFoundException("User with username: " + username + " does not exist!");
		}

		return User.getUser(userEntity);
	}

	/**
	 * Generate a password hash by using (username as) salt.
	 * 
	 * @param password, salt
	 * 
	 * @return hashed password
	 */
	public String passwordHash(String password, byte[] salt) {

		String generatedPassword = null;

		try {
			// Create MessageDigest instance for MD5
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			messageDigest.update(salt);
			// Get the hash's bytes
			byte[] bytes = messageDigest.digest(password.getBytes());
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return generatedPassword;

	}

	/**
	 * Adds a new User with it's UserDescription to the Database.
	 * 
	 * @param user
	 * 
	 * @throws AdministrationValidationException
	 */
	public void addUser(User user) throws AdministrationValidationException {
		// -- Validation
		if (!isUsernameUnique(user.getUsername())) {
			throw new AdministrationValidationException("Username already exists.");
		}

		// -- create User
		createUser(user);
	}

	/**
	 * Create {@link UserEntity} based on the {@link User} Transfer Object.
	 * 
	 * @param user
	 * 
	 * @return {@link UserEntity}
	 */
	private UserEntity createUser(User user) {
		UserEntity newUser = new UserEntity();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		newUser.setRole(user.getRole());
		UserDescriptionEntity userdescription = createUserDescription(user.getUserdescription());
		newUser.setUserdescription(userdescription);

		userBean.create(newUser);
		userdescription.setUser(newUser);

		return newUser;
	}

	/**
	 * Create {@link UserDescriptionEntity} based on the {@link UserDescription}
	 * Transfer Object.
	 * 
	 * @param userDescription
	 * 
	 * @return UserDescriptionEntity
	 */
	private UserDescriptionEntity createUserDescription(UserDescription userDescription) {
		UserDescriptionEntity userdescription = new UserDescriptionEntity();
		userdescription.setName(userDescription.getName());
		userdescription.setAge(userDescription.getAge());

		userDescriptionBean.create(userdescription);

		return userdescription;
	}

	/**
	 * Edit USer action
	 * 
	 * @param user
	 */
	public void editUser(User user) {

		UserEntity userEntity = null;
		try {
			userEntity = this.getUserEntityById(user.getId());
		} catch (AdministrationEntityNotFoundException e) {

		}
		userEntity.setId(user.getId());
		userEntity.setPassword(user.getPassword());
		userEntity.setRole(user.getRole());
		userEntity.getUserdescription().setName(user.getUserdescription().getName());
		userEntity.getUserdescription().setAge(user.getUserdescription().getAge());
		
		if( userBean.updateUser(userEntity) > 0 && userDescriptionBean.updateUserDescription(userEntity.getUserdescription()) > 0) {
			System.out.println("User data updated.");
		}
		

	}

	/**
	 * Verifies if the given username is unique among the existing usernames.
	 * 
	 * @param username
	 * 
	 * @return true or false
	 */
	private boolean isUsernameUnique(String username) {
		boolean isUnique = true;
		if (username == null || username.isEmpty()) {
			isUnique = false;
		}

		UserEntity userEntity = userBean.findByUsername(username);
		if (userEntity != null) {
			isUnique = false;
		}

		return isUnique;
	}

	/**
	 * Removes {@link UserEntity} from Database.
	 * 
	 * @param user
	 * 
	 * @throws AdministrationEntityNotFoundException
	 */
	public void deletUser(User user) throws AdministrationEntityNotFoundException {
		UserEntity userEntity = userBean.findById(user.getId());
		if (userEntity == null) {
			throw new AdministrationEntityNotFoundException("User wit id: " + user.getId() + " does not exist!");
		}

		// -- delete UserEntit.
		userBean.delete(userEntity);
	}

}
