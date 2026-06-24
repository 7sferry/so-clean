package com.example.soclean.usecase.user.detail;

import com.example.soclean.domain.user.InvalidUsernameException;
import com.example.soclean.domain.user.BirthDate;
import com.example.soclean.domain.user.UserDomain;
import com.example.soclean.domain.user.UserNotFoundException;
import com.example.soclean.domain.user.Username;
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
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@ExtendWith(MockitoExtension.class)
class UserDetailUseCaseTest{

	@Mock
	private UserDetailGateway userDetailGateway;

	@Mock
	private UserDetailPresenter presenter;

	@Captor
	private ArgumentCaptor<UserDetailResult> resultCaptor;

	@InjectMocks
	private UserDetailDefaultUseCase getUserDetailUseCase;

	@Test
	void givenExistingUsername_shouldPresentUserDetail() {
		UserDomain user = UserDomain.construct(1L, new Username("alice"), new BirthDate(LocalDate.now().minusYears(20)), true, Instant.now());
		willReturn(Optional.of(user)).given(userDetailGateway).findByUsername(new Username("alice"));

		getUserDetailUseCase.execute(new UserDetailRequest("alice"), presenter);

		then(presenter).should().present(resultCaptor.capture());
		UserDetailResult result = resultCaptor.getValue();
		BDDSoftAssertions.thenSoftly(softly -> {
			softly.then(result.user().id()).isEqualTo(1L);
			softly.then(result.user().username().value()).isEqualTo("alice");
			softly.then(result.user().active()).isTrue();
			softly.then(result.user().createdAt()).isNotNull();
		});
	}

	@Test
	void givenNonExistingUsername_shouldThrowException() {
		willReturn(Optional.empty()).given(userDetailGateway).findByUsername(new Username("unknown"));

		thenThrownBy(() -> getUserDetailUseCase.execute(
				new UserDetailRequest("unknown"), presenter))
				.isInstanceOf(UserNotFoundException.class)
				.hasMessageContaining("User not found: unknown");

		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenBlankUsername_shouldThrowException() {
		thenThrownBy(() -> getUserDetailUseCase.execute(
				new UserDetailRequest("  "), presenter))
				.isInstanceOf(InvalidUsernameException.class)
				.hasMessageContaining("Username must not be blank");

		then(userDetailGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

}
