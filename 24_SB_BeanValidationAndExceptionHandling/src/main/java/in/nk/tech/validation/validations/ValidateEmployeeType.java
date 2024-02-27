package in.nk.tech.validation.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import in.nk.tech.validation.validations.validators.EmployeeTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER })
@Documented
@Constraint(validatedBy = EmployeeTypeValidator.class)
public @interface ValidateEmployeeType {

	public String message() default "Invalid EmployeeType : type must be either Permanent or Vendor";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
