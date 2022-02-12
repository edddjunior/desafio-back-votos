package com.southsystem.ApiVoting.app.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -461444634228585948L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@NotNull(message = "'cpf' is null")
	@NotEmpty(message = "'cpf' is empty.")
	@Column(name = "cpf", nullable = false, length = 14)
	private String cpf;

	@Column(name = "name", nullable = true)
	private String name;
}
