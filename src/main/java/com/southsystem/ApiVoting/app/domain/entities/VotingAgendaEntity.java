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
	@Column(name = "id")
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "start_datetime")
	private LocalDateTime startDateTime;
	@Column(name = "end_datetime")
	private LocalDateTime endDateTime;
	@ManyToMany
	@JoinTable(
		name = "agenda_voters", 
		joinColumns = @JoinColumn(name = "agenda_id"), 
		inverseJoinColumns = @JoinColumn(name = "voter_id")
	)
	private List<UserEntity> voters;
}
