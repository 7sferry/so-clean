package com.example.soclean.usecase.user.registration;

import java.time.LocalDate;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public record UserRegistrationRequest(String username, LocalDate birthDate) {

}
