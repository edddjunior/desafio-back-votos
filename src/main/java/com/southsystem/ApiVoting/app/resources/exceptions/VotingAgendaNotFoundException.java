package com.southsystem.ApiVoting.app.resources.exceptions;

public class VotingAgendaNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6245890977861924675L;

	public VotingAgendaNotFoundException() {
		super();
	}

	public VotingAgendaNotFoundException(String msg) {
		super(msg);
	}

	public VotingAgendaNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
