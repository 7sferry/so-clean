package com.example.soclean.repository.user.registration;

import com.example.soclean.domain.user.UserRecord;
import com.example.soclean.repository.user.generated.tables.Users;
import com.example.soclean.usecase.user.registration.RegisterUserGateway;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@RequiredArgsConstructor
public class RegisterUserGatewayImpl implements RegisterUserGateway {

	private static final Users USERS = Users.USERS;

	private final DSLContext dsl;

	@Override
	public UserRecord save(UserRecord user) {
		LocalDateTime createdAt = LocalDateTime.ofInstant(user.getCreatedAt(), ZoneOffset.UTC);
		Record record = dsl.insertInto(USERS)
				.set(USERS.USERNAME, user.getUsername())
				.set(USERS.PASSWORD, user.getPassword())
				.set(USERS.ACTIVE, user.isActive())
				.set(USERS.CREATED_AT, createdAt)
				.returningResult(USERS.ID, USERS.USERNAME, USERS.PASSWORD, USERS.ACTIVE, USERS.CREATED_AT)
				.fetchOne();

		UserRecord result = new UserRecord(record.get(USERS.USERNAME), record.get(USERS.PASSWORD));
		result.setId(record.get(USERS.ID));
		result.setActive(record.get(USERS.ACTIVE));
		result.setCreatedAt(record.get(USERS.CREATED_AT).toInstant(ZoneOffset.UTC));
		return result;
	}

	@Override
	public boolean existsByUsername(String username) {
		return dsl.fetchExists(
				dsl.selectOne().from(USERS).where(USERS.USERNAME.eq(username))
		);
	}

}
