package com.example.soclean.domain.user;

import java.time.Instant;
import java.time.LocalDate;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public record UserDomain(Long id, Username username, BirthDate birthDate, boolean active, Instant createdAt) {

	public static UserDomain register(Username username, BirthDate birthDate) {
		return new UserDomain(null, username, birthDate, true, Instant.now());
	}

	public static UserDomain construct(Long id, Username username, BirthDate birthDate, boolean active, Instant createdAt) {
		return new UserDomain(id, username, birthDate, active, createdAt);
	}

	public String usernameValue() {
		return username.value();
	}

	public LocalDate birthDateValue() {
		return birthDate.value();
	}

}
