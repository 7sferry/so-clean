package com.example.soclean.cli.user.detail;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.usecase.user.detail.UserDetailPresenter;
import com.example.soclean.usecase.user.detail.UserDetailResult;
import lombok.Getter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Getter
public class UserDetailCliPresenter implements UserDetailPresenter{

	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private String output;

	@Override
	public void present(UserDetailResult result) {
		UserDomain user = result.user();
		output = "User detail:\n"
				+ "  Username  : " + user.username().value() + '\n'
				+ "  Status    : " + (user.active() ? "Active" : "Inactive") + '\n'
				+ "  Birth Date: " + DATE_FORMATTER.format(user.birthDateValue());
	}

}
