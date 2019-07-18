package com.msg.adm.business.data;

import com.msg.adm.model.UserDescriptionEntity;

/**
 * Transfer Object for UserDescriptionEntity.
 *
 */
public class UserDescription extends AbstractPojo {

	private String name;
	private int age;
	private User user;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public static UserDescription getUserDescriptionWithoutUser(UserDescriptionEntity userDescriptionEntity) {
		UserDescription userDescription = new UserDescription();
		userDescription.setId(userDescriptionEntity.getId());
		userDescription.setName(userDescriptionEntity.getName());
		userDescription.setAge(userDescriptionEntity.getAge());
		
		return userDescription;
	}
	
}
