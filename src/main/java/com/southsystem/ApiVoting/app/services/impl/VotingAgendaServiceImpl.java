package com.southsystem.ApiVoting.app.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.domain.repositories.VotingAgendaRepository;
import com.southsystem.ApiVoting.app.services.VotingAgendaService;

@Service
public class VotingAgendaServiceImpl implements VotingAgendaService {

	@Autowired
	private VotingAgendaRepository votingAgendaRepository;

	/**
	 * @see VotingAgendaService#find(Long)
	 */
	@Override
	public Optional<VotingAgendaEntity> find(Long votingAgendaId) {
		return votingAgendaRepository.findById(votingAgendaId);
	}

	/**
	 * @see VotingAgendaService#create(VotingAgendaEntity)
	 */
	@Override
	@Transactional
	public VotingAgendaEntity create(VotingAgendaEntity data) {
		return votingAgendaRepository.save(data);
	}
}
