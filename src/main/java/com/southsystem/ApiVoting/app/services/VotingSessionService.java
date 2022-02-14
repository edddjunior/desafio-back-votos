package com.southsystem.ApiVoting.app.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.southsystem.ApiVoting.app.domain.entities.VoteEntity;
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
	 * Returns all the VotingSession entities paged.
	 * 
	 * @param Pageable
	 * @return Page<VotingSessionEntity>
	 */
	Page<VotingSessionEntity> findAll(Pageable pageable);

	/**
	 * Finds a VotingSession by its associated VotingAgenda id.
	 * 
	 * @param Long
	 * @return Optional<VotingSessionEntity>
	 */
	Optional<VotingSessionEntity> findByVotingAgendaId(Long votingAgendaId);

	/**
	 * Creates a VotingSession.
	 * 
	 * @param VotingSessionEntity
	 * @return VotingSessionEntity
	 */
	VotingSessionEntity create(VotingSessionEntity data);

	/**
	 * Adds a vote to the session.
	 * 
	 * @param VotingSessionEntity
	 * @param VoteEntity
	 * @return VoteEntity
	 */
	VotingSessionEntity addVote(VotingSessionEntity voteSessionData, VoteEntity voteData);
}
