package com.southsystem.ApiVoting.app.services.jobs.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.southsystem.ApiVoting.app.domain.dto.SessionVotesDTO;
import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.services.jobs.workers.VoteWorker;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobsListener {

	@Autowired
	private VoteWorker voteWorker;

	public void performVoteCounting(VotingSessionEntity data) {
		voteWorker.countVotesAndSendResult(data);
	}

	public void feedbackKafka(SessionVotesDTO data) {
		log.info("Success!");
		log.info(data.toString());
		log.info("Success!");
	}
}