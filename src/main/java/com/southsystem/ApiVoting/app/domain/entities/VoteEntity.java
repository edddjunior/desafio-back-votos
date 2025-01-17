package com.southsystem.ApiVoting.app.domain.entities;

import java.io.Serializable;

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
public class VoteEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5308059826046485900L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@NotNull(message = "'voting_session_id' is null.")
	@ManyToOne
	@JoinColumn(name = "voting_session_id")
	private VotingSessionEntity session;

	@NotNull(message = "'user_id' is null.")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@NotNull(message = "'voteType' is null.")
	@Enumerated(EnumType.STRING)
	@Column(name = "vote_type")
	private VoteType voteType;
}
