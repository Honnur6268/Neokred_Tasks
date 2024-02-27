package in.nk.tech.validation.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import in.nk.tech.validation.validations.validators.AgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, LOCAL_VARIABLE })
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidateAge {

	public int min() default 15;

	public int max() default 100;

	String message() default "The age must be, age >= 15 or age <= 100";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
