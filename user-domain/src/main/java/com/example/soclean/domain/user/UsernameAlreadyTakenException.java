package com.example.soclean.domain.user;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public class UsernameAlreadyTakenException extends RuntimeException {

	public UsernameAlreadyTakenException(Username username) {
		super("Username already taken: " + username.value());
	}

}
