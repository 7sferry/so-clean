package com.example.soclean.usecase.user.detail;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.domain.user.UserNotFoundException;
import com.example.soclean.domain.user.Username;
import lombok.RequiredArgsConstructor;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@RequiredArgsConstructor
public class UserDetailDefaultUseCase implements UserDetailUseCase{

	private final UserDetailGateway userDetailGateway;

	@Override
	public void execute(UserDetailRequest request, UserDetailPresenter presenter) {
		Username username = new Username(request.username());
		UserDomain user = userDetailGateway.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(username));
		presenter.present(new UserDetailResult(user));
	}

}
