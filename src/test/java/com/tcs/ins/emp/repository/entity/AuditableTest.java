package com.tcs.ins.emp.repository.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({ MockitoExtension.class })
public class AuditableTest {

	private static final String REQUEST_PARAM_LAST_MODIFIED_BY = "Admin";

	@Test
	void idTest() {
		Login login = new Login();
		login.setCreatedBy(REQUEST_PARAM_LAST_MODIFIED_BY);
		assertThat(login.getCreatedBy()).isEqualTo(REQUEST_PARAM_LAST_MODIFIED_BY);
	}

	@Test
	void lastModifiedByTest() {
		Login login = new Login();
		login.setLastModifiedBy(REQUEST_PARAM_LAST_MODIFIED_BY);
		assertThat(login.getLastModifiedBy()).isEqualTo(REQUEST_PARAM_LAST_MODIFIED_BY);
	}

	@Test
	void createdDateTest() {
		Login login = new Login();
		LocalDateTime rightNow = LocalDateTime.now();
		login.setCreatedDate(rightNow);
		assertThat(login.getCreatedDate()).isNotNull();
	}

	@Test
	void lastModifiedDateTest() {
		Login login = new Login();
		LocalDateTime rightNow = LocalDateTime.now();
		login.setLastModifiedDate(rightNow);
		assertThat(login.getLastModifiedDate()).isNotNull();
	}
}
