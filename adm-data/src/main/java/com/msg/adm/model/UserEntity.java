package com.msg.adm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.msg.adm.model.enums.EnumRoles;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_id_seq", sequenceName = "USER_ID_SEQ", allocationSize = 1, initialValue = 1)
@NamedQueries({ 
    @NamedQuery(name = "user.getAllUsers", query = "SELECT u FROM UserEntity u ORDER BY u.username"),
    @NamedQuery(name = "user.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username"),
    @NamedQuery(name = "user.update", query = "UPDATE UserEntity u SET u.role = :role, u.password = :password  WHERE u.id = :id")
})
public class UserEntity implements Serializable { 

	public static final String USER_FIND_BY_USERNAME = "user.findByUsername";
	public static final String GETALLUSERSQUERY = "user.getAllUsers";
	public static final String USER_UPDATE = "user.update";

	private static final long serialVersionUID = -1108205893692379782L;
    private Long id;
	private String username;
	private EnumRoles role;
	private String password;
	private List<AppliedJobEntity> jobs = new ArrayList<AppliedJobEntity>();
	private UserDescriptionEntity userdescription;
	private List<MeetingEntity> meetings = new ArrayList<MeetingEntity>();
	
	@Id
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Enumerated(EnumType.STRING)
	public EnumRoles getRole() {
		return role;
	}
	
	public void setRole(EnumRoles role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	public List<AppliedJobEntity> getJobs() {
		return jobs;
	}

	public void setJobs(List<AppliedJobEntity> jobs) {
		this.jobs = jobs;
	}

	@OneToOne(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "userdescription_id", referencedColumnName = "id")
	public UserDescriptionEntity getUserdescription() {
		return userdescription;
	}

	public void setUserdescription(UserDescriptionEntity userdescription) {
		this.userdescription = userdescription;
	}

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usermeeting", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "meeting_id") })
	public List<MeetingEntity> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<MeetingEntity> meetings) {
		this.meetings = meetings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", role=" + role + ", password=" + password
				+ ", jobs=" + jobs + ", userdescription=" + userdescription + ", meetings=" + meetings + "]";
	}

	
}
