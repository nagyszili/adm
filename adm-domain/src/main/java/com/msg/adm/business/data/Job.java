package com.msg.adm.business.data;

import com.msg.adm.model.JobEntity;

/**
 * Transfer Object for JobEnity.
 *
 */
public class Job extends AbstractPojo {

	private String name;
	private String description;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public static Job getJob(JobEntity jobEntity) {
		Job job = new Job();
		job.setId(jobEntity.getId());
		job.setName(jobEntity.getName());
		job.setDescription(jobEntity.getDescription());

		return job;
	}

}
