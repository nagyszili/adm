package com.msg.adm.business.data;

/**
 * Abstract class for the Transfer Objects.
 *
 */
public abstract class AbstractPojo {

	private Long id;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}
