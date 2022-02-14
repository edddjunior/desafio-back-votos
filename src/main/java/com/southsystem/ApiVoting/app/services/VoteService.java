package com.southsystem.ApiVoting.app.services;

import java.util.List;

import com.southsystem.ApiVoting.app.domain.entities.VoteEntity;

public interface VoteService {

	/**
	 * Verify if user already voted in the session.
	 * 
	 * @param sessionId
	 * @param userId
	 * @return boolean
	 */
	boolean verifyIfUserVoted(Long sessionId, Long userId);

	/**
	 * Finds all session votes.
	 * 
	 * @param sessionId
	 * @return List<VoteEntity>
	 */
	List<VoteEntity> findAllBySession(Long sessionId);
}
