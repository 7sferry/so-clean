package com.example.soclean.service.user.registration;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.usecase.user.registration.RegisterUserPresenter;
import com.example.soclean.usecase.user.registration.RegisterUserResult;
import lombok.Getter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Getter
public class RegisterUserWebPresenter implements RegisterUserPresenter {

	private RegisterUserResponse response;

	@Override
	public void present(RegisterUserResult result) {
		UserDomain user = result.user();
		String status = user.active() ? "Active" : "Inactive";
		response = new RegisterUserResponse(user.username().value(), status);
	}

}
