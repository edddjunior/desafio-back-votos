package com.southsystem.ApiVoting.app.services;

import java.util.Optional;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;

public interface VotingAgendaService {

	/**
	 * Finds a VotingAgenda by its id.
	 * 
	 * @param votingAgendaId
	 * @return Optional<VotingAgendaEntity>
	 */
	Optional<VotingAgendaEntity> findVotingAgenda(Long votingAgendaId);

	/**
	 * Creates a VotingAgenda.
	 * 
	 * @param VotingAgendaEntity
	 * @return VotingAgendaEntity
	 */
	VotingAgendaEntity create(VotingAgendaEntity votingAgenda);
}
