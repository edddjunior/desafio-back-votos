package com.southsystem.ApiVoting.app.services;

import java.util.Optional;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;

public interface UserService {

	/**
	 * Finds an User by its id.
	 * 
	 * @param userId
	 * @return Optional<UserEntity>
	 */
	Optional<UserEntity> findUser(Long userId);

	/**
	 * Creates an User.
	 * 
	 * @param UserEntity
	 * @return UserEntity
	 */
	UserEntity create(UserEntity user);
}
