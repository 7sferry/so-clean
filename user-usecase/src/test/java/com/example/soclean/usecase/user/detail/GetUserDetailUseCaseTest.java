package com.example.soclean.usecase.user.detail;

import com.example.soclean.domain.user.InvalidUsernameException;
import com.example.soclean.domain.user.Password;
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
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@ExtendWith(MockitoExtension.class)
class GetUserDetailUseCaseTest {

	@Mock
	private GetUserDetailGateway getUserDetailGateway;

	@Mock
	private GetUserDetailPresenter presenter;

	@Captor
	private ArgumentCaptor<GetUserDetailResult> resultCaptor;

	@InjectMocks
	private GetUserDetailUseCaseImpl getUserDetailUseCase;

	@Test
	void givenExistingUsername_shouldPresentUserDetail() {
		UserDomain user = UserDomain.construct(1L, new Username("alice"), new Password("secret12"), true, Instant.now());
		willReturn(Optional.of(user)).given(getUserDetailGateway).findByUsername(new Username("alice"));

		getUserDetailUseCase.execute(new GetUserDetailRequest("alice"), presenter);

		then(presenter).should().present(resultCaptor.capture());
		GetUserDetailResult result = resultCaptor.getValue();
		BDDSoftAssertions.thenSoftly(softly -> {
			softly.then(result.user().id()).isEqualTo(1L);
			softly.then(result.user().username().value()).isEqualTo("alice");
			softly.then(result.user().active()).isTrue();
			softly.then(result.user().createdAt()).isNotNull();
		});
	}

	@Test
	void givenNonExistingUsername_shouldThrowException() {
		willReturn(Optional.empty()).given(getUserDetailGateway).findByUsername(new Username("unknown"));

		thenThrownBy(() -> getUserDetailUseCase.execute(
				new GetUserDetailRequest("unknown"), presenter))
				.isInstanceOf(UserNotFoundException.class)
				.hasMessageContaining("User not found: unknown");

		then(presenter).shouldHaveNoInteractions();
	}

	@Test
	void givenBlankUsername_shouldThrowException() {
		thenThrownBy(() -> getUserDetailUseCase.execute(
				new GetUserDetailRequest("  "), presenter))
				.isInstanceOf(InvalidUsernameException.class)
				.hasMessageContaining("Username must not be blank");

		then(getUserDetailGateway).shouldHaveNoInteractions();
		then(presenter).shouldHaveNoInteractions();
	}

}
