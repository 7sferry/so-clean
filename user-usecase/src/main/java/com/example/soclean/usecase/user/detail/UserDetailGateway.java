package com.example.soclean.usecase.user.detail;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.domain.user.Username;

import java.util.Optional;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public interface UserDetailGateway{

	Optional<UserDomain> findByUsername(Username username);

}
