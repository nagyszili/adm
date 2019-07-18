package com.msg.adm.business.data;

import java.util.List;
import java.util.stream.Collectors;

import com.msg.adm.model.UserEntity;
import com.msg.adm.model.enums.EnumRoles;

/**
 * Transfer Object for UserEntity.
 *
 */
public class User extends AbstractPojo {

	private String username;
	private EnumRoles role;
	private String password;
	private List<AppliedJob> jobs;
	private UserDescription userdescription;
	private List<Meeting> meetings;
	
	/**
	 * Standard Constructor.
	 * 
	 */
	public User() { }
	
	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public User(Long id) {
		this.setId(id);
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the role
	 */
	public EnumRoles getRole() {
		return role;
	}
	
	/**
	 * @param role the role to set
	 */
	public void setRole(EnumRoles role) {
		this.role = role;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the jobs
	 */
	public List<AppliedJob> getJobs() {
		return jobs;
	}
	
	/**
	 * @param jobs the jobs to set
	 */
	public void setJobs(List<AppliedJob> jobs) {
		this.jobs = jobs;
	}
	
	/**
	 * @return the userdescription
	 */
	public UserDescription getUserdescription() {
		return userdescription;
	}
	
	/**
	 * @param userdescription the userdescription to set
	 */
	public void setUserdescription(UserDescription userdescription) {
		this.userdescription = userdescription;
	}
	
	/**
	 * @return the meetings
	 */
	public List<Meeting> getMeetings() {
		return meetings;
		
	}
	
	/**
	 * @param meetings the meetings to set
	 */
	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}
	

	/**
	 * Returns an {@link User} Transfer Object from the {@link UserEntity}.
	 * 
	 * @return User
	 */
	
	public static User getUser(UserEntity userEntity) {
		User user = new User();
		user.setId(userEntity.getId());
		user.setUsername(userEntity.getUsername());
		user.setPassword(userEntity.getPassword());
		user.setRole(userEntity.getRole());
		user.setUserdescription( UserDescription.getUserDescriptionWithoutUser(userEntity.getUserdescription()) );
		user.setJobs(userEntity.getJobs().stream().map(j -> AppliedJob.getAppliedJob(j)).collect(Collectors.toList()));
		user.setMeetings(userEntity.getMeetings().stream().map(m -> Meeting.getMeeting(m)).collect(Collectors.toList()));
		
		return user;
	}
	
	
	
}
