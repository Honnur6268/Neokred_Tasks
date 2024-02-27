package in.nk.tech.validation.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import in.nk.tech.validation.validations.validators.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER })
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
	
	public String message() default "Email already present, should be unique";

	//Represents at what phase this annotation should execute(Creation, Updation, Deletion)
	Class<?>[] groups() default {};

	//Defines any additional data
	Class<? extends Payload>[] payload() default {};
}
