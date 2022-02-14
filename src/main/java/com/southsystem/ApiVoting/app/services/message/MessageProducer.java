package com.southsystem.ApiVoting.app.services.message;

import com.southsystem.ApiVoting.app.domain.dto.SessionVotesDTO;
import com.southsystem.ApiVoting.app.services.message.topics.MessageTopics;

public interface MessageProducer {

	void send(MessageTopics topic, SessionVotesDTO data);
}
