package com.southsystem.ApiVoting.app.services.jobs.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.services.jobs.workers.VoteWorker;

@Component
public class JobsListener {

	@Autowired
	private VoteWorker voteWorker;

	public void performVoteCounting(VotingSessionEntity data) {
		voteWorker.countVotesAndSendResult(data);
	}
}