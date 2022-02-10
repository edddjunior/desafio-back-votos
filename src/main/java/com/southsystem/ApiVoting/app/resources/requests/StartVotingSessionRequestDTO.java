package com.southsystem.ApiVoting.app.resources.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StartVotingSessionRequestDTO {

	private Long votingAgendaId;
	private Long durationInMinutes;
}
