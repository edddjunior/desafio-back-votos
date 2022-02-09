package com.southsystem.ApiVoting.test;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;

public class Fixtures {

	public static class VotingAgendaFixtures {
		public static VotingAgendaEntity getRequiredFieldsVotingAgendaMock() {
			return VotingAgendaEntity.builder().id((long) 1).title("Is Metallica better than Megadeth?").build();
		}
	}
}
