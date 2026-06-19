package com.example.soclean.service.user.detail;

import com.example.soclean.usecase.user.detail.UserDetailRequest;
import com.example.soclean.usecase.user.detail.UserDetailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@RestController
@RequiredArgsConstructor
public class UserDetailController{

	private final UserDetailUseCase userDetailUseCase;

	@GetMapping("api/users/detail")
	public ResponseEntity<UserDetailResponse> getUserDetail(UserDetailRequest request) {
		UserDetailWebPresenter presenter = new UserDetailWebPresenter();
		userDetailUseCase.execute(request, presenter);
		return presenter.getResponse();
	}

}
