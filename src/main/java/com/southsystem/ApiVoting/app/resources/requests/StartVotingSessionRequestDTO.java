package com.southsystem.ApiVoting.app.resources.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StartVotingSessionRequestDTO {


	@NotNull(message = "'votingAgendaId' is null.")
	private Long votingAgendaId;

	@Positive(message = "'durationInMinutes' must be a positive integer number.")
	private Long durationInMinutes;
}
