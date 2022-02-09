package com.southsystem.ApiVoting.app.resources.requests;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateVotingAgendaRequestDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3436276456948550079L;

	private Long id;

	@NotEmpty(message = "You must provide a title")
	@Length(min = 5, max = 80, message = "Invalid length. 5 to 80 characters.")
	private String title;
}
