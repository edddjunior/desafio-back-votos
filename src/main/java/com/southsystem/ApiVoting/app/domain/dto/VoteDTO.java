package com.southsystem.ApiVoting.app.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoteDTO {

	private UserDTO user;
	private VotingSessionDTO session;
}
