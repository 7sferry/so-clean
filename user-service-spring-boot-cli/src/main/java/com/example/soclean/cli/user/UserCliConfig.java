package com.example.soclean.cli.user;

import com.example.soclean.repository.user.detail.UserDetailJooqGateway;
import com.example.soclean.repository.user.registration.UserRegistrationJooqGateway;
import com.example.soclean.usecase.user.detail.UserDetailGateway;
import com.example.soclean.usecase.user.detail.UserDetailUseCase;
import com.example.soclean.usecase.user.detail.UserDetailUseCaseImpl;
import com.example.soclean.usecase.user.registration.UserRegistrationGateway;
import com.example.soclean.usecase.user.registration.UserRegistrationUseCase;
import com.example.soclean.usecase.user.registration.UserRegistrationUseCaseImpl;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Configuration
public class UserCliConfig{

	@Bean
	public UserRegistrationUseCase userRegistrationUseCase(UserRegistrationGateway userRegistrationGateway) {
		return new UserRegistrationUseCaseImpl(userRegistrationGateway);
	}

	@Bean
	UserRegistrationGateway userRegistrationGateway(DSLContext dslContext){
		return new UserRegistrationJooqGateway(dslContext);
	}

	@Bean
	UserDetailGateway userDetailGateway(DSLContext dslContext){
		return new UserDetailJooqGateway(dslContext);
	}

	@Bean
	public UserDetailUseCase getUserDetailUseCase(UserDetailGateway userDetailGateway) {
		return new UserDetailUseCaseImpl(userDetailGateway);
	}

}
