package com.BasicsOfSpring.SpringBasics;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBasicsApplication.class, args);
	}
	@Bean
	public OpenAPI getOpenAPI(){
		return new OpenAPI();
	}
}
