package com.hashedin.service.exception;

public class ResourceAlreadyExistException extends TodoBaseException{

	/** Exception class to be used for throwing exception when
	 *  resource already exists and we can't create duplicate for it.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistException(String msg){
		super(msg);
	}
}
