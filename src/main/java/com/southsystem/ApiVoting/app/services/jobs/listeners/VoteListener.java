package com.southsystem.ApiVoting.app.services.jobs.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.sonus21.rqueue.annotation.RqueueListener;
import com.southsystem.ApiVoting.app.domain.dto.VoteDTO;
import com.southsystem.ApiVoting.app.resources.exceptions.UserNotFoundException;
import com.southsystem.ApiVoting.app.resources.exceptions.VotingSessionAlreadyEndedException;
import com.southsystem.ApiVoting.app.resources.exceptions.VotingSessionNotFoundException;
import com.southsystem.ApiVoting.app.resources.requests.AddVoteRequestDTO;
import com.southsystem.ApiVoting.app.services.jobs.workers.VoteWorker;

@Component
public class VoteListener {

	@Autowired
	private VoteWorker voteWorker;

	@RqueueListener(value = "${votes.queue.name}")
	public VoteDTO performVote(AddVoteRequestDTO data)
			throws UserNotFoundException, VotingSessionNotFoundException, VotingSessionAlreadyEndedException {
		return voteWorker.performVote(data);
	}
}