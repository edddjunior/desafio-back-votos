package com.southsystem.ApiVoting.app.services.mappers;

import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.dto.VoteDTO;
import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.entities.VoteEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.domain.enums.VoteType;
import com.southsystem.ApiVoting.app.util.ApiUtil;

@Service
public class VoteMapper {

	private UserMapper userMapper;
	private VotingSessionMapper votingSessionMapper;

	/**
	 * Converts the Vote request data representation to its actual entity.
	 * 
	 * @param VotingSessionEntity
	 * @param UserEntity
	 * @param VoteType
	 * @return VoteEntity
	 */
	public VoteEntity toEntity(VotingSessionEntity votingSessionEntity, UserEntity userEntity, VoteType voteType) {
		VoteEntity entity = new VoteEntity();
		ApiUtil.setIfNotNull(entity::setId, null);
		ApiUtil.setIfNotNull(entity::setSession, votingSessionEntity);
		ApiUtil.setIfNotNull(entity::setVoteType, voteType);
		return entity;
	}

	/**
	 * Converts Vote entity to its response data representation.
	 * 
	 * @param VoteEntity
	 * @return VoteDTO
	 */
	public VoteDTO toDTO(VoteEntity entity) {
		VoteDTO dto = new VoteDTO();
		ApiUtil.setIfNotNull(dto::setUser, userMapper.toDTO(entity.getUser()));
		ApiUtil.setIfNotNull(dto::setSession,
				votingSessionMapper.toDTO(entity.getSession(), entity.getSession().getAgenda()));
		return dto;
	}
}
