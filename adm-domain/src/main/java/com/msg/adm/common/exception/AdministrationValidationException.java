package com.msg.adm.common.exception;

/**
 * Exception thrown when an unsuccessful Validation happens.
 *
 */
public class AdministrationValidationException extends Exception {

	private static final long serialVersionUID = -1514107098278028510L;
	
	/**
	 * Constructor 
	 * 
	 * @param message
	 */
	public AdministrationValidationException(String message) {
		super(message);
	}

}
