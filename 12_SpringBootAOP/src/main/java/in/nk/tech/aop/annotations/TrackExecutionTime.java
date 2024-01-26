package in.nk.tech.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This Annotation finds execution time of methods on which it is annotated.
 * Applicable to only on method level
 *
 * @author  Honnur Ali
 * @since 20.1
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TrackExecutionTime {

}
