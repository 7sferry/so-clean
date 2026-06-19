package com.example.soclean.cli.user.registration;

import com.example.soclean.usecase.user.registration.UserRegistrationRequest;
import com.example.soclean.usecase.user.registration.UserRegistrationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.CommandRegistration.OptionArity;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Command
@RequiredArgsConstructor
public class UserRegistrationCommand{

	private final UserRegistrationUseCase userRegistrationUseCase;

	@Command(command = "register-user", description = "Register a new user")
	public String register(@Option(arity = OptionArity.EXACTLY_ONE) String username,
						  @Option(arity = OptionArity.EXACTLY_ONE) String password) {
		UserRegistrationCliPresenter presenter = new UserRegistrationCliPresenter();
		userRegistrationUseCase.execute(new UserRegistrationRequest(username, password), presenter);
		return presenter.getOutput();
	}

}
