package com.example.soclean.usecase.user.registration;

import com.example.soclean.domain.user.InvalidUsernameException;
import com.example.soclean.domain.user.Password;
import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.domain.user.Username;
import com.example.soclean.domain.user.UsernameAlreadyTakenException;
import com.example.soclean.domain.user.WeakPasswordException;
import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseTest {

	@Mock
	private RegisterUserGateway registerUserGateway;

	@Mock
	private RegisterUserPresenter presenter;

	@Captor
	private ArgumentCaptor<RegisterUserResult> resultCaptor;

	@InjectMocks
	private RegisterUserUseCaseImpl registerUserUseCase;

	@Test
	void givenValidRequest_shouldSaveAndPresentUser() {
		UserDomain savedUser = UserDomain.construct(1L, new Username("alice"), new Password("secret12"), true, Instant.now());
		willReturn(false).given(registerUserGateway).existsByUsername(new Username("alice"));
		willReturn(savedUser).given(registerUserGateway).save(any(UserDomain.class));

		registerUserUseCase.execute(new RegisterUserRequest("alice", "secret12"), presenter);

		then(registerUserGateway).should().save(any(UserDomain.class));
		then(presenter).should().present(resultCaptor.capture());
		RegisterUserResult result = resultCaptor.getValue();
		BDDSoftAssertions.thenSoftly(softly -> {
			softly.then(result.user().id()).isEqualTo(1L);
			softly.then(result.user().username().value()).isEqualTo("alice");
			softly.then(result.user().active()).isTrue();
			softly.then(result.user().createdAt()).isNotNull();
		});
	}

	@Test
	void givenDuplicateUsername_shouldThrowException() {
		willReturn(true).given(registerUserGateway).existsByUsername(new Username("alice"));

		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("alice", "secret12"), presenter))
				.isInstanceOf(UsernameAlreadyTakenException.class)
				.hasMessageContaining("Username already taken");

		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenBlankUsername_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("  ", "secret12"), presenter))
				.isInstanceOf(InvalidUsernameException.class)
				.hasMessageContaining("Username must not be blank");

		then(registerUserGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenBlankPassword_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("alice", ""), presenter))
				.isInstanceOf(WeakPasswordException.class)
				.hasMessageContaining("Password must not be blank");

		then(registerUserGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenPasswordTooShort_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("alice", "short1"), presenter))
				.isInstanceOf(WeakPasswordException.class)
				.hasMessageContaining("Password must be at least 8 characters");

		then(registerUserGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenPasswordWithoutNumber_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("alice", "allletters"), presenter))
				.isInstanceOf(WeakPasswordException.class)
				.hasMessageContaining("Password must contain at least one letter and one number");

		then(registerUserGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenPasswordWithoutLetter_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("alice", "12345678"), presenter))
				.isInstanceOf(WeakPasswordException.class)
				.hasMessageContaining("Password must contain at least one letter and one number");

		then(registerUserGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

}
