package com.southsystem.ApiVoting.app.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
