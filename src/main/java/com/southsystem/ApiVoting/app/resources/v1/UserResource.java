package com.southsystem.ApiVoting.app.resources.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.ApiVoting.app.config.dto.response.Response;
import com.southsystem.ApiVoting.app.domain.dto.UserDTO;
import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.resources.exceptions.UserNotFoundException;
import com.southsystem.ApiVoting.app.resources.requests.CreateUserRequestDTO;
import com.southsystem.ApiVoting.app.services.UserService;
import com.southsystem.ApiVoting.app.services.mappers.UserMapper;
import com.southsystem.ApiVoting.app.util.ApiUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Find an User by Id.")
	public ResponseEntity<Response<UserDTO>> findById(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
			@PathVariable("id") Long userId) throws UserNotFoundException {

		Response<UserDTO> response = new Response<>();

		Optional<UserEntity> user = userService.find(userId);
		if (user.isEmpty()) {
			throw new UserNotFoundException("No user found with 'id' = " + userId + ".");
		}
		UserDTO userDTO = userMapper.toDTO(user.get());
		createSelfLink(user.get(), userDTO);
		response.setData(userDTO);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@GetMapping(value = "")
	@ApiOperation(value = "List All Users.")
	public ResponseEntity<Response<List<UserDTO>>> findAll(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@PageableDefault(page = 0, size = 10, sort = { "id" }) Pageable pageable) throws UserNotFoundException {

		Response<List<UserDTO>> response = new Response<>();

		Page<UserEntity> users = userService.findAll(pageable);
		if (users.isEmpty()) {
			throw new UserNotFoundException("There are no users registered.");
		}

		List<UserDTO> usersDTO = new ArrayList<>();
		users.stream().forEach(u -> usersDTO.add(userMapper.toDTO(u)));
		usersDTO.stream().forEach(dto -> {
			createSelfLink(apiVersion, dto);
		});
		response.setData(usersDTO);
		response.setTotalPages(users.getTotalPages());
		response.setTotalElements(users.getTotalElements());

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.OK);

	}

	@PostMapping(path = "", produces = { "application/json" })
	@ApiOperation(value = "Create Users.")
	public ResponseEntity<Response<UserDTO>> postVotingAgenda(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@Valid @RequestBody CreateUserRequestDTO req, BindingResult result) {

		Response<UserDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		UserEntity user = userService.create(userMapper.toEntity(req));
		UserDTO userDTO = userMapper.toDTO(user);
		createSelfLink(user, userDTO);
		response.setData(userDTO);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

	/**
	 * Creates a self link of the User object.
	 * 
	 * @param UserEntity
	 * @param UserDTO
	 * 
	 */
	private void createSelfLink(UserEntity entity, UserDTO dto) {
		Link selfLink = WebMvcLinkBuilder.linkTo(UserResource.class).slash(entity.getId()).withSelfRel();
		dto.add(selfLink);
	}

	/**
	 * Creates self links for the User objects.
	 * 
	 * @param String
	 * @param VotingAgendaDTO
	 * 
	 */
	private void createSelfLink(String apiVersion, final UserDTO dto) {
		Link selfLink;
		try {
			selfLink = WebMvcLinkBuilder.linkTo(methodOn(UserResource.class).findById(apiVersion, dto.getId()))
					.withSelfRel().expand();
			dto.add(selfLink);
		} catch (Exception e) {
		}
	}
}
