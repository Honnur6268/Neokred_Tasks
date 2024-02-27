package in.nk.tech.validation.validations.validators;

import java.util.Arrays;
import java.util.List;

import in.nk.tech.validation.validations.ValidateEmployeeType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeTypeValidator implements ConstraintValidator<ValidateEmployeeType, String> {

	@Override
	public boolean isValid(String employeeType, ConstraintValidatorContext context) {
		List<String> employeeTypes = Arrays.asList("Permanent", "Vendor");
		return employeeTypes.contains(employeeType);
	}

}
