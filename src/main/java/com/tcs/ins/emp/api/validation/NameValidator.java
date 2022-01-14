package com.tcs.ins.emp.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

	private String regExp = "[a-zA-Z]+";

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		return name.matches(regExp);
	}
}