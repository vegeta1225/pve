package vegeta1225.pve.constraint;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import lombok.extern.slf4j.Slf4j;
import vegeta1225.pve.constant.MessageConstant;
import vegeta1225.pve.dto.PasswordDtoRequest;
import vegeta1225.pve.generator.PasswordGenerator;

@SpringBootTest
public class ConstraintTests {

	private static ValidatorFactory	validatorFactory;
	private static Validator		validator;

	@Autowired
	private PasswordGenerator		passwordGenerator;

	@BeforeAll
	public static void init() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@AfterAll
	public static void destroy() {
		validatorFactory.close();
	}

	@Test
	public void testShorterLength() {
		Assertions.assertTrue(validate(passwordGenerator.getShorterPassword()).stream()
				.anyMatch(constraint -> constraint.getMessageTemplate().contains(MessageConstant.CONSTRAINT_KEY_SIZE)));
	}

	@Test
	public void testLongerLength() {
		Assertions.assertTrue(validate(passwordGenerator.getLongerPassword()).stream()
				.anyMatch(constraint -> constraint.getMessageTemplate().contains(MessageConstant.CONSTRAINT_KEY_SIZE)));
	}

	@Test
	public void testAtLeastOneLowerCaseLetter() {
		Assertions.assertTrue(validate(passwordGenerator.getNoLowerCaseLetterPassword()).stream()
				.anyMatch(constraint -> constraint.getMessageTemplate().contains(MessageConstant.CONSTRAINT_KEY_AT_LEAST_ONE_LOWER_CASE_LETTER)));
	}

	@Test
	public void testAtLeastOneDigit() {
		Assertions.assertTrue(validate(passwordGenerator.getNoDigitPassword()).stream()
				.anyMatch(constraint -> constraint.getMessageTemplate().contains(MessageConstant.CONSTRAINT_KEY_AT_LEAST_ONE_DIGIT)));
	}

	@Test
	public void testNoFollowedRepeatedSubsequence() {
		Assertions.assertTrue(validate(passwordGenerator.getFollowedRepeatedSubsequencePassword()).stream()
				.anyMatch(constraint -> constraint.getMessageTemplate().contains(MessageConstant.CONSTRAINT_KEY_NO_FOLLOWED_REPEATED_SUBSEQUENCE)));
	}

	@Test
	public void testNoFollowedRepeatedSequence() {
		Assertions.assertTrue(validate(passwordGenerator.getFollowedRepeatedSequencePassword()).stream()
				.anyMatch(constraint -> constraint.getMessageTemplate().contains(MessageConstant.CONSTRAINT_KEY_NO_FOLLOWED_REPEATED_SUBSEQUENCE)));
	}

	private Set<ConstraintViolation<PasswordDtoRequest>> validate(String password) {
		PasswordDtoRequest dto = PasswordDtoRequest.builder()
				.password(password)
				.build();

		return validator.validate(dto);
	}
}
