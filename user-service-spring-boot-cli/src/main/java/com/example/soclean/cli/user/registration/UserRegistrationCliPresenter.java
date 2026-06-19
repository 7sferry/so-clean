package com.example.soclean.cli.user.registration;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.usecase.user.registration.UserRegistrationPresenter;
import com.example.soclean.usecase.user.registration.UserRegistrationResult;
import lombok.Getter;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Getter
public class UserRegistrationCliPresenter implements UserRegistrationPresenter{

	private String output;

	@Override
	public void present(UserRegistrationResult result) {
		UserDomain user = result.user();
		output = "User registered successfully!\n"
				+ "  Username  : " + user.username().value() + '\n'
				+ "  Status    : " + (user.active() ? "Active" : "Inactive") + '\n';
	}

}
