package com.southsystem.ApiVoting.app.services.impl;

import java.time.LocalDateTime;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.domain.repositories.VotingSessionRepository;
import com.southsystem.ApiVoting.app.services.VotingSessionService;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

	@Autowired
	private VotingSessionRepository votingsessionRepository;
	
	@Value("${app.preferences.session.default_duration_time}")
	private Long defaultDurationTime;

	/**
	 * @see VotingSessionService#find(Long)
	 */
	@Override
	public Optional<VotingSessionEntity> find(Long votingSessionId) {
		return votingsessionRepository.findById(votingSessionId);
	}

	/**
	 * @see VotingSessionService#create(VotingSessionEntity)
	 */
	@Override
	@Transactional
	public VotingSessionEntity create(VotingSessionEntity data) {
		if (data.getDurationInMinutes() == null || data.getDurationInMinutes() == (long) 0) {
			data.setDurationInMinutes(defaultDurationTime);
		}
		data.setStartDatetime(LocalDateTime.now());
		data.setEndDateTime(data.getStartDatetime().plusMinutes(data.getDurationInMinutes()));
		data.getAgenda().setHasStarted(true);
		return votingsessionRepository.save(data);
	}
}
