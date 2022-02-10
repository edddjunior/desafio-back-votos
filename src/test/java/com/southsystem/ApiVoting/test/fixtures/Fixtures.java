package com.southsystem.ApiVoting.test.fixtures;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.domain.entities.VotingSessionEntity;

public class Fixtures {

	public static class VotingSessionFixtures {

		public static VotingSessionEntity getNoDurationVotingSessionMock() {
			return VotingSessionEntity.builder().build();
		}

		public static VotingSessionEntity getInvalidDurationVotingSessionMock() {
			return VotingSessionEntity.builder().durationInMinutes((long) -12).build();
		}

		public static VotingSessionEntity getNoAgendaVotingSessionMock() {
			return VotingSessionEntity.builder().durationInMinutes((long) 10).build();
		}

		public static VotingSessionEntity getRequiredFieldsVotingSessionMock() {
			return VotingSessionEntity.builder().agenda(VotingAgendaFixtures.getCompleteCreatedVotingAgendaMock())
					.durationInMinutes((long) 10).build();
		}

		public static VotingAgendaEntity getInvalidVotingAgendaMock() {
			return VotingAgendaEntity.builder().id((long) 1).build();
		}

		public static VotingAgendaEntity getInvalidTitleVotingAgendaMock() {
			return VotingAgendaEntity.builder().title("Is M").build();
		}

		public static VotingAgendaEntity getEmptyTitleVotingAgendaMock() {
			return VotingAgendaEntity.builder().title("Is M").build();
		}
	}

	public static class VotingAgendaFixtures {
		public static VotingAgendaEntity getRequiredFieldsVotingAgendaMock() {
			return VotingAgendaEntity.builder().title("Is Metallica better than Megadeth?").build();
		}

		public static VotingAgendaEntity getInvalidVotingAgendaMock() {
			return VotingAgendaEntity.builder().id((long) 1).build();
		}

		public static VotingAgendaEntity getInvalidTitleVotingAgendaMock() {
			return VotingAgendaEntity.builder().title("Is M").build();
		}

		public static VotingAgendaEntity getEmptyTitleVotingAgendaMock() {
			return VotingAgendaEntity.builder().title("Is M").build();
		}

		public static VotingAgendaEntity getCompleteCreatedVotingAgendaMock() {
			return VotingAgendaEntity.builder().title("Is Metallica better than Megadeth?").build();
		}
	}

	public static class UserFixtures {
		public static UserEntity getRequiredFieldsUserMock() {
			return UserEntity.builder().cpf("816.244.280-45").build();
		}

		public static UserEntity getInvalidUserMock() {
			return UserEntity.builder().name("John Doe").build();
		}

		public static UserEntity getEmptyCPFUserMock() {
			return UserEntity.builder().cpf("").build();
		}
	}
}
