package org.bplustree.service.exception;

public class InvalidDataProvidedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4124861316013506428L;

	public InvalidDataProvidedException() {
		super();
	}
	
	public InvalidDataProvidedException(String message) {
		super(message);
	}
}
