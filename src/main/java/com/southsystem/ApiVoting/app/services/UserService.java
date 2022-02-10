package com.southsystem.ApiVoting.app.services;

import java.util.Optional;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;

public interface UserService {

	/**
	 * Finds an User by its id.
	 * 
	 * @param Long
	 * @return Optional<UserEntity>
	 */
	Optional<UserEntity> find(Long userId);

	/**
	 * Creates an User.
	 * 
	 * @param UserEntity
	 * @return UserEntity
	 */
	UserEntity create(UserEntity data);
}
