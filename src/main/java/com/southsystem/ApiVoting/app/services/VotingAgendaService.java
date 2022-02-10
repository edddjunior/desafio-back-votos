package com.southsystem.ApiVoting.app.services;

import java.util.Optional;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;

public interface VotingAgendaService {

	/**
	 * Finds a VotingAgenda by its id.
	 * 
	 * @param Long
	 * @return Optional<VotingAgendaEntity>
	 */
	Optional<VotingAgendaEntity> find(Long votingAgendaId);

	/**
	 * Creates a VotingAgenda.
	 * 
	 * @param VotingAgendaEntity
	 * @return VotingAgendaEntity
	 */
	VotingAgendaEntity create(VotingAgendaEntity data);
}
