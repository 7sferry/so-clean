package com.example.soclean.usecase.user.detail;

import com.example.soclean.domain.user.UserRecord;

import java.util.Optional;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public interface GetUserDetailGateway {

	Optional<UserRecord> findByUsername(String username);

}
