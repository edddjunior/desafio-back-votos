package com.southsystem.ApiVoting.app.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "voting_session")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingSessionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@NotNull(message = "'voting_agenda_id' is null")
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "voting_agenda_id", nullable = false)
	private VotingAgendaEntity agenda;

	@NotNull(message = "'start_datetime' is null")
	@Column(name = "start_datetime", nullable = false)
	private LocalDateTime startDatetime;

	@NotNull(message = "'duration_in_minutes' is null")
	@Positive(message = "'duration_in_minutes' must be a positive integer number")
	@Column(name = "duration_in_minutes", nullable = false)
	private Long durationInMinutes;

	@NotNull(message = "'end_datetime' is null")
	@Column(name = "end_datetime", nullable = false)
	private LocalDateTime endDateTime;
}