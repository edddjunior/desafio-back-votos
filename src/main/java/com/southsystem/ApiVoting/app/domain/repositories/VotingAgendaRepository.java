package com.southsystem.ApiVoting.app.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;

@Repository
public interface VotingAgendaRepository extends JpaRepository<VotingAgendaEntity, Long> {

}
