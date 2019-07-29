package com.msg.adm.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.msg.adm.business.data.Job;
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

}
