package com.southsystem.ApiVoting.test.services.impl.suites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.test.Fixtures.VotingAgendaFixtures;
import com.southsystem.ApiVoting.test.services.impl.setup.VotingAgendaServiceImplTestSetup;

public class VotingAgendaServiceImplTest extends VotingAgendaServiceImplTestSetup {

	@Test
	@Order(1)
	public void testCreateWhenTitleIsValid() {
		VotingAgendaEntity mock = VotingAgendaFixtures.getRequiredFieldsVotingAgendaMock();
//		setupSaveMockRepo(mock, null);
		VotingAgendaEntity response = votingAgendaService.create(mock);
		assertNotNull(response);
		assertEquals(response.getTitle(), mock.getTitle());
	}

	@Test
	@Order(2)
	public void testCreateWhenTitleIsInvalid() {
		VotingAgendaEntity mock = VotingAgendaFixtures.getRequiredFieldsVotingAgendaMock();
//		setupSaveMockRepo(mock, null);
		VotingAgendaEntity response = votingAgendaService.create(mock);
		assertNotNull(response);
		assertEquals(response.getTitle(), mock.getTitle());
	}
}
