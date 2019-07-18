package com.msg.adm.business.data;

import com.msg.adm.model.JobEntity;

/**
 * Transfer Object for JobEnity.
 *
 */
public class Job extends AbstractPojo {

	private String name;
	private int salary;
	
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
	 * @return the salary
	 */
	public int getSalary() {
		return salary;
	}
	
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public static Job getJob(JobEntity jobEntity ) {
		Job job = new Job();
		job.setId(jobEntity.getId());
		job.setName(jobEntity.getName());
		job.setSalary(jobEntity.getSalary());
		
		return job;
	}
	
	
}
