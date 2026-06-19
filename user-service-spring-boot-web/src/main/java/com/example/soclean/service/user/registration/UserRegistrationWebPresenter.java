package com.example.soclean.service.user.registration;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.usecase.user.registration.UserRegistrationPresenter;
import com.example.soclean.usecase.user.registration.UserRegistrationResult;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Getter
public class UserRegistrationWebPresenter implements UserRegistrationPresenter{

	private ResponseEntity<UserRegistrationResponse> response;

	@Override
	public void present(UserRegistrationResult result) {
		UserDomain user = result.user();
		String status = user.active() ? "Active" : "Inactive";
		response = ResponseEntity.status(HttpStatus.CREATED).body(new UserRegistrationResponse(user.usernameValue(), status));
	}

}
