package com.hashedin.service.exception;

public class TodoBaseException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public TodoBaseException() {

	}

	public TodoBaseException(String message) {
		super(message);
		this.setMessage(message);
	}

	public TodoBaseException(Throwable e) {
		super(e);
	}

	public TodoBaseException(String message, Throwable t) {
		super(message, t);
		this.setMessage(message);
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
