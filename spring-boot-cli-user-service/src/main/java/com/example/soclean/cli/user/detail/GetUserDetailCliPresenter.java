package com.example.soclean.cli.user.detail;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.usecase.user.detail.GetUserDetailPresenter;
import com.example.soclean.usecase.user.detail.GetUserDetailResult;
import lombok.Getter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Getter
public class GetUserDetailCliPresenter implements GetUserDetailPresenter {

	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.of("Asia/Jakarta"));

	private String output;

	@Override
	public void present(GetUserDetailResult result) {
		UserDomain user = result.user();
		output = "User detail:\n"
				+ "  Username  : " + user.username().value() + '\n'
				+ "  Status    : " + (user.active() ? "Active" : "Inactive") + '\n'
				+ "  Created at: " + DATE_FORMATTER.format(user.createdAt());
	}

}
