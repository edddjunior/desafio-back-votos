package com.southsystem.ApiVoting.app.resources.exceptions;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.southsystem.ApiVoting.app.config.dto.response.Response;

@ControllerAdvice
public class ApiExceptionHandler<T> {

	@ExceptionHandler(value = { UserAlreadyVotedException.class })
	private ResponseEntity<Response<T>> handleUserAlreadyVotedException(UserAlreadyVotedException exception,
			WebRequest request) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(value = { UserNotFoundException.class })
	private ResponseEntity<Response<T>> handleUserNotFoundException(UserNotFoundException exception,
			WebRequest request) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(value = { VotingAgendaNotFoundException.class })
	private ResponseEntity<Response<T>> handleVotingAgendaNotFoundException(VotingAgendaNotFoundException exception,
			WebRequest request) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(value = { VotingSessionNotFoundException.class })
	private ResponseEntity<Response<T>> handleVotingSessionNotFoundException(VotingSessionNotFoundException exception,
			WebRequest request) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = { VotingSessionAlreadyStartedException.class })
	private ResponseEntity<Response<T>> handleVotingSessionAlreadyStartedException(
			VotingSessionAlreadyStartedException exception, WebRequest request) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = { VotingSessionAlreadyEndedException.class })
	private ResponseEntity<Response<T>> handleVotingSessionAlreadyEndedException(
			VotingSessionAlreadyEndedException exception, WebRequest request) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class, JsonParseException.class })
	private ResponseEntity<Response<T>> handleMessageNotReadableException(HttpMessageNotReadableException exception,
			WebRequest request) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	private ResponseEntity<Response<T>> handleConstraintViolationException(ConstraintViolationException exception,
			WebRequest request) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	private ResponseEntity<Response<T>> handleIllegalArgoumentExceptionException(IllegalArgumentException exception,
			WebRequest request) {
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

//	@ExceptionHandler(value = { ServerErrorException.class })
//    	protected ResponseEntity<Response<T>> handleTravelInvalidUpdateException(TravelInvalidUpdateException exception, 
//    		WebRequest request) {
//		
//		Response<T> response = new Response<>();
//		response.addErrorMsgToResponse(exception.getLocalizedMessage());
//		
//		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//    	}
//	
//	@ExceptionHandler(value = { TransactionNotFoundException.class })
//    	protected ResponseEntity<Response<T>> handleTravelNotFoundException(TravelNotFoundException exception, 
//    		WebRequest request) {
//		
//		Response<T> response = new Response<>();
//		response.addErrorMsgToResponse(exception.getLocalizedMessage());
//		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//    	}
//	
//	@ExceptionHandler(value = { HttpClientErrorException.Conflict.class })
//    	protected ResponseEntity<Response<T>> handleConflictException(HttpClientErrorException exception, 
//    		WebRequest request) {
//		
//		Response<T> response = new Response<>();
//		response.addErrorMsgToResponse(exception.getLocalizedMessage());
//		
//		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
//    	}
//	
//	
//	@ExceptionHandler(value = { HttpClientErrorException.TooManyRequests.class })
//    	protected ResponseEntity<Response<T>> handleTooManyRequestException(HttpClientErrorException exception, 
//    		WebRequest request) {
//		
//		Response<T> response = new Response<>();
//		response.addErrorMsgToResponse(exception.getLocalizedMessage());
//		
//		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
//    	}
//	
//	@ExceptionHandler(value = { ServerErrorException.class })
//    	protected ResponseEntity<Response<T>> handleAPIException(ServerErrorException exception, 
//    		WebRequest request) {
//		
//		Response<T> response = new Response<>();
//		response.addErrorMsgToResponse(exception.getLocalizedMessage());
//		
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    	}
//}
}
