package com.example.soclean.usecase.user.registration;

import com.example.soclean.domain.user.Password;
import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.domain.user.Username;
import com.example.soclean.domain.user.UsernameAlreadyTakenException;
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
		Username username = new Username(request.username());
		Password password = new Password(request.password());
		if (registerUserGateway.existsByUsername(username)) {
			throw new UsernameAlreadyTakenException(username);
		}
		UserDomain saved = registerUserGateway.save(UserDomain.register(username, password));
		presenter.present(new RegisterUserResult(saved));
	}

}
