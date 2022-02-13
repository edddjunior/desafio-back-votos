package com.southsystem.ApiVoting.app.resources.exceptions;

public class UserAlreadyVotedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2628994470160657766L;

	public UserAlreadyVotedException() {
		super();
	}

	public UserAlreadyVotedException(String msg) {
		super(msg);
	}

	public UserAlreadyVotedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
