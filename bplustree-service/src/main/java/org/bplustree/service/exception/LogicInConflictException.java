package org.bplustree.service.exception;

public class LogicInConflictException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4124861316013506428L;

	public LogicInConflictException() {
		super();
	}
	
	public LogicInConflictException(String message) {
		super(message);
	}
}
