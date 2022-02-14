package com.southsystem.ApiVoting.app.domain.dto;

import org.springframework.hateoas.RepresentationModel;

import com.southsystem.ApiVoting.app.domain.enums.VoteType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VoteDTO extends RepresentationModel<VoteDTO> {

	private Long id;
	private UserDTO user;
	private VotingSessionDTO session;
	private VoteType voteType;
}
