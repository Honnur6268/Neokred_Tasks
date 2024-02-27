package in.nk.tech.validation.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import in.nk.tech.validation.validations.validators.CustomLengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER })
@Constraint(validatedBy = CustomLengthValidator.class)
public @interface ValidateLength {
	
	public int min() default 4;
	
	public int max() default Integer.MAX_VALUE;
	
	String message() default "The Length of Name must be at least 4";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
