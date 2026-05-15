package com.example.soclean.service.user.registration;

import com.example.soclean.usecase.user.registration.RegisterUserGateway;
import com.example.soclean.usecase.user.registration.RegisterUserUseCase;
import com.example.soclean.usecase.user.registration.RegisterUserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Configuration
public class RegisterUserUseCaseConfig {

	@Bean
	public RegisterUserUseCase registerUserUseCase(RegisterUserGateway registerUserGateway) {
		return new RegisterUserUseCaseImpl(registerUserGateway);
	}

}
