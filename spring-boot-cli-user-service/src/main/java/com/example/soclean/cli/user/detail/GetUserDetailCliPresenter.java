package com.example.soclean.cli.user.detail;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.usecase.user.detail.GetUserDetailPresenter;
import com.example.soclean.usecase.user.detail.GetUserDetailResult;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public class GetUserDetailCliPresenter implements GetUserDetailPresenter {

	private static final ZoneId ZONE = ZoneId.of("Asia/Jakarta");
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm").withZone(ZONE);

	private String output;

	@Override
	public void present(GetUserDetailResult result) {
		UserDomain user = result.user();
		output = "User detail:\n"
				+ "  Username  : " + user.username().value() + '\n'
				+ "  Status    : " + (user.isActive() ? "Active" : "Inactive") + '\n'
				+ "  Created at: " + DATE_FORMATTER.format(user.createdAt());
	}

	public String getOutput() {
		return output;
	}

}
