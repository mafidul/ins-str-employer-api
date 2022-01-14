package com.tcs.ins.emp.api.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({ MockitoExtension.class })
public class PasswordConstraintValidatorTest {
	@Mock
	ConstraintValidatorContext constraintValidatorContext;

	@Test
	void passwordValidationTest() {
		PasswordConstraintValidator passwordConstraintValidator = new PasswordConstraintValidator();
		String password = "Admin@2021";
		assertTrue(passwordConstraintValidator.isValid(password, constraintValidatorContext));
	}

	@Test
	void passwordInvalidTest() {
		String password = "Admi";
		// PasswordValidator validator;
		PasswordConstraintValidator passwordConstraintValidator = new PasswordConstraintValidator();
		// RuleResult result = validator.validate(new PasswordData(password));
		// List<String> messages = validator.getMessages(result);
		// String messageTemplate = messages.stream().collect(Collectors.joining(","));
		// constraintValidatorContext.buildConstraintViolationWithTemplate(any()).addConstraintViolation()
		// .disableDefaultConstraintViolation();
		// assertTrue(passwordConstraintValidator.isValid(password,
		// constraintValidatorContext));
	}
}