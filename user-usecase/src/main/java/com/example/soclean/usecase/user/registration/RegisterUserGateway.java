package com.example.soclean.usecase.user.registration;

import com.example.soclean.domain.user.UserRecord;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public interface RegisterUserGateway {

	UserRecord save(UserRecord user);

	boolean existsByUsername(String username);

}
