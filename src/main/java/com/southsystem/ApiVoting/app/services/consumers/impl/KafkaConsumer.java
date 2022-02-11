package com.southsystem.ApiVoting.app.services.consumers.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

@KafkaListener(topicPattern = "${kafka.topics.project-status-changed}", autoStartup = "${kafka.enabled}")
public class KafkaConsumer {

public void listenToProjectStatusChange(ConsumerRecord<String, ProjectStatusChangeDto> record) {

    ProjectStatusChangeDto payload = record.value();

}
