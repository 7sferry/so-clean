package com.example.soclean.usecase.user.registration;

import com.example.soclean.domain.user.UserRecord;
import com.example.soclean.domain.user.registration.RegisterUserRequest;
import com.example.soclean.domain.user.registration.RegisterUserResult;
import lombok.RequiredArgsConstructor;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

	private final RegisterUserGateway registerUserGateway;

	@Override
	public void execute(RegisterUserRequest request, RegisterUserPresenter presenter) {
		validate(request);
		UserRecord savedUser = registerUserGateway.save(new UserRecord(request.username(), request.password()));
		presenter.present(new RegisterUserResult(savedUser));
	}

	private void validate(RegisterUserRequest request) {
		if (request.username() == null || request.username().isBlank()) {
			throw new IllegalArgumentException("Username must not be blank");
		}
		if (request.password() == null || request.password().isBlank()) {
			throw new IllegalArgumentException("Password must not be blank");
		}
		if (request.password().length() < 8) {
			throw new IllegalArgumentException("Password must be at least 8 characters");
		}
		if (!request.password().matches(".*[a-zA-Z].*") || !request.password().matches(".*\\d.*")) {
			throw new IllegalArgumentException("Password must contain at least one letter and one number");
		}
		if (registerUserGateway.existsByUsername(request.username())) {
			throw new IllegalArgumentException("Username already taken: " + request.username());
		}
	}

}
