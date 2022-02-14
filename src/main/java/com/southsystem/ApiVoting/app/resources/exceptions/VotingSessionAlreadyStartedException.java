package com.southsystem.ApiVoting.app.resources.exceptions;

public class VotingSessionAlreadyStartedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5874412992692428670L;

	public VotingSessionAlreadyStartedException() {
		super();
	}

	public VotingSessionAlreadyStartedException(String msg) {
		super(msg);
	}

	public VotingSessionAlreadyStartedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
