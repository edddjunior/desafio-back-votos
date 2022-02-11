package com.southsystem.ApiVoting.app.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.southsystem.ApiVoting.app.domain.entities.VoteEntity;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

}
