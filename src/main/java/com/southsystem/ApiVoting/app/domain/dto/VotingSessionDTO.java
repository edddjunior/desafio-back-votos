package com.southsystem.ApiVoting.app.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VotingSessionDTO {

	private Long id;
	private VotingAgendaDTO agenda;
	private LocalDateTime startDatetime;
	private LocalDateTime endDateTime;
	private Long durationInMinutes;
}
