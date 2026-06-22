package com.example.soclean.service.user;

import com.example.soclean.repository.user.detail.UserDetailJpaGateway;
import com.example.soclean.repository.user.jpa.UserJpaRepository;
import com.example.soclean.repository.user.registration.UserRegistrationJpaGateway;
import com.example.soclean.usecase.user.detail.UserDetailGateway;
import com.example.soclean.usecase.user.detail.UserDetailUseCase;
import com.example.soclean.usecase.user.detail.UserDetailDefaultUseCase;
import com.example.soclean.usecase.user.registration.UserRegistrationGateway;
import com.example.soclean.usecase.user.registration.UserRegistrationUseCase;
import com.example.soclean.usecase.user.registration.UserRegistrationDefaultUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Configuration
public class UserWebConfig{

	@Bean
	public UserRegistrationUseCase userRegistrationUseCase(UserRegistrationGateway userRegistrationGateway) {
		return new UserRegistrationDefaultUseCase(userRegistrationGateway);
	}

	@Bean
	UserRegistrationGateway userRegistrationGateway(UserJpaRepository userJpaRepository){
		return new UserRegistrationJpaGateway(userJpaRepository);
	}

	@Bean
	UserDetailGateway userDetailGateway(UserJpaRepository userJpaRepository){
		return new UserDetailJpaGateway(userJpaRepository);
	}

	@Bean
	public UserDetailUseCase userDetailUseCase(UserDetailGateway userDetailGateway) {
		return new UserDetailDefaultUseCase(userDetailGateway);
	}

}
