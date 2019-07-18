package com.msg.adm.business.data;

import com.msg.adm.model.AppliedJobEntity;
import com.msg.adm.model.JobEntity;

/**
 * Transfer Object for AppliedJobEntity.
 *
 */
public class AppliedJob extends AbstractPojo {

	private User user;
	private Job job;
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}
	
	/**
	 * @param job the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}
	

	public static AppliedJob getAppliedJob(AppliedJobEntity appliedJobEntity) {
		AppliedJob appliedJob = new AppliedJob();
		appliedJob.setId(appliedJobEntity.getId());
		appliedJob.setJob(Job.getJob(appliedJobEntity.getJob()));
		
		return appliedJob;
	}
	
}