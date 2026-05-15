package com.example.soclean.service.user.registration;

import com.example.soclean.domain.user.UserRecord;
import com.example.soclean.domain.user.registration.RegisterUserResponse;
import com.example.soclean.domain.user.registration.RegisterUserResult;
import com.example.soclean.usecase.user.registration.RegisterUserPresenter;
import lombok.Getter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Getter
public class RegisterUserWebPresenter implements RegisterUserPresenter {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm")
					.withZone(ZoneId.of("Asia/Jakarta"));

	private RegisterUserResponse response;

	@Override
	public void present(RegisterUserResult result) {
		UserRecord user = result.user();
		String status = user.isActive() ? "Active" : "Inactive";
		String createdAt = DATE_FORMATTER.format(user.getCreatedAt());
		response = new RegisterUserResponse(user.getUsername(), status, createdAt);
	}

}
