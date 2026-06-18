package com.example.soclean.service.user.detail;

import com.example.soclean.usecase.user.detail.GetUserDetailRequest;
import com.example.soclean.usecase.user.detail.GetUserDetailUseCase;
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
public class GetUserDetailController {

	private final GetUserDetailUseCase getUserDetailUseCase;

	@GetMapping("api/users/detail")
	public ResponseEntity<GetUserDetailResponse> getUserDetail(GetUserDetailRequest request) {
		GetUserDetailPresenterImpl presenter = new GetUserDetailPresenterImpl();
		getUserDetailUseCase.execute(request, presenter);
		return ResponseEntity.ok(presenter.getResponse());
	}

}
