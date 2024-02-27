package in.nk.tech.validation.validations.validators;

import in.nk.tech.validation.validations.ValidateAge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<ValidateAge, Integer> {

	private int min;
	private int max;

	@Override
	public void initialize(ValidateAge parameters) {
		min = parameters.min();
		max = parameters.max();
	}

	@Override
	public boolean isValid(Integer age, ConstraintValidatorContext context) {
		if (age == null) {
			return true;
		}

		return age >= min && age <= max;
	}

}
