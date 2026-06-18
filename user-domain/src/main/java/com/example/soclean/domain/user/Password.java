package com.example.soclean.domain.user;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public record Password(String value) {

	public Password {
		if (value == null || value.isBlank()) {
			throw new WeakPasswordException("Password must not be blank");
		}
		if (value.length() < 8) {
			throw new WeakPasswordException("Password must be at least 8 characters");
		}
		if (!value.matches(".*[a-zA-Z].*") || !value.matches(".*\\d.*")) {
			throw new WeakPasswordException("Password must contain at least one letter and one number");
		}
	}

}
