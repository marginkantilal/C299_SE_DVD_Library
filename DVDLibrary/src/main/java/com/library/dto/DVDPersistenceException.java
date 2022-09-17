package com.library.dto;

public class DVDPersistenceException extends Exception {
	public DVDPersistenceException(String message) {
		super(message);
	}
	
	public DVDPersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

}
