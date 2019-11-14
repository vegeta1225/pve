package vegeta1225.pve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vegeta1225.pve.constraint.DefaultPassword;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDtoRequest {

	@DefaultPassword
	private String password;
}
