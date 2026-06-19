package com.example.soclean.usecase.user.registration;

import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.domain.user.Username;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public interface UserRegistrationGateway{

	UserDomain save(UserDomain user);

	boolean existsByUsername(Username username);

}
