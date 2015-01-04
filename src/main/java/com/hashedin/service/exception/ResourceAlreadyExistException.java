package com.hashedin.service.exception;

public class ResourceAlreadyExistException extends TodoBaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistException(String msg){
		super(msg);
	}
}
