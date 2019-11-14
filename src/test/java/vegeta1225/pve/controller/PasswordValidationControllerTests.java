package vegeta1225.pve.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import vegeta1225.pve.dto.PasswordDtoRequest;
import vegeta1225.pve.generator.PasswordGenerator;

@SpringBootTest
public class PasswordValidationControllerTests {

	@Autowired
	private PasswordValidationController	controller;

	@MockBean
	private PasswordDtoRequest				dto;

	@Autowired
	private PasswordGenerator				passwordGenerator;

	@Test
	public void testNormalCase() throws Exception {
		Mockito.when(dto.getPassword()).thenReturn(passwordGenerator.getNormalPassword());
		ResponseEntity<Void> result = controller.validate(dto);
		Assertions.assertTrue(HttpStatus.NO_CONTENT == result.getStatusCode());
	}
}