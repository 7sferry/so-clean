package com.example.soclean.cli.user.registration;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.usecase.user.registration.RegisterUserPresenter;
import com.example.soclean.usecase.user.registration.RegisterUserResult;
import lombok.Getter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Getter
public class RegisterUserCliPresenter implements RegisterUserPresenter {

	private static final ZoneId ZONE = ZoneId.of("Asia/Jakarta");
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm").withZone(ZONE);

	private String output;

	@Override
	public void present(RegisterUserResult result) {
		UserDomain user = result.user();
		output = "User registered successfully!\n"
				+ "  Username  : " + user.username().value() + '\n'
				+ "  Status    : " + (user.active() ? "Active" : "Inactive") + '\n';
	}

}
