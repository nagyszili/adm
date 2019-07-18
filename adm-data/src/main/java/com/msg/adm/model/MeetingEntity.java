package com.msg.adm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "meeting")
@SequenceGenerator(name = "meeting_id_seq", sequenceName = "MEETING_ID_SEQ", allocationSize = 1, initialValue = 1)
public class MeetingEntity implements Serializable{

	private static final long serialVersionUID = 1267898031327748202L;
	
	private Long id;
	private String name;
	
	@Id
    @GeneratedValue(generator = "meeting_id_seq", strategy = GenerationType.SEQUENCE)
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
	

}
