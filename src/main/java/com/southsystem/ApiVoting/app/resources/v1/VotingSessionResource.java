package com.southsystem.ApiVoting.app.resources.v1;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import com.southsystem.ApiVoting.app.resources.exceptions.VotingSessionAlreadyStartedException;
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

	@PostMapping(path = "", produces = { "application/json" })
	@ApiOperation(value = "Create Voting Session")
	public ResponseEntity<Response<VotingSessionDTO>> startVotingSession(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@Valid @RequestBody StartVotingSessionRequestDTO req, BindingResult result)
			throws VotingAgendaNotFoundException, VotingSessionAlreadyStartedException {

		Response<VotingSessionDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<VotingAgendaEntity> votingAgenda = votingAgendaService.find(req.getVotingAgendaId());
		if (votingAgenda.isEmpty()) {
			throw new VotingAgendaNotFoundException(
					"No voting agenda found with 'id' = " + req.getVotingAgendaId() + ".");
		}
		Optional<VotingSessionEntity> votingSession = votingSessionService
				.findByVotingAgendaId(req.getVotingAgendaId());
		if (votingSession.isPresent() && votingSession.get().hasStarted()) {
			throw new VotingSessionAlreadyStartedException("This voting agenda has already started.");
		}

		VotingSessionEntity votingSessionEntity = votingSessionService
				.create(votingSessionMapper.toEntity(req, votingAgenda.get()));
		VotingSessionDTO votingSessionDTO = votingSessionMapper.toDTO(votingSessionEntity, votingAgenda.get());
		createSelfLink(votingSessionEntity, votingSessionDTO);
		response.setData(votingSessionDTO);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

	/**
	 * Creates a self link of the VotingSession object
	 * 
	 * @param VotingSessionEntity
	 * @param VotingSessionDTO
	 * 
	 */
	private void createSelfLink(VotingSessionEntity entity, VotingSessionDTO dto) {
		Link selfLink = WebMvcLinkBuilder.linkTo(VotingSessionResource.class).slash(entity.getId()).withSelfRel();
		dto.add(selfLink);
		Link childLink = WebMvcLinkBuilder.linkTo(VotingAgendaResource.class).slash(entity.getVotingAgenda().getId())
				.withSelfRel();
		dto.getAgenda().add(childLink);
	}
}
