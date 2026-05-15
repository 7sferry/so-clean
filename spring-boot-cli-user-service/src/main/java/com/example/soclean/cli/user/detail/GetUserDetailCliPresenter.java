package com.example.soclean.cli.user.detail;

import com.example.soclean.domain.user.UserRecord;
import com.example.soclean.domain.user.detail.GetUserDetailResult;
import com.example.soclean.usecase.user.detail.GetUserDetailPresenter;

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
		UserRecord user = result.user();
		output = "User detail:\n"
				+ "  Username  : " + user.getUsername() + "\n"
				+ "  Status    : " + (user.isActive() ? "Active" : "Inactive") + "\n"
				+ "  Created at: " + DATE_FORMATTER.format(user.getCreatedAt());
	}

	public String getOutput() {
		return output;
	}

}
