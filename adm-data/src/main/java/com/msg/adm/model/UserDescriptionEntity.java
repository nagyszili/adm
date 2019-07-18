package com.msg.adm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "userdescription")
@SequenceGenerator(name = "userdescription_id_seq", sequenceName = "USERDESCRIPTION_ID_SEQ", allocationSize = 1, initialValue = 1)
@NamedQueries({
    @NamedQuery(name = "userDescription.update", query = "UPDATE UserDescriptionEntity d SET d.name = :name, d.age = :age WHERE d.id = :id")
})
public class UserDescriptionEntity implements Serializable {

	private static final long serialVersionUID = -1413726073680260590L;
	public static final String USER_DESCRIPTION_UPDATE = "userDescription.update";
	
	private Long id;
	private String name;
	private int age;
	private UserEntity user;
	
	@Id
    @GeneratedValue(generator = "userdescription_id_seq", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@OneToOne(mappedBy = "userdescription")
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	

	
}
