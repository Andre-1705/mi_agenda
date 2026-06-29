package com.example.miagendaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiAgendaWebApplication {

	public static void main(String[] args) {
		System.out.println("✅ Spring Boot arrancó correctamente");
		SpringApplication.run(MiAgendaWebApplication.class, args);
	}

}
