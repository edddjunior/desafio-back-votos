package com.southsystem.ApiVoting.app.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.repositories.UserRepository;
import com.southsystem.ApiVoting.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * @see UserService#find(Long)
	 */
	@Override
//	@Cacheable("users")
	public Optional<UserEntity> find(Long userId) {
		return userRepository.findById(userId);
	}

	/**
	 * @see UserService#findAll(Pageable)
	 */
	@Override
//	@Cacheable("users")
	public Page<UserEntity> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	/**
	 * @see UserService#create(UserEntity)
	 */
	@Override
//	@CacheEvict(value = "users", allEntries = true)
//	@CachePut(value = "users", key = "#data.id")
	@Transactional
	public UserEntity create(UserEntity data) {
		return userRepository.save(data);
	}
}
