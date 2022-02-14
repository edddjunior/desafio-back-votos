package com.southsystem.ApiVoting.test.services.impl.suites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.southsystem.ApiVoting.app.domain.entities.UserEntity;
import com.southsystem.ApiVoting.test.fixtures.Fixtures.UserFixtures;
import com.southsystem.ApiVoting.test.services.impl.setup.UserServiceImplTestSetup;

public class UserServiceImplTest extends UserServiceImplTestSetup {

	@Test
	@Order(1)
	public void testCreateWhenRequiredFieldsAreValid() {
		UserEntity mock = UserFixtures.getRequiredFieldsUserMock();
		UserEntity response = userService.create(mock);
		assertNotNull(response);
		assertEquals(response.getCpf(), mock.getCpf());
	}

	@Test
	@Order(2)
	public void testCreateWhenUserIsInvalid() {
		UserEntity mock = UserFixtures.getInvalidUserMock();
		UserEntity response = null;
		try {
			response = userService.create(mock);
		} catch (ConstraintViolationException e) {
			assertEquals(ConstraintViolationException.class, e.getClass());
		}
		assertNull(response);
	}

	@Test
	@Order(3)
	public void testCreateWhenCpfIsEmpty() {
		UserEntity mock = UserFixtures.getEmptyCPFUserMock();
		UserEntity response = null;
		try {
			response = userService.create(mock);
		} catch (ConstraintViolationException e) {
			assertEquals(ConstraintViolationException.class, e.getClass());
		}
		assertNull(response);
	}
}