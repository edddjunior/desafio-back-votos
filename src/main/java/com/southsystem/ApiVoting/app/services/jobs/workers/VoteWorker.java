package com.southsystem.ApiVoting.app.services.jobs.workers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.dto.SessionVotesDTO;
import com.southsystem.ApiVoting.app.domain.entities.VoteEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.domain.enums.VoteType;
import com.southsystem.ApiVoting.app.services.VoteService;
import com.southsystem.ApiVoting.app.services.message.MessageProducer;
import com.southsystem.ApiVoting.app.services.message.topics.MessageTopics;

@Service
public class VoteWorker {

	@Autowired
	private VoteService voteService;

	@Autowired
	private MessageProducer messageProducer;

	public void countVotesAndSendResult(VotingSessionEntity data) {
		SessionVotesDTO results = getResults(data.getId());
		messageProducer.send(MessageTopics.VOTE_RETURN, results);

	}

	public SessionVotesDTO getResults(Long sessionId) {
		List<VoteEntity> votes = voteService.findAllBySession(sessionId);
		Long yesVotes = votes.stream().filter(v -> v.getVoteType().equals(VoteType.YES)).count();
		Long noVotes = votes.stream().filter(v -> v.getVoteType().equals(VoteType.NO)).count();

		SessionVotesDTO sessionVotes = new SessionVotesDTO();
		sessionVotes.setVotingSessionId(sessionId);
		sessionVotes.setVotesYes(yesVotes);
		sessionVotes.setVotesNo(noVotes);
		return sessionVotes;
	}
}
