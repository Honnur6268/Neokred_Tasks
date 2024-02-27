package in.nk.tech.validation.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER, LOCAL_VARIABLE })
@Constraint(validatedBy = {})
@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be a 10-digit numeric value")
//^(\\+\\d{1,3}[- ]?)?\\d{10}$ --> with country code
public @interface ValidateMobile {

	String message() default "Invalid mobile number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
