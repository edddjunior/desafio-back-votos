package com.southsystem.ApiVoting.app.resources.requests;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

	@NotNull(message = "'title' is null")
	@NotEmpty(message = "'title' is empty.")
	@Length(min = 5, max = 80, message = "'title' is invalid. Must have 5 to 80 characters.")
	private String title;
}
