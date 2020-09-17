package com.techluana.encurtadorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EncurtadorAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncurtadorAppApplication.class, args);
	}

}
