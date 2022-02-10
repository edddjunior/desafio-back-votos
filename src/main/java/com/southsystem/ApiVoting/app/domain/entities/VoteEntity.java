package com.southsystem.ApiVoting.app.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.southsystem.ApiVoting.app.domain.enums.VoteType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vote")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "voting_session_id")
	private VotingSessionEntity session;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@NotNull(message = "'voteType' is null")
	@Enumerated(EnumType.STRING)
	private VoteType voteType;
}
