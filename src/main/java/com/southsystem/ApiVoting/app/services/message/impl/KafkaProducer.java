package com.southsystem.ApiVoting.app.services.message.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.southsystem.ApiVoting.app.domain.dto.VoteDTO;
import com.southsystem.ApiVoting.app.resources.requests.AddVoteRequestDTO;
import com.southsystem.ApiVoting.app.services.message.MessageProducer;
import com.southsystem.ApiVoting.app.services.message.enums.MessageTopics;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KafkaProducer implements MessageProducer {

	private final KafkaTemplate<String, AddVoteRequestDTO> voteProducer;
	private final KafkaTemplate<String, VoteDTO> voteReturnProducer;

	@Override
	public void send(MessageTopics topic, AddVoteRequestDTO data) {
		voteProducer.send(topic.name, data);
	}

	@Override
	public void send(MessageTopics topic, VoteDTO data) {
		voteReturnProducer.send(topic.name, data);
	}
}


//public class VoteListener {
//
//	@Autowired
//	private VoteWorker voteWorker;
//
//	@KafkaListener(topicPattern = "${kafka.topics.vote}", autoStartup = "${kafka.enabled}")
//	public void performVote(ConsumerRecord<String, AddVoteRequestDTO> data) {
//
//		AddVoteRequestDTO voteRequest = data.value();
//
//		try {
//			voteWorker.performVote(voteRequest);
//		} catch (Exception e) {
//		}
//	}
//}