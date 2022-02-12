package com.southsystem.ApiVoting.app.services.jobs.workers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.dto.VoteDTO;
import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.entities.VoteEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.resources.exceptions.UserNotFoundException;
import com.southsystem.ApiVoting.app.resources.exceptions.VotingSessionAlreadyEndedException;
import com.southsystem.ApiVoting.app.resources.exceptions.VotingSessionNotFoundException;
import com.southsystem.ApiVoting.app.resources.requests.AddVoteRequestDTO;
import com.southsystem.ApiVoting.app.services.UserService;
import com.southsystem.ApiVoting.app.services.VoteService;
import com.southsystem.ApiVoting.app.services.VotingSessionService;
import com.southsystem.ApiVoting.app.services.mappers.VoteMapper;

@Service
public class VoteWorker {

	@Autowired
	private UserService userService;

	@Autowired
	private VotingSessionService votingSessionService;

	@Autowired
	private VoteService voteService;

	@Autowired
	private VoteMapper voteMapper;

//	@Autowired
//	private MessageProducer messageProducer;

	public VoteDTO performVote(AddVoteRequestDTO data)
			throws UserNotFoundException, VotingSessionNotFoundException, VotingSessionAlreadyEndedException {

		Optional<UserEntity> user = userService.find(data.getUserId());
		if (user.isEmpty()) {
			throw new UserNotFoundException("No user found with 'id' = " + data.getUserId());
		}
		Optional<VotingSessionEntity> votingSession = votingSessionService.find(data.getVotingSessionId());
		if (votingSession.isEmpty()) {
			throw new VotingSessionNotFoundException(
					"No voting session was found with 'id' = " + data.getVotingSessionId());
		}
		if (LocalDateTime.now().isAfter(votingSession.get().getEndDateTime())) {
			throw new VotingSessionAlreadyEndedException("This voting session has already ended.");
		}
//		if () {
//			// TODO: check user is already associated with vote as well as cpf validation
//		}

		VoteEntity voteEntity = voteService
				.addVote(voteMapper.toEntity(votingSession.get(), user.get(), data.getVoteType()));

//		messageProducer.send(MessageTopics.VOTE_RETURN, voteDTO);
		return voteMapper.toDTO(voteEntity);
	}
}
