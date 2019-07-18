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
@Table(name = "job")
@SequenceGenerator(name = "job_id_seq", sequenceName = "JOB_ID_SEQ", allocationSize = 1, initialValue = 1)
public class JobEntity implements Serializable {

	private static final long serialVersionUID = 8492850849406666141L;
	
	private Long id;
	private String name;
	private int salary;
	
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}


	
	
}
