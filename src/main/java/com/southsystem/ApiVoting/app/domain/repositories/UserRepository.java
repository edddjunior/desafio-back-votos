package com.southsystem.ApiVoting.app.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
