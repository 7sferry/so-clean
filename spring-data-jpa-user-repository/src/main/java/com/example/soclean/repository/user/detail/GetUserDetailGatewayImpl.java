package com.example.soclean.repository.user.detail;

import com.example.soclean.domain.user.UserRecord;
import com.example.soclean.repository.user.entity.UserEntity;
import com.example.soclean.repository.user.jpa.UserJpaRepository;
import com.example.soclean.usecase.user.detail.GetUserDetailGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Component
public class GetUserDetailGatewayImpl implements GetUserDetailGateway {

	private final UserJpaRepository userJpaRepository;

	public GetUserDetailGatewayImpl(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@Override
	public Optional<UserRecord> findByUsername(String username) {
		return userJpaRepository.findByUsername(username)
				.map(this::toUserRecord);
	}

	private UserRecord toUserRecord(UserEntity entity) {
		UserRecord user = new UserRecord(entity.getUsername(), entity.getPassword());
		user.setId(entity.getId());
		user.setActive(entity.isActive());
		user.setCreatedAt(entity.getCreatedAt());
		return user;
	}

}
