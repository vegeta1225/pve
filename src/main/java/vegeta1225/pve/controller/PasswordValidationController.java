package vegeta1225.pve.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import vegeta1225.pve.dto.PasswordDtoRequest;

@RestController
public class PasswordValidationController {

	@ApiOperation(value = "validate each passwords by pre-defined rules")
	@ApiResponses(
			value = {
					@ApiResponse(code = 400, message = "Bad Request"),
					@ApiResponse(code = 500, message = "Internal Server Error") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PostMapping(value = "/api/v1/passwords/validate", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> validate(@RequestBody @Valid PasswordDtoRequest dto) {
		return ResponseEntity.noContent().build();
	}
}
