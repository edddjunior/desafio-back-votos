package com.southsystem.ApiVoting.app.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.domain.repositories.VotingAgendaRepository;
import com.southsystem.ApiVoting.app.services.VotingAgendaService;

@Service
public class VotingAgendaImpl implements VotingAgendaService {
	
	@Autowired
	private VotingAgendaRepository votingAgendaRepository;

	public VotingAgendaEntity createVotingAgenda(VotingAgendaEntity votingAgenda) {
		// TODO Auto-generated method stub
		return null;
	}

	public VotingAgendaEntity findVotingAgenda(Long votingAgendaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public VotingAgendaEntity addUserToVotingAgenda(UserEntity userToBeAdded, Long votingAgendaId) {
		VotingAgendaEntity votingAgenda = findVotingAgenda(votingAgendaId);
		votingAgenda.addVoter(userToBeAdded);
		return votingAgendaRepository.save(votingAgenda);
	}
}
