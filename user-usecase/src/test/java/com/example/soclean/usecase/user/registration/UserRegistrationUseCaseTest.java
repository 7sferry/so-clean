package com.example.soclean.usecase.user.registration;

import com.example.soclean.domain.user.*;
import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@ExtendWith(MockitoExtension.class)
class UserRegistrationUseCaseTest{

	@Mock
	private UserRegistrationGateway userRegistrationGateway;

	@Mock
	private UserRegistrationPresenter presenter;

	@Captor
	private ArgumentCaptor<UserRegistrationResult> resultCaptor;

	@InjectMocks
	private UserRegistrationDefaultUseCase registerUserUseCase;

	@Test
	void givenValidRequest_shouldSaveAndPresentUser() {
		UserDomain savedUser = UserDomain.construct(1L, new Username("alice"), new BirthDate(LocalDate.now().minusYears(20)), true, Instant.now());
		willReturn(false).given(userRegistrationGateway).existsByUsername(new Username("alice"));
		willReturn(savedUser).given(userRegistrationGateway).save(any(UserDomain.class));

		registerUserUseCase.execute(new UserRegistrationRequest("alice", LocalDate.now().minusYears(20)), presenter);

		then(userRegistrationGateway).should().save(any(UserDomain.class));
		then(presenter).should().present(resultCaptor.capture());
		UserRegistrationResult result = resultCaptor.getValue();
		BDDSoftAssertions.thenSoftly(softly -> {
			softly.then(result.user().id()).isEqualTo(1L);
			softly.then(result.user().username().value()).isEqualTo("alice");
			softly.then(result.user().active()).isTrue();
			softly.then(result.user().createdAt()).isNotNull();
		});
	}

	@Test
	void givenDuplicateUsername_shouldThrowException() {
		willReturn(true).given(userRegistrationGateway).existsByUsername(new Username("alice"));

		thenThrownBy(() -> registerUserUseCase.execute(
				new UserRegistrationRequest("alice", LocalDate.now().minusYears(20)), presenter))
				.isInstanceOf(UsernameAlreadyTakenException.class)
				.hasMessageContaining("Username already taken");

		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenBlankUsername_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new UserRegistrationRequest("  ", LocalDate.now().minusYears(20)), presenter))
				.isInstanceOf(InvalidUsernameException.class)
				.hasMessageContaining("Username must not be blank");

		then(userRegistrationGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenNullBirthDate_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new UserRegistrationRequest("alice", null), presenter))
				.isInstanceOf(IllegalBirthDateException.class)
				.hasMessageContaining("Birth date must not be blank");

		then(userRegistrationGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenUnderageBirthDate_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new UserRegistrationRequest("alice", LocalDate.now()), presenter))
				.isInstanceOf(IllegalBirthDateException.class)
				.hasMessageContaining("Birth date must be at least 18 years old");

		then(userRegistrationGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

}
