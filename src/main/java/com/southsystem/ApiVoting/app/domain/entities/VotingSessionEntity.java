package com.southsystem.ApiVoting.app.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	@OneToOne
	@JoinColumn(name = "agenda_id")
	private VotingAgendaEntity agenda;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
}
