package com.southsystem.ApiVoting.app.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	 * Returns all the User entities paged.
	 * 
	 * @return Page<UserEntity>
	 */
	Page<UserEntity> findAll(Pageable pageable);

	/**
	 * Creates an User.
	 * 
	 * @param UserEntity
	 * @return UserEntity
	 */
	UserEntity create(UserEntity data);
}
