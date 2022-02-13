package com.southsystem.ApiVoting.app.services.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.dto.VoteDTO;
import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.entities.VoteEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.domain.enums.VoteType;
import com.southsystem.ApiVoting.app.util.ApiUtil;

@Service
public class VoteMapper {

	@Autowired
	private UserMapper userMapper;

	@Autowired
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
		ApiUtil.setIfNotNull(entity::setUser, userEntity);
		return entity;
	}

	/**
	 * Converts VotingSession, UserEntity and VoteType entity to Vote response data
	 * representation.
	 * 
	 * @param VotingSessionEntity
	 * @param UserEntity
	 * @param VoteType
	 * @return VoteEntity
	 */
	public VoteDTO toDTO(VotingSessionEntity votingSessionEntity, UserEntity userEntity, VoteType voteType) {
		VoteDTO dto = new VoteDTO();
		ApiUtil.setIfNotNull(dto::setId, votingSessionEntity.getVotes().stream()
				.filter(v -> v.getUser().getId() == userEntity.getId()).collect(Collectors.toList()).get(0).getId());
		ApiUtil.setIfNotNull(dto::setSession,
				votingSessionMapper.toDTO(votingSessionEntity, votingSessionEntity.getVotingAgenda()));
		ApiUtil.setIfNotNull(dto::setUser, userMapper.toDTO(userEntity));
		ApiUtil.setIfNotNull(dto::setVoteType, voteType);
		return dto;
	}
}
