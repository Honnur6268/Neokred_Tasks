package in.nk.tech.validation.validations.validators;

import in.nk.tech.validation.validations.ValidateLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomLengthValidator implements ConstraintValidator<ValidateLength, String> {

	private int min;
	private int max;

	@Override
	public void initialize(ValidateLength parameters) {
		min = parameters.min();
		max = parameters.max();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		int length = value.length();
		return length >= min && length <= max;
	}

}
