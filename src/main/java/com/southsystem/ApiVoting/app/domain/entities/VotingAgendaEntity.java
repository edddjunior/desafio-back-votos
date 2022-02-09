package com.southsystem.ApiVoting.app.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "voting_agenda")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingAgendaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@NotNull(message = "'title' is null")
	@NotEmpty(message = "'title' is empty.")
	@Length(min = 5, max = 80, message = "'title' is invalid. Must have 5 to 80 characters.")
	@Column(name = "title", nullable = false)
	private String title;

	@Future(message = "'start_datetime' is invalid. Must be in the future")
	@Column(name = "start_datetime")
	private LocalDateTime startDateTime;

	@Future(message = "'end_datetime' is invalid. Must be in the future")
	@Column(name = "end_datetime")
	private LocalDateTime endDateTime;

	@ManyToMany
	@JoinTable(name = "agenda_voters", joinColumns = @JoinColumn(name = "agenda_id"), inverseJoinColumns = @JoinColumn(name = "voter_id"))
	@Setter(AccessLevel.NONE)
	private List<UserEntity> voters;

	public void addVoter(UserEntity voter) {
		this.voters.add(voter);
	}
}
