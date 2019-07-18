package com.msg.adm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "appliedjob")
@SequenceGenerator(name = "appliedjob_id_seq", sequenceName = "APPLIEDJOB_ID_SEQ", allocationSize = 1, initialValue = 1)
public class AppliedJobEntity implements Serializable {

	private static final long serialVersionUID = -1207281945491003626L;
	
	private Long id;
	private UserEntity user;
	private JobEntity job;
	
	@Id
    @GeneratedValue(generator = "appliedjob_id_seq", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

//	@ManyToOne
//	@JoinColumn(name = "einflussElementTam_id", referencedColumnName = "id", nullable = false)
	@ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
	public JobEntity getJob() {
		return job;
	}

	public void setJob(JobEntity job) {
		this.job = job;
	}
	

	
	
}
