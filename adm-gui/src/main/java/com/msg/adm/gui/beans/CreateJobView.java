package com.msg.adm.gui.beans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.msg.adm.business.JobService;
import com.msg.adm.business.data.Job;

@ManagedBean
@ViewScoped
public class CreateJobView {

	private String name;
	private String description;

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

	public void createJob() {

		Job job = new Job();
		job.setName(name);
		job.setDescription(description);

		jobService.create(job);
		addMessage("Well done Job created!");
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("administration.xhtml");
		} catch (IOException e) {
			addMessage(e.toString());
		}
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
