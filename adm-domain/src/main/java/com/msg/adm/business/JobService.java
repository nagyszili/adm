package com.msg.adm.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.msg.adm.business.data.Job;
import com.msg.adm.common.exception.AdministrationEntityNotFoundException;
import com.msg.adm.model.JobEntity;
import com.msg.adm.persistence.JobBean;

@Stateless
public class JobService {

	@Inject
	private JobBean jobBean;

	public void create(Job job) {
		JobEntity jobEntity = new JobEntity();
		jobEntity.setName(job.getName());
		jobEntity.setDescription(job.getDescription());

		jobBean.create(jobEntity);

	}
	
	public JobEntity getJobEntityById(Long id) throws AdministrationEntityNotFoundException{
		
		JobEntity jobEntity = jobBean.findByJobId(id);
		
		if(jobEntity == null) {
			throw new AdministrationEntityNotFoundException("JobEntity wit id: " + id + " does not exist!");
		}
		
		return jobEntity;
	}
	
	public void editJob(Job job) {
		JobEntity jobEntity = null;
		
		try {
			jobEntity = this.getJobEntityById(job.getId());
		} catch (AdministrationEntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jobEntity.setDescription(job.getDescription());
		jobEntity.setName(job.getName());
		jobEntity.setId(job.getId());
		
		jobBean.update(jobEntity);

		
	}

}
