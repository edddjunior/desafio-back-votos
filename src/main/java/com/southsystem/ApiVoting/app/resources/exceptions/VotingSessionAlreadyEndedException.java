package com.southsystem.ApiVoting.app.resources.exceptions;

public class VotingSessionAlreadyEndedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2716847662129074401L;

	public VotingSessionAlreadyEndedException() {
		super();
	}

	public VotingSessionAlreadyEndedException(String msg) {
		super(msg);
	}

	public VotingSessionAlreadyEndedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
