package com.southsystem.ApiVoting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class ApiVotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiVotingApplication.class, args);
		System.out.println("Application Running...");
	}

}
