package com.example.soclean.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.shell.command.annotation.CommandScan;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@SpringBootApplication
@CommandScan
public class UserCliApplication{

	public static void main(String[] args) {
		SpringApplication.run(UserCliApplication.class, args);
	}

}
