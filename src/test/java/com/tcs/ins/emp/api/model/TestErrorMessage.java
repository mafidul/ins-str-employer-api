package com.tcs.ins.emp.api.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class TestErrorMessage {

	@Test
	void getStatusCodeTest() {
		String statusCode = "404";
		ErrorMessage errorMessage = new ErrorMessage(statusCode, null);
		errorMessage.getStatusCode();
		assertThat(errorMessage.getStatusCode(), equalTo(statusCode));
	}

	@Test
	void getErrorMessageTest() {
		String message = "Testing";
		ErrorMessage errorMessage = new ErrorMessage(null, message);
		errorMessage.getErrorMessage();
		assertThat(errorMessage.getErrorMessage(), equalTo(message));
	}
}