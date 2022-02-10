package com.southsystem.ApiVoting.app.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.repositories.UserRepository;
import com.southsystem.ApiVoting.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	/**
	 * @see UserService#findUser(Long)
	 */
	@Override
	public Optional<UserEntity> findUser(Long userId) {
		return userRepository.findById(userId);
	}

	/**
	 * @see UserService#create(UserEntity)
	 */
	@Override
	@Transactional
	public UserEntity create(UserEntity user) {
		return userRepository.save(user);
	}
}
