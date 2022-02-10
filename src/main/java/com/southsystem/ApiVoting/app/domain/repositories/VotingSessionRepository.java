package com.southsystem.ApiVoting.app.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;

public interface VotingSessionRepository extends JpaRepository<VotingSessionEntity, Long> {

}
