package com.example.soclean.domain.user;

import java.time.LocalDate;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public record BirthDate(LocalDate value) {

	public BirthDate{
		if (value == null) {
			throw new IllegalBirthDateException("Birth date must not be blank");
		}
		if (LocalDate.now().minusYears(18).isBefore(value)) {
			throw new IllegalBirthDateException("Birth date must be at least 18 years old");
		}
	}

}
