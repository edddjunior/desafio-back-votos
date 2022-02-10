package com.southsystem.ApiVoting.app.services.mappers;

import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.dto.UserDTO;
import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.resources.requests.CreateUserRequestDTO;
import com.southsystem.ApiVoting.app.util.ApiUtil;

@Service
public class UserMapper {

	/**
	 * Converts the User request data representation to its actual entity.
	 * 
	 * @param CreateUserRequestDTO
	 * @return UserEntity
	 */
	public UserEntity toEntity(CreateUserRequestDTO req) {
		UserEntity user = new UserEntity();
		ApiUtil.setIfNotNull(user::setId, null);
		ApiUtil.setIfNotNull(user::setCpf, req.getCpf());
		ApiUtil.setIfNotNull(user::setName, req.getName());
		return user;
	}

	/**
	 * Converts VotingAgenda entity to its response data representation.
	 * 
	 * @param UserEntity
	 * @return UserDTO
	 */
	public UserDTO toUserDTO(UserEntity entity) {
		UserDTO response = new UserDTO();
		ApiUtil.setIfNotNull(response::setId, entity.getId());
		ApiUtil.setIfNotNull(response::setCpf, entity.getCpf());
		ApiUtil.setIfNotNull(response::setName, entity.getName());
		return response;
	}
}
