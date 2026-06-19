package com.example.soclean.repository.user.registration;

import com.example.soclean.domain.user.Password;
import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.domain.user.Username;
import com.example.soclean.repository.user.entity.UserEntity;
import com.example.soclean.repository.user.jpa.UserJpaRepository;
import com.example.soclean.usecase.user.registration.UserRegistrationGateway;
import lombok.RequiredArgsConstructor;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@RequiredArgsConstructor
public class UserRegistrationJpaGateway implements UserRegistrationGateway{

	private final UserJpaRepository userJpaRepository;

	@Override
	public UserDomain save(UserDomain user) {
		UserEntity entity = new UserEntity();
		entity.setUsername(user.usernameValue());
		entity.setPassword(user.passwordValue());
		entity.setActive(user.active());
		entity.setCreatedAt(user.createdAt());
		UserEntity saved = userJpaRepository.save(entity);
		return UserDomain.construct(
				saved.getId(),
				new Username(saved.getUsername()),
				new Password(saved.getPassword()),
				saved.isActive(),
				saved.getCreatedAt()
		);
	}

	@Override
	public boolean existsByUsername(Username username) {
		return userJpaRepository.existsByUsername(username.value());
	}

}
