package com.example.luftest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories(basePackages = "com.example.luftest.repository")
@SpringBootApplication
public class LuftestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuftestApplication.class, args);
	}

}
