package com.example.soclean.repository.user.detail;

import com.example.soclean.domain.user.Password;
import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.domain.user.Username;
import com.example.soclean.repository.user.entity.UserEntity;
import com.example.soclean.repository.user.jpa.UserJpaRepository;
import com.example.soclean.usecase.user.detail.UserDetailGateway;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@RequiredArgsConstructor
public class UserDetailJpaGateway implements UserDetailGateway{

	private final UserJpaRepository userJpaRepository;

	@Override
	public Optional<UserDomain> findByUsername(Username username) {
		return userJpaRepository.findByUsername(username.value())
				.map(this::toUser);
	}

	private UserDomain toUser(UserEntity entity) {
		return UserDomain.construct(
				entity.getId(),
				new Username(entity.getUsername()),
				new Password(entity.getPassword()),
				entity.isActive(),
				entity.getCreatedAt()
		);
	}

}
