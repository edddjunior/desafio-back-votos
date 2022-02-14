package com.southsystem.ApiVoting.test.services.impl.suites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;
import com.southsystem.ApiVoting.test.fixtures.Fixtures.VotingAgendaFixtures;
import com.southsystem.ApiVoting.test.fixtures.Fixtures.VotingSessionFixtures;
import com.southsystem.ApiVoting.test.services.impl.setup.VotingSessionServiceImplTestSetup;

public class VotingSessionServiceImplTest extends VotingSessionServiceImplTestSetup {

	@Test
	@Order(1)
	public void testCreateWhenRequiredFieldsAreValid() {
		VotingSessionEntity mockSession = VotingSessionFixtures.getNoAgendaVotingSessionMock();
		VotingAgendaEntity mockAgenda = VotingAgendaFixtures.getCompleteCreatedVotingAgendaMock();
		VotingAgendaEntity agenda = setupCreateValidExistingAgenda(mockAgenda, null);
		mockSession.setVotingAgenda(agenda);
		VotingSessionEntity response = votingSessionService.create(mockSession);
		assertNotNull(response);
		assertEquals(mockSession.getDurationInMinutes(), response.getDurationInMinutes());
		assertEquals(response.getStartDatetime().plusMinutes(mockSession.getDurationInMinutes()),
				response.getEndDateTime());
	}

	@Test
	@Order(2)
	public void testCreateWhenDurationIsNull() {
		VotingSessionEntity mockSession = VotingSessionFixtures.getNoDurationVotingSessionMock();
		VotingAgendaEntity mockAgenda = VotingAgendaFixtures.getCompleteCreatedVotingAgendaMock();
		VotingAgendaEntity agenda = setupCreateValidExistingAgenda(mockAgenda, null);
		mockSession.setVotingAgenda(agenda);
		VotingSessionEntity response = votingSessionService.create(mockSession);
		assertNotNull(response);
		assertEquals((long) 1, response.getDurationInMinutes());
		assertEquals(response.getStartDatetime().plusMinutes(response.getDurationInMinutes()),
				response.getEndDateTime());
	}

	@Test
	@Order(3)
	public void testCreateWhenDurationIsInvalid() {
		VotingSessionEntity mockSession = VotingSessionFixtures.getInvalidDurationVotingSessionMock();
		VotingAgendaEntity mockAgenda = VotingAgendaFixtures.getCompleteCreatedVotingAgendaMock();
		VotingAgendaEntity agenda = setupCreateValidExistingAgenda(mockAgenda, null);
		mockSession.setVotingAgenda(agenda);
		VotingSessionEntity response = null;
		try {
			response = votingSessionService.create(mockSession);
		} catch (ConstraintViolationException e) {
			assertEquals(ConstraintViolationException.class, e.getClass());
		}
		assertNull(response);
	}
}
