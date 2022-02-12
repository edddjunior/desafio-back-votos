package com.southsystem.ApiVoting.app.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	 * Returns all the VotingAgenda entities paged.
	 * 
	 * @param Pageable
	 * @return Page<VotingAgendaEntity>
	 */
	Page<VotingAgendaEntity> findAll(Pageable pageable);

	/**
	 * Creates a VotingAgenda.
	 * 
	 * @param VotingAgendaEntity
	 * @return VotingAgendaEntity
	 */
	VotingAgendaEntity create(VotingAgendaEntity data);
}
