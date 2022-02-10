package com.southsystem.ApiVoting.app.resources.v1;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.southsystem.ApiVoting.app.config.dto.response.Response;
import com.southsystem.ApiVoting.app.domain.dto.VotingSessionDTO;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.resources.exceptions.VotingAgendaNotFoundException;
import com.southsystem.ApiVoting.app.resources.requests.StartVotingSessionRequestDTO;
import com.southsystem.ApiVoting.app.services.VotingAgendaService;
import com.southsystem.ApiVoting.app.services.VotingSessionService;
import com.southsystem.ApiVoting.app.services.mappers.VotingSessionMapper;
import com.southsystem.ApiVoting.app.util.ApiUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/voting/sessions")
public class VotingSessionResource {

	@Autowired
	private VotingSessionService votingSessionService;

	@Autowired
	private VotingAgendaService votingAgendaService;

	@Autowired
	private VotingSessionMapper votingSessionMapper;

	@PostMapping(path = "start", produces = { "application/json" })
	@ApiOperation(value = "Create Voting Session")
	public ResponseEntity<Response<VotingSessionDTO>> startVotingSession(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@Valid @RequestBody StartVotingSessionRequestDTO req, BindingResult result)
			throws VotingAgendaNotFoundException {

		Response<VotingSessionDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<VotingAgendaEntity> votingAgenda = votingAgendaService.find(req.getVotingAgendaId());
		if (votingAgenda.isEmpty()) {
			throw new VotingAgendaNotFoundException("No voting agenda found with 'id' = " + req.getVotingAgendaId());
		}

		VotingSessionEntity votingSession = votingSessionService
				.create(votingSessionMapper.toEntity(req, votingAgenda.get()));
		response.setData(votingSessionMapper.toDTO(votingSession, votingAgenda.get()));

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

}
