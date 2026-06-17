package com.example.soclean.repository.user.registration;

import com.example.soclean.domain.user.Password;
import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.domain.user.Username;
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
	public UserDomain save(UserDomain user) {
		LocalDateTime createdAt = LocalDateTime.ofInstant(user.createdAt(), ZoneOffset.UTC);
		Record record = dsl.insertInto(USERS)
				.set(USERS.USERNAME, user.username().value())
				.set(USERS.PASSWORD, user.password().value())
				.set(USERS.ACTIVE, user.isActive())
				.set(USERS.CREATED_AT, createdAt)
				.returningResult(USERS.ID, USERS.USERNAME, USERS.PASSWORD, USERS.ACTIVE, USERS.CREATED_AT)
				.fetchOne();

		return UserDomain.construct(
				record.get(USERS.ID),
				new Username(record.get(USERS.USERNAME)),
				new Password(record.get(USERS.PASSWORD)),
				record.get(USERS.ACTIVE),
				record.get(USERS.CREATED_AT).toInstant(ZoneOffset.UTC)
		);
	}

	@Override
	public boolean existsByUsername(Username username) {
		return dsl.fetchExists(
				dsl.selectOne().from(USERS).where(USERS.USERNAME.eq(username.value()))
		);
	}

}
