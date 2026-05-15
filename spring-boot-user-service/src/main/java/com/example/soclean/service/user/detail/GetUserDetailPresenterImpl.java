package com.example.soclean.service.user.detail;

import com.example.soclean.domain.user.UserRecord;
import com.example.soclean.domain.user.detail.GetUserDetailResponse;
import com.example.soclean.domain.user.detail.GetUserDetailResult;
import com.example.soclean.usecase.user.detail.GetUserDetailPresenter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public class GetUserDetailPresenterImpl implements GetUserDetailPresenter {

	private static final ZoneId ZONE = ZoneId.of("Asia/Jakarta");
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm").withZone(ZONE);

	private GetUserDetailResponse response;

	@Override
	public void present(GetUserDetailResult result) {
		UserRecord user = result.user();
		String status = user.isActive() ? "Active" : "Inactive";
		String createdAt = DATE_FORMATTER.format(user.getCreatedAt());
		response = new GetUserDetailResponse(user.getUsername(), status, createdAt);
	}

	public GetUserDetailResponse getResponse() {
		return response;
	}

}
