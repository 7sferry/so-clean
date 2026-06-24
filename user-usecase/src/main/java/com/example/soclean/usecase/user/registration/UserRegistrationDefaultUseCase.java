package com.example.soclean.usecase.user.registration;

import com.example.soclean.domain.user.*;
import lombok.RequiredArgsConstructor;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@RequiredArgsConstructor
public class UserRegistrationDefaultUseCase implements UserRegistrationUseCase{

	private final UserRegistrationGateway userRegistrationGateway;

	@Override
	public void execute(UserRegistrationRequest request, UserRegistrationPresenter presenter) {
		Username username = new Username(request.username());
		BirthDate birthDate = new BirthDate(request.birthDate());
		if (userRegistrationGateway.existsByUsername(username)) {
			throw new UsernameAlreadyTakenException(username);
		}
		UserDomain saved = userRegistrationGateway.save(UserDomain.register(username, birthDate));
		presenter.present(new UserRegistrationResult(saved));
	}

}
