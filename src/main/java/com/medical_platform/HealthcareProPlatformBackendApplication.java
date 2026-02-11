package com.medical_platform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class HealthcareProPlatformBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareProPlatformBackendApplication.class, args);
		
		
	}
	@Bean
    public CommandLineRunner showPort(Environment env) {
        return args -> {
            String port = env.getProperty("local.server.port");
            System.out.println("=================================");
            System.out.println("Server is running on port: " + port);
            System.out.println("=================================");
        };
    }
}
