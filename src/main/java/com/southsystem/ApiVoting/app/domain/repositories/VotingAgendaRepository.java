package com.southsystem.ApiVoting.app.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;

public interface VotingAgendaRepository extends JpaRepository<VotingAgendaEntity, Long> {

}
