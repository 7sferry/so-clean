package com.example.soclean.service.user.detail;

import com.example.soclean.usecase.user.detail.GetUserDetailGateway;
import com.example.soclean.usecase.user.detail.GetUserDetailUseCase;
import com.example.soclean.usecase.user.detail.GetUserDetailUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Configuration
public class GetUserDetailUseCaseConfig {

	@Bean
	public GetUserDetailUseCase getUserDetailUseCase(GetUserDetailGateway getUserDetailGateway) {
		return new GetUserDetailUseCaseImpl(getUserDetailGateway);
	}

}
