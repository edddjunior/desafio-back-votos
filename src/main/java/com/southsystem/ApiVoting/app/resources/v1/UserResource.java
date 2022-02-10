package com.southsystem.ApiVoting.app.resources.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.ApiVoting.app.config.dto.response.Response;
import com.southsystem.ApiVoting.app.domain.dto.UserDTO;
import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
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

	@PostMapping(path = "", produces = { "application/json" })
	@ApiOperation(value = "Create Users")
	public ResponseEntity<Response<UserDTO>> postVotingAgenda(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@Valid @RequestHeader CreateUserRequestDTO req, BindingResult result) {

		Response<UserDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		UserEntity user = userService.create(userMapper.toEntity(req));
		response.setData(userMapper.toUserDTO(user));

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}
}
