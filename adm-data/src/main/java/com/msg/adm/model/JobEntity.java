package com.msg.adm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "job")
@SequenceGenerator(name = "job_id_seq", sequenceName = "JOB_ID_SEQ", allocationSize = 1, initialValue = 1)
@NamedQueries({
	@NamedQuery(name="job.update",query="UPDATE JobEntity j SET j.name=:name, j.description=:description WHERE j.id=:id")
})
public class JobEntity implements Serializable {

	private static final long serialVersionUID = 8492850849406666141L;
	
	public static final String JOB_UPDATE = "job.update";

	private Long id;
	private String name;
	private String description;

	@Id
	@GeneratedValue(generator = "job_id_seq", strategy = GenerationType.SEQUENCE)
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
