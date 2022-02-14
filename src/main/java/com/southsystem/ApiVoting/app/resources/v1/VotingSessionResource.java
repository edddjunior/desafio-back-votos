package com.southsystem.ApiVoting.app.resources.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
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
import com.southsystem.ApiVoting.app.domain.dto.SessionVotesDTO;
import com.southsystem.ApiVoting.app.domain.dto.VoteDTO;
import com.southsystem.ApiVoting.app.domain.dto.VotingSessionDTO;
import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.app.resources.exceptions.UserAlreadyVotedException;
import com.southsystem.ApiVoting.app.resources.exceptions.UserNotFoundException;
import com.southsystem.ApiVoting.app.resources.exceptions.VotingAgendaNotFoundException;
import com.southsystem.ApiVoting.app.resources.exceptions.VotingSessionAlreadyEndedException;
import com.southsystem.ApiVoting.app.resources.exceptions.VotingSessionAlreadyStartedException;
import com.southsystem.ApiVoting.app.resources.exceptions.VotingSessionNotFoundException;
import com.southsystem.ApiVoting.app.resources.requests.AddVoteRequestDTO;
import com.southsystem.ApiVoting.app.resources.requests.StartVotingSessionRequestDTO;
import com.southsystem.ApiVoting.app.services.UserService;
import com.southsystem.ApiVoting.app.services.VoteService;
import com.southsystem.ApiVoting.app.services.VotingAgendaService;
import com.southsystem.ApiVoting.app.services.VotingSessionService;
import com.southsystem.ApiVoting.app.services.jobs.workers.VoteWorker;
import com.southsystem.ApiVoting.app.services.mappers.VoteMapper;
import com.southsystem.ApiVoting.app.services.mappers.VotingSessionMapper;
import com.southsystem.ApiVoting.app.util.ApiUtil;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/voting/sessions")
@Slf4j
public class VotingSessionResource {

	@Autowired
	private VotingSessionService votingSessionService;

	@Autowired
	private VotingAgendaService votingAgendaService;

	@Autowired
	private UserService userService;

	@Autowired
	private VoteService voteService;

	@Autowired
	private VoteWorker voteWorker;

	@Autowired
	private VotingSessionMapper votingSessionMapper;

