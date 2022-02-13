package com.southsystem.ApiVoting.app.services.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.domain.repositories.VotingSessionRepository;
import com.southsystem.ApiVoting.app.services.VotingSessionService;
import com.southsystem.ApiVoting.app.services.jobs.listeners.JobsListener;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

	@Autowired
	private VotingSessionRepository votingsessionRepository;

	@Autowired
	private JobsListener jobsListener;

	@Value("${app.preferences.session.default_duration_time}")
	private Long defaultDurationTime;

	/**
	 * @see VotingSessionService#find(Long)
	 */
	@Override
	@Cacheable(value = "sessions")
	public Optional<VotingSessionEntity> find(Long votingSessionId) {
		return votingsessionRepository.findById(votingSessionId);
	}

	/**
	 * @see VotingSessionService#findAll(Pageable)
	 */
	@Override
	@Cacheable(value = "sessions")
	public Page<VotingSessionEntity> findAll(Pageable pageable) {
		return votingsessionRepository.findAll(pageable);
	}

	/**
	 * @see VotingSessionService#findByVotingAgendaId(VotingSessionEntity)
	 */
	@Override
	@Cacheable(value = "sessions")
	public Optional<VotingSessionEntity> findByVotingAgendaId(Long votingAgendaId) {
		return votingsessionRepository.findByVotingAgendaId(votingAgendaId);
	}

	/**
	 * @see VotingSessionService#create(VotingSessionEntity)
	 */
	@Override
	@CacheEvict(value = "agendas", allEntries = true)
	@CachePut(value = "agendas", key = "#data.id")
	@Transactional
	public VotingSessionEntity create(VotingSessionEntity data) {
		if (data.getDurationInMinutes() == null || data.getDurationInMinutes() == (long) 0) {
			data.setDurationInMinutes(defaultDurationTime);
		}
		data.setStartDatetime(LocalDateTime.now());
		data.setHasStarted(true);
		data.setEndDateTime(data.getStartDatetime().plusMinutes(data.getDurationInMinutes()));

		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
			@Override
			public void afterCommit() {
				setTriggerVoteCounter(data);
			}
		});
		return votingsessionRepository.save(data);
	}

	/**
	 * Schedules a job for vote counting.
	 * 
	 * @param VotingSessionEntity
	 */
	private void setTriggerVoteCounter(VotingSessionEntity data) {
		ScheduledExecutorService exe = Executors.newSingleThreadScheduledExecutor();
		exe.schedule(() -> {
			jobsListener.performVoteCounting(data);
		}, LocalDateTime.now().until(data.getEndDateTime(), ChronoUnit.SECONDS), TimeUnit.SECONDS);
	}
}
