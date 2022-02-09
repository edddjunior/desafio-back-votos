package com.southsystem.ApiVoting.app.services;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;

public interface VotingAgendaService {

	VotingAgendaEntity createVotingAgenda(VotingAgendaEntity votingAgenda);
	
	VotingAgendaEntity findVotingAgenda(Long votingAgendaId);

	VotingAgendaEntity addUserToVotingAgenda(UserEntity userToBeAdded, Long votingAgendaId);
}
