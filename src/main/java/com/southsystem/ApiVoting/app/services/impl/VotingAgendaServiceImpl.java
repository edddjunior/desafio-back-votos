package com.southsystem.ApiVoting.app.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.domain.repositories.VotingAgendaRepository;
import com.southsystem.ApiVoting.app.services.VotingAgendaService;

@Service
public class VotingAgendaServiceImpl implements VotingAgendaService {

	@Autowired
	private VotingAgendaRepository votingAgendaRepository;

	/**
	 * @see VotingAgendaService#findVotingAgenda(Long)
	 */
	@Override
	public Optional<VotingAgendaEntity> findVotingAgenda(Long votingAgendaId) {
		return votingAgendaRepository.findById(votingAgendaId);
	}

	/**
	 * @see VotingAgendaService#create(VotingAgendaEntity)
	 */
	@Override
	@Transactional
	public VotingAgendaEntity create(VotingAgendaEntity votingAgenda) {
		return votingAgendaRepository.save(votingAgenda);
	}

	/**
	 * @see VotingAgendaService#addUserToVotingAgenda(VotingAgendaEntity,
	 *      UserEntity)
	 */
	@Override
	public VotingAgendaEntity addUserToVotingAgenda(VotingAgendaEntity votingAgenda, UserEntity user) {
		// TODO Auto-generated method stub
		return null;
	}
}
