package com.example.soclean.domain.user;

import java.time.Instant;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public record UserDomain(Long id, Username username, Password password, boolean active, Instant createdAt) {

	public static UserDomain register(Username username, Password password) {
		return new UserDomain(null, username, password, true, Instant.now());
	}

	public static UserDomain construct(Long id, Username username, Password password, boolean active, Instant createdAt) {
		return new UserDomain(id, username, password, active, createdAt);
	}

	public String usernameValue() {
		return username.value();
	}

	public String passwordValue() {
		return password.value();
	}

}
