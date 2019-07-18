package com.msg.adm.rest;

import javax.ws.rs.core.Response;

/**
 * Values needed after a Resource Validation.
 *
 */
public class ValidationResponse {
	
	private boolean valid;
    private Response.Status status;
    private String message;
    
    /**
     * Standard Constructor
     *
     */
    public ValidationResponse() { }

    /**
     * Constructor
     *
     * @param valid
     */
    public ValidationResponse(boolean valid) {
        this.valid = valid;
    }
    
	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}
	
	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	/**
	 * @return the status
	 */
	public Response.Status getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(Response.Status status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
