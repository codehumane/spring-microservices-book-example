package codehumane.legacyrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity
@SpringBootApplication
public class LegacyrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegacyrestApplication.class, args);
	}
}
