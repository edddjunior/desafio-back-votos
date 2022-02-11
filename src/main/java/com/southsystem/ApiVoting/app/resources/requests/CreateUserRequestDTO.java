package com.southsystem.ApiVoting.app.resources.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequestDTO {

	@NotNull(message = "'title' is null.")
	@NotEmpty(message = "'title' is empty.")
	private String cpf;

	private String name;
}
