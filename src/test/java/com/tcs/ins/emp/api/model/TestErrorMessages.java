package com.tcs.ins.emp.api.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestErrorMessages {

	@Test
	void testErrorMessage() {
		List<String> actual = Arrays.asList("a", "b", "c");
		List<String> expected = Arrays.asList("a", "b", "c");
		String statusCode = "404";
		String message = "Testing";
		ErrorMessages errorMessages = new ErrorMessages();
		ErrorMessage errorMessage = new ErrorMessage(statusCode, message);
		Assertions.assertArrayEquals(actual.toArray(), expected.toArray());
	}

	@Test
	void getErrorsTest() {
		String statusCode = "404";
		String message = "Testing";
		List<String> ErrorMessage = new ArrayList<String>();
		ErrorMessage.add(statusCode);
		ErrorMessage.add(message);
		ErrorMessages errorMessages = new ErrorMessages();
		errorMessages.getErrors();
	}

	@Test
	void addTest() {
		String statusCode = "404";
		String message = "Testing";
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		list1.add(statusCode);
		list1.add(message);
		list2.add(statusCode);
		list2.add(message);
		ErrorMessages errorMessages = new ErrorMessages();
		errorMessages.add(statusCode,message);
		Assertions.assertArrayEquals(list1.toArray(), list2.toArray());
	}
}