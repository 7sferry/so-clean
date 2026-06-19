package com.example.soclean.service.user.registration;

import com.example.soclean.usecase.user.registration.UserRegistrationRequest;
import com.example.soclean.usecase.user.registration.UserRegistrationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@RestController
@RequiredArgsConstructor
public class UserRegistrationController{

	private final UserRegistrationUseCase userRegistrationUseCase;

	@PostMapping("/api/users/register")
	public ResponseEntity<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest request) {
		UserRegistrationWebPresenter presenter = new UserRegistrationWebPresenter();
		userRegistrationUseCase.execute(request, presenter);
		return presenter.getResponse();
	}

}
