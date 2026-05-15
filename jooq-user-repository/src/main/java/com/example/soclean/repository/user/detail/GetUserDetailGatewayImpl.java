package com.example.soclean.repository.user.detail;

import com.example.soclean.domain.user.UserRecord;
import com.example.soclean.repository.user.generated.tables.Users;
import com.example.soclean.usecase.user.detail.GetUserDetailGateway;
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
public class GetUserDetailGatewayImpl implements GetUserDetailGateway {

	private static final Users USERS = Users.USERS;

	private final DSLContext dsl;

	@Override
	public Optional<UserRecord> findByUsername(String username) {
		return dsl.select(USERS.ID, USERS.USERNAME, USERS.PASSWORD, USERS.ACTIVE, USERS.CREATED_AT)
				.from(USERS)
				.where(USERS.USERNAME.eq(username))
				.fetchOptional()
				.map(this::toUserRecord);
	}

	private UserRecord toUserRecord(Record record) {
		UserRecord user = new UserRecord(record.get(USERS.USERNAME), record.get(USERS.PASSWORD));
		user.setId(record.get(USERS.ID));
		user.setActive(record.get(USERS.ACTIVE));
		user.setCreatedAt(record.get(USERS.CREATED_AT).toInstant(ZoneOffset.UTC));
		return user;
	}

}
