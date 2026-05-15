package com.example.soclean.service.user;

import com.example.soclean.usecase.user.detail.GetUserDetailGateway;
import com.example.soclean.usecase.user.detail.GetUserDetailUseCase;
import com.example.soclean.usecase.user.detail.GetUserDetailUseCaseImpl;
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
public class UserConfig {

	@Bean
	public RegisterUserUseCase registerUserUseCase(RegisterUserGateway registerUserGateway) {
		return new RegisterUserUseCaseImpl(registerUserGateway);
	}

	@Bean
	public GetUserDetailUseCase getUserDetailUseCase(GetUserDetailGateway getUserDetailGateway) {
		return new GetUserDetailUseCaseImpl(getUserDetailGateway);
	}

}
