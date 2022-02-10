package com.southsystem.ApiVoting.app.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

	private Long id;
	private String cpf;
	private String name;
}
