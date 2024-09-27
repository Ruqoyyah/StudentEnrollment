package com.school.sport_enrollment;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.school.sport_enrollment.Utils.ServerProp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "RBACK API", version = "1.0", description = "Documentation RBAC API v1.0"))
public class SportEnrollmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportEnrollmentApplication.class, args);
	}
	@Bean
	public OpenAPI myAPI() {

		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("bearer-key",
								new SecurityScheme()
										.type(SecurityScheme.Type.HTTP)
										.scheme("bearer")
										.bearerFormat("JWT")))
				.addSecurityItem(
						new SecurityRequirement()
								.addList("bearer-jwt", Arrays.asList("read", "write"))
								.addList("bearer-key", Collections.emptyList()))
				.servers(Arrays.asList(
						
						new ServerProp().url("http://localhost:8081").description("Local host URL")
						));

	}
}
