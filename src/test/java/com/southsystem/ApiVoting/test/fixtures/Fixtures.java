package com.southsystem.ApiVoting.test.fixtures;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;

public class Fixtures {

	public static class VotingAgendaFixtures {
		public static VotingAgendaEntity getRequiredFieldsVotingAgendaMock() {
			return VotingAgendaEntity.builder().title("Is Metallica better than Megadeth?").build();
		}

		public static VotingAgendaEntity getInvalidVotingAgenda() {
			return VotingAgendaEntity.builder().id((long) 1).build();
		}

		public static VotingAgendaEntity getInvalidTitleVotingAgendaMock() {
			return VotingAgendaEntity.builder().title("Is M").build();
		}
	}
}
