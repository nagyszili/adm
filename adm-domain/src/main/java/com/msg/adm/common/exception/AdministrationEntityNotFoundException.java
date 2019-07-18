package com.msg.adm.common.exception;

/**
 * Exception thrown when an Entity is not found.
 *
 */
public class AdministrationEntityNotFoundException extends Exception {

	private static final long serialVersionUID = 1202381306104162992L;
	
	/**
	 * Constructor 
	 * 
	 * @param message
	 */
	public AdministrationEntityNotFoundException(String message) {
		super(message);
	}

}
