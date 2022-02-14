package com.southsystem.ApiVoting.app.resources.requests;

import javax.validation.constraints.NotNull;

import com.southsystem.ApiVoting.app.domain.enums.VoteType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddVoteRequestDTO {

	@NotNull(message = "'voting_session_id' is null.")
	private Long votingSessionId;

	@NotNull(message = "'user_id' is null.")
	private Long userId;

	@NotNull(message = "'vote_type' is null.")
	private VoteType voteType;
}
