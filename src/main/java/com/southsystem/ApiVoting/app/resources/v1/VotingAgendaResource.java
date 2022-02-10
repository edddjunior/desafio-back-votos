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
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.resources.requests.CreateVotingAgendaRequestDTO;
import com.southsystem.ApiVoting.app.resources.responses.VotingAgendaResponseDTO;
import com.southsystem.ApiVoting.app.services.VotingAgendaService;
import com.southsystem.ApiVoting.app.services.mappers.VotingAgendaMapper;
import com.southsystem.ApiVoting.app.util.ApiUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("v1/voting/agendas")
public class VotingAgendaResource {

	@Autowired
	private VotingAgendaService votingAgendaService;

	@Autowired
	private VotingAgendaMapper votingAgendaMapper;

	@PostMapping(path = "", produces = { "application/json" })
	@ApiOperation(value = "Create VotingResource Agenda")
	public ResponseEntity<Response<VotingAgendaResponseDTO>> postVotingAgenda(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@Valid @RequestBody CreateVotingAgendaRequestDTO req, BindingResult result) {

		Response<VotingAgendaResponseDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		VotingAgendaEntity votingAgenda = votingAgendaMapper.toEntity(req);
		votingAgendaService.create(votingAgenda);

		response.setData(votingAgendaMapper.toResponse(votingAgenda));
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}
}
