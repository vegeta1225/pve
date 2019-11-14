package vegeta1225.pve.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import vegeta1225.pve.validator.NoFollowedRepeatedSubsequenceValidator;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { NoFollowedRepeatedSubsequenceValidator.class })
@Documented
public @interface NoFollowedRepeatedSubsequence {
	String message() default "{vegeta1225.pve.constraints.NoFollowedRepeatedSubsequence.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
