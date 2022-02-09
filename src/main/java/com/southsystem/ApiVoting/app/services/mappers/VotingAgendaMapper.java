package com.southsystem.ApiVoting.app.services.mappers;

import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.resources.requests.CreateVotingAgendaRequestDTO;
import com.southsystem.ApiVoting.app.resources.responses.VotingAgendaResponseDTO;
import com.southsystem.ApiVoting.app.util.ApiUtil;

@Service
public class VotingAgendaMapper {

	/**
	 * Converts the VotingAgenda request data representation to its actual entity.
	 * 
	 * @param req
	 * @return votingAgenda
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
	 * @param entity
	 * @return response
	 */
	public VotingAgendaResponseDTO toResponse(VotingAgendaEntity entity) {
		VotingAgendaResponseDTO response = new VotingAgendaResponseDTO();
		ApiUtil.setIfNotNull(response::setId, entity.getId());
		ApiUtil.setIfNotNull(response::setTitle, entity.getTitle());
		ApiUtil.setIfNotNull(response::setStartDateTime, entity.getStartDateTime());
		ApiUtil.setIfNotNull(response::setEndDateTime, entity.getEndDateTime());
		ApiUtil.setIfNotNull(response::setVoters, entity.getVoters());
		return response;
	}
}
