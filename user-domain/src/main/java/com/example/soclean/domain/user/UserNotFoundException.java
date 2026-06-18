package com.example.soclean.domain.user;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(Username username) {
		super("User not found: " + username.value());
	}

}
