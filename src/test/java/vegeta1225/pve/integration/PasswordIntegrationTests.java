package vegeta1225.pve.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import vegeta1225.pve.constant.MessageConstant;
import vegeta1225.pve.dto.PasswordDtoRequest;
import vegeta1225.pve.generator.PasswordGenerator;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordIntegrationTests {

	@Autowired
	private MockMvc				mockMvc;

	@Autowired
	private ObjectMapper		mapper;

	@Autowired
	private PasswordGenerator	passwordGenerator;

	private static final String	API_URL_PASSWORD_VALIDATE_V1	= "/api/v1/passwords/validate";

	@Test
	public void testNormalCase() throws Exception {
		post(passwordGenerator.getNormalPassword(), HttpStatus.NO_CONTENT);
	}

	@Test
	public void testShorterLength() throws Exception {
		MvcResult result = post(passwordGenerator.getShorterPassword(), HttpStatus.BAD_REQUEST);
		Assertions.assertTrue(result.getResolvedException().getMessage().contains(MessageConstant.CONSTRAINT_KEY_SIZE));
	}

	@Test
	public void testLongerLength() throws Exception {
		MvcResult result = post(passwordGenerator.getLongerPassword(), HttpStatus.BAD_REQUEST);
		Assertions.assertTrue(result.getResolvedException().getMessage().contains(MessageConstant.CONSTRAINT_KEY_AT_LEAST_ONE_LOWER_CASE_LETTER));
	}

	@Test
	public void testAtLeastOneLowerCaseLetter() throws Exception {
		MvcResult result = post(passwordGenerator.getNoLowerCaseLetterPassword(), HttpStatus.BAD_REQUEST);
		Assertions.assertTrue(result.getResolvedException().getMessage().contains(MessageConstant.CONSTRAINT_KEY_AT_LEAST_ONE_LOWER_CASE_LETTER));
	}

	@Test
	public void testAtLeastOneDigit() throws Exception {
		MvcResult result = post(passwordGenerator.getNoDigitPassword(), HttpStatus.BAD_REQUEST);
		Assertions.assertTrue(result.getResolvedException().getMessage().contains(MessageConstant.CONSTRAINT_KEY_AT_LEAST_ONE_DIGIT));
	}

	@Test
	public void testNoFollowedRepeatedSubsequence() throws Exception {
		MvcResult result = post(passwordGenerator.getFollowedRepeatedSubsequencePassword(), HttpStatus.BAD_REQUEST);
		Assertions.assertTrue(result.getResolvedException().getMessage().contains(MessageConstant.CONSTRAINT_KEY_NO_FOLLOWED_REPEATED_SUBSEQUENCE));
	}

	@Test
	public void testNoFollowedRepeatedSequence() throws Exception {
		MvcResult result = post(passwordGenerator.getFollowedRepeatedSequencePassword(), HttpStatus.BAD_REQUEST);
		Assertions.assertTrue(result.getResolvedException().getMessage().contains(MessageConstant.CONSTRAINT_KEY_NO_FOLLOWED_REPEATED_SUBSEQUENCE));
	}

	/**
	 * default post template: create post body, do post, check http status with expectation and return result for further usage.
	 * 
	 * @param password
	 * @param expectedHttpStatus
	 * @return
	 * @throws Exception
	 */
	private MvcResult post(String password, HttpStatus expectedHttpStatus) throws Exception {
		PasswordDtoRequest dto = PasswordDtoRequest.builder()
				.password(password)
				.build();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(API_URL_PASSWORD_VALIDATE_V1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto));

		return mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is(expectedHttpStatus.value()))
				.andReturn();
	}
}