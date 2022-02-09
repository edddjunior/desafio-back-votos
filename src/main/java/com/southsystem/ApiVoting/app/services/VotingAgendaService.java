package com.southsystem.ApiVoting.app.services;

import java.util.Optional;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;

public interface VotingAgendaService {

	Optional<VotingAgendaEntity> findVotingAgenda(Long votingAgendaId);

	VotingAgendaEntity createVotingAgenda(VotingAgendaEntity votingAgenda);

	VotingAgendaEntity addUserToVotingAgenda(VotingAgendaEntity votingAgenda, UserEntity user);
}
