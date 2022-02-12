package com.southsystem.ApiVoting.app.services.mappers;

import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.dto.VotingAgendaDTO;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.resources.requests.CreateVotingAgendaRequestDTO;
import com.southsystem.ApiVoting.app.util.ApiUtil;

@Service
public class VotingAgendaMapper {

	/**
	 * Converts the VotingAgenda request data representation to its actual entity.
	 * 
	 * @param CreateVotingAgendaRequestDTO
	 * @return VotingAgendaEntity
	 */
	public VotingAgendaEntity toEntity(CreateVotingAgendaRequestDTO req) {
		VotingAgendaEntity votingAgenda = new VotingAgendaEntity();
		ApiUtil.setIfNotNull(votingAgenda::setId, null);
		ApiUtil.setIfNotNull(votingAgenda::setTitle, req.getTitle());
		return votingAgenda;
	}

	/**
	 * Converts VotingAgenda entity to its response data representation.
	 * 
	 * @param VotingAgendaEntity
	 * @return VotingAgendaDTO
	 */
	public VotingAgendaDTO toDTO(VotingAgendaEntity entity) {
		VotingAgendaDTO response = new VotingAgendaDTO();
		ApiUtil.setIfNotNull(response::setId, entity.getId());
		ApiUtil.setIfNotNull(response::setTitle, entity.getTitle());
		return response;
	}
}
