package com.example.soclean.service.user;

import com.example.soclean.repository.user.detail.GetUserDetailGatewayImpl;
import com.example.soclean.repository.user.jpa.UserJpaRepository;
import com.example.soclean.repository.user.registration.RegisterUserGatewayImpl;
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
	RegisterUserGateway registerUserGateway(UserJpaRepository userJpaRepository){
		return new RegisterUserGatewayImpl(userJpaRepository);
	}

	@Bean
	GetUserDetailGateway userDetailGateway(UserJpaRepository userJpaRepository){
		return new GetUserDetailGatewayImpl(userJpaRepository);
	}

	@Bean
	public GetUserDetailUseCase getUserDetailUseCase(GetUserDetailGateway getUserDetailGateway) {
		return new GetUserDetailUseCaseImpl(getUserDetailGateway);
	}

}
