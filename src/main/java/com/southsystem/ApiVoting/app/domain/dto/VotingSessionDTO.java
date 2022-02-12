package com.southsystem.ApiVoting.app.domain.dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VotingSessionDTO extends RepresentationModel<VotingSessionDTO> {

	private Long id;
	private VotingAgendaDTO agenda;
	private boolean hasStarted;
	private LocalDateTime startDatetime;
	private LocalDateTime endDateTime;
	private Long durationInMinutes;
}
