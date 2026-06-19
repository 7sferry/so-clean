package com.example.soclean.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@SpringBootApplication(scanBasePackages = "com.example.soclean")
@EntityScan("com.example.soclean.repository")
@EnableJpaRepositories("com.example.soclean.repository")
public class UserWebApplication{

	public static void main(String[] args) {
		SpringApplication.run(UserWebApplication.class, args);
	}

}
