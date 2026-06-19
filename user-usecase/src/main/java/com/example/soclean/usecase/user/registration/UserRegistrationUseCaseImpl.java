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
public class UserRegistrationUseCaseImpl implements UserRegistrationUseCase{

	private final UserRegistrationGateway userRegistrationGateway;

	@Override
	public void execute(UserRegistrationRequest request, UserRegistrationPresenter presenter) {
		Username username = new Username(request.username());
		Password password = new Password(request.password());
		if (userRegistrationGateway.existsByUsername(username)) {
			throw new UsernameAlreadyTakenException(username);
		}
		UserDomain saved = userRegistrationGateway.save(UserDomain.register(username, password));
		presenter.present(new UserRegistrationResult(saved));
	}

}
