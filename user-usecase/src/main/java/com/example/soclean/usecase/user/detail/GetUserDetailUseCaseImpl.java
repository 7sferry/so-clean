package com.example.soclean.usecase.user.detail;

import com.example.soclean.domain.user.UserRecord;
import com.example.soclean.domain.user.detail.GetUserDetailRequest;
import com.example.soclean.domain.user.detail.GetUserDetailResult;
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
		if (request.username() == null || request.username().isBlank()) {
			throw new IllegalArgumentException("Username must not be blank");
		}
		UserRecord user = getUserDetailGateway.findByUsername(request.username())
				.orElseThrow(() -> new IllegalArgumentException("User not found: " + request.username()));
		presenter.present(new GetUserDetailResult(user));
	}

}
