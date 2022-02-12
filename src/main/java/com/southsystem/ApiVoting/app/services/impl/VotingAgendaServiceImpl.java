package com.southsystem.ApiVoting.app.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.domain.repositories.VotingAgendaRepository;
import com.southsystem.ApiVoting.app.services.VotingAgendaService;

@Service
public class VotingAgendaServiceImpl implements VotingAgendaService {

	@Autowired
	private VotingAgendaRepository votingAgendaRepository;

	/**
	 * @see VotingAgendaService#find(Long)
	 */
	@Override
	@Cacheable("agendas")
	public Optional<VotingAgendaEntity> find(Long votingAgendaId) {
		return votingAgendaRepository.findById(votingAgendaId);
	}

	/**
	 * @see VotingAgendaService#findAll(Pageable)
	 */
	@Override
	@Cacheable("agendas")
	public Page<VotingAgendaEntity> findAll(Pageable pageable) {
		return votingAgendaRepository.findAll(pageable);
	}

	/**
	 * @see VotingAgendaService#create(VotingAgendaEntity)
	 */
	@Override
	@CacheEvict(value = "agendas", allEntries = true)
	@CachePut(value = "agendas", key = "#data.id")
	@Transactional
	public VotingAgendaEntity create(VotingAgendaEntity data) {
		return votingAgendaRepository.save(data);
	}
}
