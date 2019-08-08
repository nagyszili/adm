package com.msg.adm.gui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.msg.adm.business.JobService;
import com.msg.adm.business.data.Job;

@ManagedBean(name = "editJobView")
@ViewScoped
public class EditJobView {

	private String name;
	private String description;
	private Long id;

	@Inject
	private JobService jobService;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void editJob() {
		Job job = new Job();

		job.setId(id);
		job.setName(name);
		job.setDescription(description);

		jobService.editJob(job);

	}

}
