package com.southsystem.ApiVoting.app.resources.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {

	private Long id;
	private String cpf;
	private String name;
}
