package com.southsystem.ApiVoting.app.resources.exceptions;

public class VotingSessionNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5493406722489397734L;

	public VotingSessionNotFoundException() {
		super();
	}

	public VotingSessionNotFoundException(String msg) {
		super(msg);
	}

	public VotingSessionNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
