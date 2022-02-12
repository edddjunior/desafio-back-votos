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

	@NotNull(message = "'cpf' is null.")
	@NotEmpty(message = "'cpf' is empty.")
	private String cpf;

	private String name;
}
