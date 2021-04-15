package org.bplustree.service.exception;

public class EntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -4124861316013506428L;

	public EntityNotFoundException() {
		super();
	}
	
	public EntityNotFoundException(String message) {
		super(message);
	}
}
