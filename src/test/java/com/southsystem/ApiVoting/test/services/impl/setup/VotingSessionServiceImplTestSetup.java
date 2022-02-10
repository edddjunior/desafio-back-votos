package com.southsystem.ApiVoting.test.services.impl.setup;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.southsystem.ApiVoting.app.domain.entities.VotingAgendaEntity;
import com.southsystem.ApiVoting.app.domain.repositories.VotingAgendaRepository;
import com.southsystem.ApiVoting.app.services.impl.VotingSessionServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class VotingSessionServiceImplTestSetup {

	@Autowired
	protected VotingSessionServiceImpl votingSessionService;

	@Autowired
	protected VotingAgendaRepository votingAgendaRepository;

	protected VotingAgendaEntity setupCreateValidExistingAgenda(VotingAgendaEntity data, Exception e) {
		return votingAgendaRepository.save(data);
//		if (response == null && e != null) {
//			BDDMockito.given(votingAgendaRepository.save(Mockito.any(VotingAgendaEntity.class))).willThrow(e);
//		} else {
//			BDDMockito.given(votingAgendaRepository.save(Mockito.any(VotingAgendaEntity.class))).willReturn(response);
//		}
	}
}
