package com.southsystem.ApiVoting.app.resources.v1;

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
import com.southsystem.ApiVoting.app.domain.dto.VotingAgendaDTO;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.resources.requests.CreateVotingAgendaRequestDTO;
import com.southsystem.ApiVoting.app.services.VotingAgendaService;
import com.southsystem.ApiVoting.app.services.mappers.VotingAgendaMapper;
import com.southsystem.ApiVoting.app.util.ApiUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/voting/agendas")
public class VotingAgendaResource {

	@Autowired
	private VotingAgendaService votingAgendaService;

	@Autowired
	private VotingAgendaMapper votingAgendaMapper;

	@PostMapping(path = "", produces = { "application/json" })
	@ApiOperation(value = "Create Voting Agenda")
	public ResponseEntity<Response<VotingAgendaDTO>> postVotingAgenda(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@Valid @RequestBody CreateVotingAgendaRequestDTO req, BindingResult result) {

		Response<VotingAgendaDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		VotingAgendaEntity votingAgenda = votingAgendaService.create(votingAgendaMapper.toEntity(req));
		VotingAgendaDTO votingAgendaDTO = votingAgendaMapper.toDTO(votingAgenda);
		createSelfLink(votingAgenda, votingAgendaDTO);
		response.setData(votingAgendaDTO);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

	/**
	 * Creates a self link of the VotingAgenda object.
	 * 
	 * @param UserEntity
	 * @param UserDTO
	 * 
	 */
	private void createSelfLink(VotingAgendaEntity entity, VotingAgendaDTO dto) {
		Link selfLink = WebMvcLinkBuilder.linkTo(VotingAgendaResource.class).slash(entity.getId()).withSelfRel();
		dto.add(selfLink);
	}
}
