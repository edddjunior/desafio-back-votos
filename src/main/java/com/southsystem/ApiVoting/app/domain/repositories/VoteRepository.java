package com.southsystem.ApiVoting.app.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.southsystem.ApiVoting.app.domain.entities.VoteEntity;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

	/**
	 * Verify if there is a vote with given sessionId and userId.
	 * 
	 * @param Long
	 * @param Long
	 * @return boolean
	 */
	boolean existsBySessionIdAndUserId(Long sessionId, Long userId);

	/**
	 * Finds all session votes.
	 * @param sessionId 
	 * 
	 * @param Long
	 * @return List<VoteEntity>
	 */
	List<VoteEntity> findAllBySessionId(Long sessionId);
}
