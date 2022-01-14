package com.tcs.ins.emp.api.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

@ExtendWith({ MockitoExtension.class })
public class NameValidatorTest {

	@Mock
	ConstraintValidatorContext constraintValidatorContext;

	@Test
	void nameValidatorTest() {
		NameValidator login = new NameValidator();
		String name = "XYZ";
		assertTrue(login.isValid(name, constraintValidatorContext));
	}
}
