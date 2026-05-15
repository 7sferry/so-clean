package com.example.soclean.usecase.user.detail;

import com.example.soclean.domain.user.detail.GetUserDetailRequest;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public interface GetUserDetailUseCase {

	void execute(GetUserDetailRequest request, GetUserDetailPresenter presenter);

}
