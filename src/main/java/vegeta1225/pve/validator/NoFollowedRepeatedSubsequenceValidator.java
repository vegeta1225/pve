package vegeta1225.pve.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import vegeta1225.pve.constraint.NoFollowedRepeatedSubsequence;

/**
 * A show case how to use custom annotation with custom validator.
 * You could use @Pattern instead of this
 * 
 * @author vegeta1225
 *
 */
public class NoFollowedRepeatedSubsequenceValidator implements ConstraintValidator<NoFollowedRepeatedSubsequence, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		// check all subsequence by back reference
		Pattern pattern = Pattern.compile(".*(.+)\\1.*");
		
		return !pattern.matcher(value).matches();
	}

}
