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
public class GetUserDetailUseCaseImpl implements GetUserDetailUseCase {

	private final GetUserDetailGateway getUserDetailGateway;

	@Override
	public void execute(GetUserDetailRequest request, GetUserDetailPresenter presenter) {
		Username username = new Username(request.username());
		UserDomain user = getUserDetailGateway.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(username));
		presenter.present(new GetUserDetailResult(user));
	}

}
