package com.southsystem.ApiVoting.app.services;

import java.util.Optional;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
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
	 * Saves a VotingAgenda.
	 * 
	 * @param VotingAgendaEntity
	 * @return VotingAgendaEntity
	 */
	VotingAgendaEntity createVotingAgenda(VotingAgendaEntity votingAgenda);

	/**
	 * Adds User to some VotingAgenda.
	 * 
	 * @param VotingAgendaEntity
	 * @param UserEntity
	 * @return VotingAgendaEntity
	 */
	VotingAgendaEntity addUserToVotingAgenda(VotingAgendaEntity votingAgenda, UserEntity user);
}
