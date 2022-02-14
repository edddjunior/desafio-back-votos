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
import com.southsystem.ApiVoting.app.domain.dto.VotingAgendaDTO;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.resources.exceptions.VotingAgendaNotFoundException;
import com.southsystem.ApiVoting.app.resources.requests.CreateVotingAgendaRequestDTO;
import com.southsystem.ApiVoting.app.services.VotingAgendaService;
import com.southsystem.ApiVoting.app.services.mappers.VotingAgendaMapper;
import com.southsystem.ApiVoting.app.util.ApiUtil;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/voting/agendas")
@Slf4j
public class VotingAgendaResource {

	@Autowired
	private VotingAgendaService votingAgendaService;

	@Autowired
	private VotingAgendaMapper votingAgendaMapper;

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Find a Voting Agenda by Id.")
	public ResponseEntity<Response<VotingAgendaDTO>> findById(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.version}") String apiVersion,
			@PathVariable("id") Long votingAgendaId) throws VotingAgendaNotFoundException {

		Response<VotingAgendaDTO> response = new Response<>();

		Optional<VotingAgendaEntity> votingAgenda = votingAgendaService.find(votingAgendaId);
		if (votingAgenda.isEmpty()) {
			throw new VotingAgendaNotFoundException("No voting agenda found with 'id' = " + votingAgendaId + ".");
		}
		VotingAgendaDTO votingAgendaDTO = votingAgendaMapper.toDTO(votingAgenda.get());
		createSelfLink(votingAgenda.get(), votingAgendaDTO);
		response.setData(votingAgendaDTO);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@GetMapping(value = "")
	@ApiOperation(value = "List All Voting Agendas.")
	public ResponseEntity<Response<List<VotingAgendaDTO>>> findAll(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@PageableDefault(page = 0, size = 10, sort = { "id" }) Pageable pageable)
			throws VotingAgendaNotFoundException {

		Response<List<VotingAgendaDTO>> response = new Response<>();

		Page<VotingAgendaEntity> votingAgendas = votingAgendaService.findAll(pageable);
		if (votingAgendas.isEmpty()) {
			throw new VotingAgendaNotFoundException("There are no voting agendas registered.");
		}

		List<VotingAgendaDTO> votingAgendasDTO = new ArrayList<>();
		votingAgendas.stream().forEach(a -> votingAgendasDTO.add(votingAgendaMapper.toDTO(a)));
		votingAgendasDTO.stream().forEach(dto -> {
			createSelfLink(apiVersion, dto);
		});
		response.setData(votingAgendasDTO);
		response.setTotalPages(votingAgendas.getTotalPages());
		response.setTotalElements(votingAgendas.getTotalElements());

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.OK);

	}

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
	 * @param VotingAgendaEntity
	 * @param VotingAgendaDTO
	 * 
	 */
	private void createSelfLink(VotingAgendaEntity entity, VotingAgendaDTO dto) {
		Link selfLink = WebMvcLinkBuilder.linkTo(VotingAgendaResource.class).slash(entity.getId()).withSelfRel();
		dto.add(selfLink);
	}

	/**
	 * Creates self links for the VotingAgenda objects.
	 * 
	 * @param String
	 * @param VotingAgendaDTO
	 * 
	 */
	private void createSelfLink(String apiVersion, final VotingAgendaDTO dto) {
		Link selfLink;
		try {
			selfLink = WebMvcLinkBuilder.linkTo(methodOn(VotingAgendaResource.class).findById(apiVersion, dto.getId()))
					.withSelfRel().expand();
			dto.add(selfLink);
		} catch (Exception e) {
			log.info(e.toString());
		}
	}
}
