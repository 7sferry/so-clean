package com.example.soclean.domain.user;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public record Username(String value) {

	public Username {
		if (value == null || value.isBlank()) {
			throw new InvalidUsernameException("Username must not be blank");
		}
	}

}
