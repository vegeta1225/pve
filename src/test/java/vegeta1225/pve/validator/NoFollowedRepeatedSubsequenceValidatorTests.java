package vegeta1225.pve.validator;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import vegeta1225.pve.generator.PasswordGenerator;

@SpringBootTest
public class NoFollowedRepeatedSubsequenceValidatorTests {

	private final NoFollowedRepeatedSubsequenceValidator	validator	= new NoFollowedRepeatedSubsequenceValidator();

	@MockBean
	private ConstraintValidatorContext						context;

	@Autowired
	private PasswordGenerator								passwordGenerator;
	
	@Test
	public void testNormalCase() {
		Assertions.assertTrue(validator.isValid(passwordGenerator.getNormalPassword(), context));
	}

	@Test
	public void testFollowedRepeatedSequenceCase() {
		Assertions.assertFalse(validator.isValid(passwordGenerator.getFollowedRepeatedSequencePassword(), context));
	}

	@Test
	public void testFollowedRepeatedSubsequenceCase() {
		Assertions.assertFalse(validator.isValid(passwordGenerator.getFollowedRepeatedSubsequencePassword(), context));
	}
}
