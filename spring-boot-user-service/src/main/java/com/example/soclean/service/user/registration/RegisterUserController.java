package com.example.soclean.service.user.registration;

import com.example.soclean.usecase.user.registration.RegisterUserRequest;
import com.example.soclean.usecase.user.registration.RegisterUserUseCase;
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
public class RegisterUserController {

	private final RegisterUserUseCase registerUserUseCase;

	@PostMapping("/api/users/register")
	public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
		RegisterUserWebPresenter presenter = new RegisterUserWebPresenter();
		registerUserUseCase.execute(request, presenter);
		return ResponseEntity.status(HttpStatus.CREATED).body(presenter.getResponse());
	}

}
