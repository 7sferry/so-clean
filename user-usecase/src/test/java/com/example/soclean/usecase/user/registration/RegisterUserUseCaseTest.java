package com.example.soclean.usecase.user.registration;

import com.example.soclean.domain.user.UserRecord;
import com.example.soclean.domain.user.registration.RegisterUserRequest;
import com.example.soclean.domain.user.registration.RegisterUserResult;
import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
		UserRecord savedUser = new UserRecord("alice", "secret12");
		savedUser.setId(1L);
		willReturn(false).given(registerUserGateway).existsByUsername("alice");
		willReturn(savedUser).given(registerUserGateway).save(any(UserRecord.class));

		registerUserUseCase.execute(new RegisterUserRequest("alice", "secret12"), presenter);

		then(registerUserGateway).should().save(any(UserRecord.class));
		then(presenter).should().present(resultCaptor.capture());
		RegisterUserResult result = resultCaptor.getValue();
		BDDSoftAssertions.thenSoftly(softly -> {
			softly.then(result.user().getId()).isEqualTo(1L);
			softly.then(result.user().getUsername()).isEqualTo("alice");
			softly.then(result.user().isActive()).isTrue();
			softly.then(result.user().getCreatedAt()).isNotNull();
		});
	}

	@Test
	void givenDuplicateUsername_shouldThrowException() {
		willReturn(true).given(registerUserGateway).existsByUsername("alice");

		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("alice", "secret12"), presenter))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Username already taken");

		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenBlankUsername_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("  ", "secret12"), presenter))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Username must not be blank");

		then(registerUserGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenBlankPassword_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("alice", ""), presenter))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Password must not be blank");

		then(registerUserGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenPasswordTooShort_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("alice", "short1"), presenter))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Password must be at least 8 characters");

		then(registerUserGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenPasswordWithoutNumber_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("alice", "allletters"), presenter))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Password must contain at least one letter and one number");

		then(registerUserGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenPasswordWithoutLetter_shouldThrowException() {
		thenThrownBy(() -> registerUserUseCase.execute(
				new RegisterUserRequest("alice", "12345678"), presenter))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Password must contain at least one letter and one number");

		then(registerUserGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

}
