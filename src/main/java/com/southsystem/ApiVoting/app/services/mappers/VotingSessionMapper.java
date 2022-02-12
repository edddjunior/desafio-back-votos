package com.southsystem.ApiVoting.app.services.mappers;

import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.dto.VotingAgendaDTO;
import com.southsystem.ApiVoting.app.domain.dto.VotingSessionDTO;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.resources.requests.StartVotingSessionRequestDTO;
import com.southsystem.ApiVoting.app.util.ApiUtil;

@Service
public class VotingSessionMapper {

	/**
	 * Converts the VotingSession request data representation to its actual entity.
	 * 
	 * @param StartVotingSessionRequestDTO
	 * @param VotingAgendaEntity
	 * @return VotingSessionEntity
	 */
	public VotingSessionEntity toEntity(StartVotingSessionRequestDTO startVotingSessionRequest,
			VotingAgendaEntity votingAgendaEntity) {
		VotingSessionEntity votingSession = new VotingSessionEntity();
		ApiUtil.setIfNotNull(votingSession::setId, null);
		ApiUtil.setIfNotNull(votingSession::setVotingAgenda, votingAgendaEntity);
		ApiUtil.setIfNotNull(votingSession::setDurationInMinutes, startVotingSessionRequest.getDurationInMinutes());
		return votingSession;
	}

	/**
	 * Converts VotingSession entity to its response data representation.
	 * 
	 * @param VotingSessionEntity
	 * @param VotingAgendaEntity
	 * @return VotingSessionDTO
	 */
	public VotingSessionDTO toDTO(VotingSessionEntity votingSessionEntity, VotingAgendaEntity votingAgendaEntity) {
		VotingAgendaDTO votingAgendaDTO = new VotingAgendaDTO();
		votingAgendaDTO.setId(votingAgendaEntity.getId());
		votingAgendaDTO.setTitle(votingAgendaEntity.getTitle());
		VotingSessionDTO response = new VotingSessionDTO();
		ApiUtil.setIfNotNull(response::setId, votingSessionEntity.getId());
		ApiUtil.setIfNotNull(response::setAgenda, votingAgendaDTO);
		ApiUtil.setIfNotNull(response::setStartDatetime, votingSessionEntity.getStartDatetime());
		ApiUtil.setIfNotNull(response::setEndDateTime, votingSessionEntity.getEndDateTime());
		ApiUtil.setIfNotNull(response::setHasStarted, votingSessionEntity.hasStarted());
		ApiUtil.setIfNotNull(response::setDurationInMinutes, votingSessionEntity.getDurationInMinutes());
		return response;
	}
}
