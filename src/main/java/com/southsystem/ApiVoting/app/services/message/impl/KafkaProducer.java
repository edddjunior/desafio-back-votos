package com.southsystem.ApiVoting.app.services.message.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.southsystem.ApiVoting.app.domain.dto.SessionVotesDTO;
import com.southsystem.ApiVoting.app.services.message.MessageProducer;
import com.southsystem.ApiVoting.app.services.message.topics.MessageTopics;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KafkaProducer implements MessageProducer {

	private final KafkaTemplate<String, SessionVotesDTO> voteProducer;

	@Override
	public void send(MessageTopics topic, SessionVotesDTO data) {
		voteProducer.send(topic.name, data);
	}
}
