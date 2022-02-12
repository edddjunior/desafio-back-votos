package com.southsystem.ApiVoting.app.services.message;

import com.southsystem.ApiVoting.app.domain.dto.VoteDTO;
import com.southsystem.ApiVoting.app.resources.requests.AddVoteRequestDTO;
import com.southsystem.ApiVoting.app.services.message.enums.MessageTopics;

public interface MessageProducer {

	void send(MessageTopics topic, AddVoteRequestDTO voteEntity);

	void send(MessageTopics topic, VoteDTO data);
}
