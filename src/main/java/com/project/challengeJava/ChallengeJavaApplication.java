package com.project.challengeJava;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Java Spring Boot (API)",version = "1.0",
		description = "Alkemy's Java Backend Challenge"))
public class ChallengeJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeJavaApplication.class, args);
	}

}