	@Autowired
	private VoteMapper voteMapper;

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Find a VotingSession by Id.")
	public ResponseEntity<Response<VotingSessionDTO>> findById(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
			@PathVariable("id") Long votingSessionId) throws VotingSessionNotFoundException {

		Response<VotingSessionDTO> response = new Response<>();

		Optional<VotingSessionEntity> votingSession = votingSessionService.find(votingSessionId);
		if (votingSession.isEmpty()) {
			throw new VotingSessionNotFoundException("No voting session found with 'id' = " + votingSessionId + ".");
		}
		VotingSessionDTO votingSessionDTO = votingSessionMapper.toDTO(votingSession.get(),
				votingSession.get().getVotingAgenda());
		createSelfLink(votingSession.get(), votingSessionDTO);
		response.setData(votingSessionDTO);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@GetMapping(value = "")
	@ApiOperation(value = "List VotingSessions.")
	public ResponseEntity<Response<List<VotingSessionDTO>>> findAll(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@PageableDefault(page = 0, size = 10, sort = { "id" }) Pageable pageable)
			throws VotingSessionNotFoundException {

		Response<List<VotingSessionDTO>> response = new Response<>();

		Page<VotingSessionEntity> votingSessions = votingSessionService.findAll(pageable);
		if (votingSessions.isEmpty()) {
			throw new VotingSessionNotFoundException("There are no voting sessions registered.");
		}

		List<VotingSessionDTO> votingSessionsDTO = new ArrayList<>();
		votingSessions.stream().forEach(s -> votingSessionsDTO.add(votingSessionMapper.toDTO(s, s.getVotingAgenda())));
		votingSessionsDTO.stream().forEach(dto -> {
			createSelfLink(apiVersion, dto);
		});
		response.setData(votingSessionsDTO);
		response.setTotalPages(votingSessions.getTotalPages());
		response.setTotalElements(votingSessions.getTotalElements());

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

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

	@PostMapping(path = "vote", produces = { "application/json" })
	@ApiOperation(value = "Send Vote.")
	public ResponseEntity<Response<VoteDTO>> addVote(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@Valid @RequestBody AddVoteRequestDTO req, BindingResult result) throws UserNotFoundException,
			VotingSessionNotFoundException, VotingSessionAlreadyEndedException, UserAlreadyVotedException {

		Response<VoteDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<UserEntity> user = userService.find(req.getUserId());
		if (user.isEmpty()) {
			throw new UserNotFoundException("No user found with 'id' = " + req.getUserId());
		}
		Optional<VotingSessionEntity> votingSession = votingSessionService.find(req.getVotingSessionId());
		if (votingSession.isEmpty()) {
			throw new VotingSessionNotFoundException("No voting session found with 'id' = " + req.getVotingSessionId());
		}
		if (LocalDateTime.now().isAfter(votingSession.get().getEndDateTime())) {
			throw new VotingSessionAlreadyEndedException("This voting session has already ended.");
		}
		if (voteService.verifyIfUserVoted(req.getVotingSessionId(), req.getUserId())) {
			throw new UserAlreadyVotedException("This user already voted.");
		}

		VotingSessionEntity updatedVotingSession = votingSessionService.addVote(votingSession.get(),
				voteMapper.toEntity(votingSession.get(), user.get(), req.getVoteType()));
		VoteDTO voteDTO = voteMapper.toDTO(updatedVotingSession, user.get(), req.getVoteType());

		createSelfLink(apiVersion, voteDTO);
		response.setData(voteDTO);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

	@GetMapping(path = "{sessionId}/results", produces = { "application/json" })
	@ApiOperation(value = "Verify Session Results.")
	public ResponseEntity<Response<SessionVotesDTO>> verifyResults(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@PathVariable("sessionId") Long sessionId)
			throws VotingSessionNotFoundException, VotingSessionAlreadyEndedException {

		Response<SessionVotesDTO> response = new Response<>();

		Optional<VotingSessionEntity> votingSession = votingSessionService.find(sessionId);
		if (votingSession.isEmpty()) {
			throw new VotingSessionNotFoundException("No voting session found with 'id' = " + sessionId);
		}

		SessionVotesDTO results = voteWorker.getResults(sessionId);
		createSelfLink(apiVersion, results);
		response.setData(results);

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

	/**
	 * Creates self links for the VotingSession objects.
	 * 
	 * @param String
	 * @param VotingSessionDTO
	 * 
	 */
	private void createSelfLink(String apiVersion, final VotingSessionDTO dto) {
		Link selfLink;
		Link childLink;
		try {
			selfLink = WebMvcLinkBuilder.linkTo(methodOn(VotingSessionResource.class).findById(apiVersion, dto.getId()))
					.withSelfRel().expand();
			dto.add(selfLink);
			childLink = WebMvcLinkBuilder
					.linkTo(methodOn(VotingSessionResource.class).findById(apiVersion, dto.getAgenda().getId()))
					.withSelfRel().expand();
			dto.getAgenda().add(childLink);
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	private void createSelfLink(String apiVersion, final VoteDTO dto) {
		Link childLinkUser;
		Link childLinkSession;
		Link childLinkAgenda;
		try {
			childLinkUser = WebMvcLinkBuilder
					.linkTo(methodOn(UserResource.class).findById(apiVersion, dto.getUser().getId())).withSelfRel()
					.expand();
			dto.getUser().add(childLinkUser);
			childLinkSession = WebMvcLinkBuilder
					.linkTo(methodOn(VotingSessionResource.class).findById(apiVersion, dto.getSession().getId()))
					.withSelfRel().expand();
			dto.getSession().add(childLinkSession);
			childLinkAgenda = WebMvcLinkBuilder.linkTo(
					methodOn(VotingAgendaResource.class).findById(apiVersion, dto.getSession().getAgenda().getId()))
					.withSelfRel().expand();
			dto.getSession().getAgenda().add(childLinkAgenda);
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	private void createSelfLink(String apiVersion, final SessionVotesDTO dto) {
		Link selfLink;
		try {
			selfLink = WebMvcLinkBuilder
					.linkTo(methodOn(VotingSessionResource.class).verifyResults(apiVersion, dto.getVotingSessionId()))
					.withSelfRel().expand();
			dto.add(selfLink);
		} catch (Exception e) {
			log.info(e.toString());
		}
	}
}
