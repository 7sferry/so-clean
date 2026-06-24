package com.example.soclean.repository.user.detail;

import com.example.soclean.domain.user.BirthDate;
import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.domain.user.Username;
import com.example.soclean.repository.user.generated.tables.Users;
import com.example.soclean.usecase.user.detail.UserDetailGateway;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;

import java.time.ZoneOffset;
import java.util.Optional;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@RequiredArgsConstructor
public class UserDetailJooqGateway implements UserDetailGateway{

	private static final Users USERS = Users.USERS;

	private final DSLContext dsl;

	@Override
	public Optional<UserDomain> findByUsername(Username username) {
		return dsl.select(USERS.ID, USERS.USERNAME, USERS.BIRTH_DATE, USERS.ACTIVE, USERS.CREATED_AT)
				.from(USERS)
				.where(USERS.USERNAME.eq(username.value()))
				.fetchOptional()
				.map(this::toUser);
	}

	private UserDomain toUser(Record record) {
		return UserDomain.construct(
				record.get(USERS.ID),
				new Username(record.get(USERS.USERNAME)),
				new BirthDate(record.get(USERS.BIRTH_DATE)),
				record.get(USERS.ACTIVE),
				record.get(USERS.CREATED_AT).toInstant(ZoneOffset.UTC)
		);
	}

}
