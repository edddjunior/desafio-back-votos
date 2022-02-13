package com.southsystem.ApiVoting.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.repositories.VoteRepository;
import com.southsystem.ApiVoting.app.services.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	/**
	 * @see VoteService#verifyIfUserVoted(Long, Long)
	 */
	@Override
	public boolean verifyIfUserVoted(Long sessionId, Long userId) {
		return voteRepository.existsBySessionIdAndUserId(sessionId, userId);
	}
}
