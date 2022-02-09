package com.southsystem.ApiVoting.app.resources.responses;

import java.time.LocalDateTime;
import java.util.List;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VotingAgendaResponseDTO {

	private Long id;
	private String title;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private List<UserEntity> voters;
}
