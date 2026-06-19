package com.example.soclean.repository.user.jpa;

import com.example.soclean.repository.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

	boolean existsByUsername(String username);

	Optional<UserEntity> findByUsername(String username);

}
