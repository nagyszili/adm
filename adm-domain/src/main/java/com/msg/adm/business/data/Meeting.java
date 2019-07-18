package com.msg.adm.business.data;

import com.msg.adm.model.MeetingEntity;

/**
 * Transfer Object for MeetingEntity
 * 
 */
public class Meeting extends AbstractPojo {

	private String name;
	
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
	
	
	public static Meeting getMeeting(MeetingEntity meetingEntity) {
		Meeting meeting = new Meeting();
		meeting.setId(meetingEntity.getId());
		meeting.setName(meetingEntity.getName());
		
		return meeting;
	}
	
	
}
