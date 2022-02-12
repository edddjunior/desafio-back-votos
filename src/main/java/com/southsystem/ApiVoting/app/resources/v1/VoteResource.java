package com.southsystem.ApiVoting.app.resources.v1;

import javax.validation.Valid;

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

import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import com.southsystem.ApiVoting.app.config.dto.response.Response;
import com.southsystem.ApiVoting.app.domain.dto.VoteDTO;
import com.southsystem.ApiVoting.app.resources.requests.AddVoteRequestDTO;
import com.southsystem.ApiVoting.app.util.ApiUtil;

import io.swagger.annotations.ApiOperation;
import lombok.NonNull;

@RestController
@RequestMapping("v1/voting/vote")
public class VoteResource {

	private @NonNull RqueueMessageEnqueuer rqueueMessageEnqueuer;

//	@Value("${email.queue.name}")
	private String emailQueueName;

	@PostMapping(path = "vote", produces = { "application/json" })
	@ApiOperation(value = "Send Vote")
	public ResponseEntity<Response<VoteDTO>> addVote(
			@RequestHeader(value = ApiUtil.HEADER_API_VERSION, defaultValue = "${api.docs.version}") String apiVersion,
			@Valid @RequestBody AddVoteRequestDTO req, BindingResult result) {

		Response<VoteDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

//		messageProducer.send(MessageTopics.VOTE, req);

		if (rqueueMessageEnqueuer.enqueue(emailQueueName, req) != null) {

		}

		response.setData(new VoteDTO());

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(ApiUtil.HEADER_API_VERSION, apiVersion);
		return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
	}

}
