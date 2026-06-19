package com.example.soclean.service.user.detail;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.usecase.user.detail.UserDetailPresenter;
import com.example.soclean.usecase.user.detail.UserDetailResult;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Getter
public class UserDetailWebPresenter implements UserDetailPresenter{

	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm").withZone(ZoneId.of("Asia/Jakarta"));

	private ResponseEntity<UserDetailResponse> response;

	@Override
	public void present(UserDetailResult result) {
		UserDomain user = result.user();
		String status = user.active() ? "Active" : "Inactive";
		String createdAt = DATE_FORMATTER.format(user.createdAt());
		response = ResponseEntity.ok(new UserDetailResponse(user.usernameValue(), status, createdAt));
	}

}
