package com.southsystem.ApiVoting.app.services;

public interface VoteService {

	/**
	 * Verify if user already voted in the session.
	 * 
	 * @param sessionId
	 * @param userId
	 * @return boolean
	 */
	boolean verifyIfUserVoted(Long sessionId, Long userId);
}
