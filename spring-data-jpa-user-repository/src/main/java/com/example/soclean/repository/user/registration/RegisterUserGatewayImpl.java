package com.example.soclean.repository.user.registration;

import com.example.soclean.domain.user.UserRecord;
import com.example.soclean.repository.user.entity.UserEntity;
import com.example.soclean.repository.user.jpa.UserJpaRepository;
import com.example.soclean.usecase.user.registration.RegisterUserGateway;
import org.springframework.stereotype.Component;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Component
public class RegisterUserGatewayImpl implements RegisterUserGateway {

	private final UserJpaRepository userJpaRepository;

	public RegisterUserGatewayImpl(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@Override
	public UserRecord save(UserRecord user) {
		UserEntity entity = new UserEntity();
		entity.setUsername(user.getUsername());
		entity.setPassword(user.getPassword());
		entity.setActive(user.isActive());
		entity.setCreatedAt(user.getCreatedAt());
		UserEntity saved = userJpaRepository.save(entity);
		UserRecord result = new UserRecord(saved.getUsername(), saved.getPassword());
		result.setId(saved.getId());
		result.setActive(saved.isActive());
		result.setCreatedAt(saved.getCreatedAt());
		return result;
	}

	@Override
	public boolean existsByUsername(String username) {
		return userJpaRepository.existsByUsername(username);
	}

}
