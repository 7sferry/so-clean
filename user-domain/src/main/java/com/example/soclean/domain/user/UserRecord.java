package com.example.soclean.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Getter
@Setter
public class UserRecord {

	private Long id;
	private String username;
	private String password;
	private boolean active;
	private Instant createdAt;

	public UserRecord(String username, String password) {
		this.username = username;
		this.password = password;
		this.active = true;
		this.createdAt = Instant.now();
	}

}
