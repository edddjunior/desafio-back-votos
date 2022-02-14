package com.southsystem.ApiVoting.app.domain.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VotingAgendaDTO extends RepresentationModel<VotingAgendaDTO> {

	private Long id;
	private String title;
}
