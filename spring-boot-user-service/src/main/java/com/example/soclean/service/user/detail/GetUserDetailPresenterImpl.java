package com.example.soclean.service.user.detail;

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
public class GetUserDetailPresenterImpl implements GetUserDetailPresenter {

	private static final ZoneId ZONE = ZoneId.of("Asia/Jakarta");
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm").withZone(ZONE);

	private GetUserDetailResponse response;

	@Override
	public void present(GetUserDetailResult result) {
		UserDomain user = result.user();
		String status = user.active() ? "Active" : "Inactive";
		String createdAt = DATE_FORMATTER.format(user.createdAt());
		response = new GetUserDetailResponse(user.username().value(), status, createdAt);
	}

}
