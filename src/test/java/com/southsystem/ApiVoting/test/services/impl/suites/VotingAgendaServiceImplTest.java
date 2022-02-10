package com.southsystem.ApiVoting.test.services.impl.suites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.TransactionSystemException;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.test.fixtures.Fixtures.VotingAgendaFixtures;
import com.southsystem.ApiVoting.test.services.impl.setup.VotingAgendaServiceImplTestSetup;

public class VotingAgendaServiceImplTest extends VotingAgendaServiceImplTestSetup {

	@Test
	@Order(1)
	public void testCreateWhenRequiredFieldsAreValid() {
		VotingAgendaEntity mock = VotingAgendaFixtures.getRequiredFieldsVotingAgendaMock();
//		setupSaveMockRepo(mock, null);
		VotingAgendaEntity response = votingAgendaService.create(mock);
		assertNotNull(response);
		assertEquals(response.getTitle(), mock.getTitle());
	}

	@Test
	@Order(2)
	public void testCreateWhenTitleIsInvalid() {
		VotingAgendaEntity mock = VotingAgendaFixtures.getInvalidTitleVotingAgendaMock();
//		setupSaveMockRepo(mock, null);

		VotingAgendaEntity response = null;
		try {
			response = votingAgendaService.create(mock);
		} catch (ConstraintViolationException e) {
			assertEquals(ConstraintViolationException.class, e.getClass());
		}
		assertNull(response);
	}

	@Test
	@Order(3)
	public void testCreateWhenRequiredFieldsAreNotPresent() {
		VotingAgendaEntity mock = VotingAgendaFixtures.getInvalidVotingAgendaMock();
//		setupSaveMockRepo(mock, null);

		VotingAgendaEntity response = null;
		try {
			response = votingAgendaService.create(mock);
		} catch (TransactionSystemException e) {
			assertEquals(TransactionSystemException.class, e.getClass());
		}
		assertNull(response);
	}

	@Test
	@Order(4)
	public void testCreateWhenTitleIsEmpty() {
		VotingAgendaEntity mock = VotingAgendaFixtures.getEmptyTitleVotingAgendaMock();
//		setupSaveMockRepo(mock, null);

		VotingAgendaEntity response = null;
		try {
			response = votingAgendaService.create(mock);
		} catch (ConstraintViolationException e) {
			assertEquals(ConstraintViolationException.class, e.getClass());
		}
		assertNull(response);
	}
}
