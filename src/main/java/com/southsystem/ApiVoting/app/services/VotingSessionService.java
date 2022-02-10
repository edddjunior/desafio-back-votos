package com.southsystem.ApiVoting.app.services;

import java.util.Optional;

import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;

public interface VotingSessionService {

	/**
	 * Finds a VotingSession by its id.
	 * 
	 * @param Long
	 * @return Optional<VotingSessionEntity>
	 */
	Optional<VotingSessionEntity> find(Long votingSessionId);

	/**
	 * Creates a VotingSession.
	 * 
	 * @param VotingSessionEntity
	 * @return VotingSessionEntity
	 */
	VotingSessionEntity create(VotingSessionEntity data);
}
