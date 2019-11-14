package vegeta1225.pve.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vegeta1225.pve.generator.DefaultPasswordGenerator;
import vegeta1225.pve.generator.PasswordGenerator;

@Configuration
public class TestConfig {

	@Bean
	public PasswordGenerator passwordGenerator() {
		return new DefaultPasswordGenerator();
	}
}
