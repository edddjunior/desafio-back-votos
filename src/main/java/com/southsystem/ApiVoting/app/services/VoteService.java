package com.southsystem.ApiVoting.app.services;

import com.southsystem.ApiVoting.app.domain.entities.VoteEntity;

public interface VoteService {

	/**
	 * Adds a vote to the session.
	 * 
	 * @param VoteEntity
	 * @return VoteEntity
	 */
	VoteEntity addVote(VoteEntity data);
}
