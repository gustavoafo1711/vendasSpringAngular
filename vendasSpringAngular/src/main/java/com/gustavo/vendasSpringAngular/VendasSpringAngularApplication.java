package com.gustavo.vendasSpringAngular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasSpringAngularApplication {
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "hello world!";
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasSpringAngularApplication.class, args);
	}

}
