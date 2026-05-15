package com.example.soclean.usecase.user.registration;

import com.example.soclean.domain.user.registration.RegisterUserRequest;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public interface RegisterUserUseCase {

	void execute(RegisterUserRequest request, RegisterUserPresenter presenter);

}
