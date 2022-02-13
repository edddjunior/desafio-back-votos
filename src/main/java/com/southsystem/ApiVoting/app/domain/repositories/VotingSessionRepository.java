package com.southsystem.ApiVoting.app.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;

public interface VotingSessionRepository extends JpaRepository<VotingSessionEntity, Long> {

	/**
	 * Finds a VotingSession entity by VotingAgendaId.
	 * 
	 * @param Long
	 * @return Optional<VotingSessionEntity>
	 */
	Optional<VotingSessionEntity> findByVotingAgendaId(Long votingAgendaId);
}
